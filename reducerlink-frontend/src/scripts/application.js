"use strict";

var angular = require("angular");
var angularui = require("angular-ui-router");
var angularcookies = require("angular-cookies");
var angulatbootstrap = require("angular-bootstrap");
var app = angular.module("app", ["ui.router", "ui.bootstrap", "ui.bootstrap.tpls", "ngCookies"]);

app.config([
    "$stateProvider",
    "$urlRouterProvider",
    "$httpProvider",
    "$cookiesProvider",
    function($stateProvider, $urlRouterProvider, $httpProvider, $cookiesProvider) {
        $stateProvider.state("home", {
            url: "/home",
            views: { "": {
                templateUrl: "/index.html",
                controller: "LinkController"
            } }
        });
        $stateProvider.state("link-list-search", {
            url: "/link-list-search/:tag",
            views: { "": {
                templateUrl: "/views/link-list-search.html",
                controller: "LinkController"
            } },
            params: { tag: "" }
        });
        $stateProvider.state("login", {
            url: "/login",
            views: { "": {
                templateUrl: "/views/login.html",
                controller: "UserController"
            } }
        });
        $stateProvider.state("signup", {
            url: "/signup",
            views: { "": {
                templateUrl: "/views/signup.html",
                controller: "UserController"
            } }
        });
        $stateProvider.state("user-link-list", {
            url: "/user-link-list",
            views: { "": {
                templateUrl: "/views/user-link-list.html",
                controller: "UserController"
            } }
        });
        $stateProvider.state("user-profile-edit", {
            url: "/user-profile-edit",
            views: { "": {
                templateUrl: "/views/user-profile-edit.html",
                controller: "UserController"
            } }
        });
        $stateProvider.state("user-link-edit", {
            url: "/user-link-edit/:idLink",
            views: { "": {
                templateUrl: "/views/user-link-edit.html",
                controller: "UserController"
            } },
            params: { idLink: "" }
        });
        $urlRouterProvider.otherwise("/");
        $httpProvider.interceptors.push(require("./misc/HttpInterceptor"));
    }
]);
app.factory("EventDispatcherService", require("./services/EventDispatcherService"));
app.factory("AppService", require("./services/AppService"));
app.factory("LinkService", require("./services/LinkService"));
app.factory("UserService", require("./services/UserService"));
app.controller("LinkController", require("./controllers/LinkController"));
app.controller("UserController", require("./controllers/UserController"));
