"use strict";

var angular = require("angular");
var angularui = require("angular-ui-router");
var angulatbootstrap = require("angular-bootstrap");
var app = angular.module("app", ["ui.router", "ui.bootstrap", "ui.bootstrap.tpls"]);

app.config([
    "$stateProvider",
    "$urlRouterProvider",
    "$httpProvider",
    function($stateProvider, $urlRouterProvider, $httpProvider) {
        $stateProvider.state("link-list", {
            url: "/link-list",
            views: { "": {
                templateUrl: "/index.html",
                controller: "LinkController"
            } }
        });
        $stateProvider.state("link-list-search", {
            url: "/link-list-search",
            views: { "": {
                templateUrl: "/views/link-list-search.html",
                controller: "LinkController"
            } }
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
            url: "/user-link-list/:token",
            views: { "": {
                templateUrl: "/views/user-link-list.html",
                controller: "UserController"
            } },
            params: { token: "" }
        });
        $stateProvider.state("user-profile-edit", {
            url: "/user-profile-edit/:token",
            views: { "": {
                templateUrl: "/views/user-profile-edit.html",
                controller: "UserController"
            } },
            params: { token: "" }
        });
        $stateProvider.state("user-link-edit", {
            url: "/user-link-edit/:token",
            views: { "": {
                templateUrl: "/views/user-link-edit.html",
                controller: "UserController"
            } },
            params: { token: "" }
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
