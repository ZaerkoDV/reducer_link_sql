"use strict";

var angular = require("angular");
var angularui = require("angular-ui-router");
var app = angular.module("app", ["ui.router"]);

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
        $stateProvider.state("user-edit-profile", {
            url: "/user-edit-profile",
            views: { "": {
                templateUrl: "/views/user-edit-profile.html",
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
        $stateProvider.state("user-link-edit", {
            url: "/user-link-edit",
            views: { "": {
                templateUrl: "/views/user-link-edit.html",
                controller: "UserController"
            } }
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
