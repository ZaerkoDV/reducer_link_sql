"use strict";

var UserService = function($http) {
    var user = {};

    user.login = function(requirements) {
        return $http.post("/backend/user/autorization/login", requirements);
    };

    user.signup = function(requirements) {
        return $http.post("/backend/user/common/signup", requirements);
    };

    user.getUserIdByToken = function(requirements) {
        return $http.post("/backend/user/byToken/getId", requirements);
    };

    user.getAllUserLink = function(requirements) {
        return $http.post("/backend/link/byIdUser/getList", requirements);
    };

    return user;
};

module.exports = ["$http", UserService];
