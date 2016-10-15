"use strict";

const gulp = require("gulp");
const gulpJshint = require("gulp-jshint");
const gulpJscs = require("gulp-jscs");
const gulpJscsStylish = require("gulp-jscs-stylish");
const gulpStylus = require("gulp-stylus");
const gulpSourcemaps = require("gulp-sourcemaps");
const gulpConnect = require("gulp-connect");
const gulpPlumber = require("gulp-plumber");
const gulpUtil = require("gulp-util");
const gulpJade = require("gulp-jade");
const gulpReplaceTask = require("gulp-replace-task");
const runSequence = require("run-sequence");
const del = require("del");
const jshintStylish = require("jshint-stylish");
const poststylus = require("poststylus");
const autoprefixer = require("autoprefixer");
const laggard = require("laggard");
const browserify = require("browserify");
const vinylSourceStream = require("vinyl-source-stream");
const vinylBuffer = require("vinyl-buffer");
const mergeStream = require("merge-stream");
const path = require("path");
const yargs = require("yargs");
const regexpQuote = require("regexp-quote");

// Paths

const PATH_SRC = "./src";
const PATH_DEST = "./dist";

const PATH_SCRIPTS = PATH_SRC + "/scripts/**/*.js";
const PATH_SCRIPTS_COMPILE = PATH_SRC + "/scripts/application.js";
const PATH_SCRIPTS_DEST = PATH_DEST + "/scripts";
const PATH_STYLES = PATH_SRC + "/styles/**/*.styl";
const PATH_STYLES_COMPILE = PATH_SRC + "/styles/application.styl";
const PATH_STYLES_DEST = PATH_DEST + "/styles";
const PATH_TEMPLATES = PATH_SRC + "/**/*.jade";

const PATH_TEMPLATES_COMPILE = [
    PATH_SRC + "/*.jade",
    PATH_SRC + "/views/*.jade"
];

const PATH_TEMPLATES_DEST = PATH_DEST;
const PATH_FONTS_DEST = PATH_DEST + "/fonts";

const PATH_ASSETS = [
    PATH_SRC + "/**/*",
    "!" + PATH_SRC + "/**/*.jade",
    "!" + PATH_SRC + "/partials",
    "!" + PATH_SRC + "/partials/**/*",
    "!" + PATH_SRC + "/scripts",
    "!" + PATH_SRC + "/scripts/**/*",
    "!" + PATH_SRC + "/styles",
    "!" + PATH_SRC + "/styles/**/*",
    "!" + PATH_SRC + "/views/partials",
    "!" + PATH_SRC + "/views/partials/**/*"
];

// Configuration

const SERVER_PORT = 8082;

const ENV_DEVELOPMENT = {
    URL_PREFIX_BACK: 'http://localhost:8080',
    URL_PREFIX_FRONT: 'http://localhost:8082'
};

const ENV_PRODUCTION = {
    URL_PREFIX_BACK: '/backend',
    URL_PREFIX_FRONT: '/front'
};

// Init

const argv = yargs
    .choices("env", ["development", "production"])
    .default("env", "development")
    .argv;

// Helpers

function permissiveErrorHandler(error) {
    gulpUtil.log(gulpUtil.colors.red("Error occurred:\n\n") + error.toString() + "\n");
    this.emit("end");
}

// Tasks

gulp.task("clean", function() {
    return del(PATH_DEST + "/**/*");
});

gulp.task("lint:scripts", function() {
    const jshintConfig = require("./package").jshintConfig;
    jshintConfig.lookup = true;

    return mergeStream(
        gulp.src(PATH_SCRIPTS)
            .pipe(gulpPlumber(permissiveErrorHandler))
            .pipe(gulpJshint(jshintConfig))
            .pipe(gulpJshint.reporter(jshintStylish))
            .pipe(gulpJshint.reporter("fail"))
    ,
        gulp.src(PATH_SCRIPTS)
            .pipe(gulpPlumber(permissiveErrorHandler))
            .pipe(gulpJscs())
            .pipe(gulpJscsStylish())
            .pipe(gulpJscs.reporter("fail"))
    );
});

gulp.task("compile:scripts", ["lint:scripts"], function() {
    var replacements = ((argv.env == "production") ? ENV_PRODUCTION : ENV_DEVELOPMENT)
    var replacementPatterns = [];

    for (var k in replacements) {
        if (replacements.hasOwnProperty(k)) {
            replacementPatterns.push({
                match: new RegExp("%" + regexpQuote(k) + "%", "g"),
                replacement: replacements[k]
            });
        }
    }

    return browserify({
        entries: PATH_SCRIPTS_COMPILE,
        debug: true
    })
    .plugin("browserify-bower", {
        require: ["*"],
        mainfiles: {
            bootstrap: "dist/js/bootstrap.js"
        }
    })
    .bundle()
    .on("error", permissiveErrorHandler)
    .pipe(vinylSourceStream(path.basename(PATH_SCRIPTS_COMPILE)))
    .pipe(vinylBuffer())
    .pipe(gulpSourcemaps.init({
        loadMaps: true
    }))
    .pipe(gulpSourcemaps.write("./"))
    .pipe(gulpReplaceTask({
        patterns: replacementPatterns
    }))
    .pipe(gulp.dest(PATH_SCRIPTS_DEST));
});

gulp.task("compile:styles", function() {
    return gulp.src(PATH_STYLES_COMPILE)
        .pipe(gulpPlumber(permissiveErrorHandler))
        .pipe(gulpSourcemaps.init())
        .pipe(gulpStylus({
            use: [
                poststylus([
                    autoprefixer({ browsers: ["last 2 versions", "ie >= 8", "> 1%"] }),
                    laggard()
                ])
            ]
        }))
        .pipe(gulpSourcemaps.write("."))
        .pipe(gulp.dest(PATH_STYLES_DEST));
});

gulp.task("compile:templates", function() {
    return gulp.src(PATH_TEMPLATES_COMPILE, {
            base: "./src"
        })
        .pipe(gulpPlumber(permissiveErrorHandler))
        .pipe(gulpJade({
            locals: {},
            pretty: true
        }))
        .pipe(gulp.dest(PATH_TEMPLATES_DEST));
});

gulp.task("compile", ["compile:scripts", "compile:styles", "compile:templates"]);

gulp.task("copy:libs", function() {
    return mergeStream(
        gulp.src([
            "bower_components/bootstrap/dist/css/bootstrap.css",
            "bower_components/bootstrap/dist/css/bootstrap-theme.css"
        ])
        .pipe(gulp.dest(PATH_STYLES_DEST))
    ,
        gulp.src([
            "bower_components/bootstrap/dist/fonts/*"
        ])
        .pipe(gulp.dest(PATH_FONTS_DEST))
    );
});

gulp.task("copy:assets",function() {
    return gulp.src(PATH_ASSETS, {
        base: PATH_SRC
    })
    .pipe(gulp.dest(PATH_DEST));
});

gulp.task("copy", ["copy:libs", "copy:assets"]);

gulp.task("build", function(cb) {
    runSequence(
        "clean",
        ["compile", "copy"],
        cb
    );
});

gulp.task("serve", function() {
    return gulpConnect.server({
        port: SERVER_PORT,
        root: PATH_DEST
    });
});

gulp.task("serve:build", function(cb) {
    runSequence("build", "serve", cb);
});

gulp.task("watch", ["serve:build"], function() {
    gulp.watch(PATH_SCRIPTS, ["compile:scripts"]);
    gulp.watch(PATH_STYLES, ["compile:styles"]);
    gulp.watch(PATH_TEMPLATES, ["compile:templates"]);
    gulp.watch(PATH_ASSETS, ["copy:assets"]);
});

gulp.task("default", ["serve:build"]);
