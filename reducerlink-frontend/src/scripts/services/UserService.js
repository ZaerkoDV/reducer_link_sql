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

    user.createLink = function(requirements) {
        return $http.post("/backend/link/new/create", requirements);
    };

    user.getUserById = function(requirements) {
        return $http.post("/backend/user/byId/get", requirements);
    };

    user.createPhoto = function(requirements) {
        return $http.post("/backend/user/photo/create", requirements, {
            // Angular’s default transformRequest function will try to serialize our FormData object, so we override it
            // with the identity function to leave the data intact
            transformRequest: window.angular.identity,
            // By setting ‘Content-Type’: undefined, the browser sets the Content-Type to multipart/form-data for us and
            // fills in the correct boundary. Manually setting ‘Content-Type’: multipart/form-data will fail to fill in
            // the boundary parameter of the request.
            headers: { "Content-Type": undefined }
        });
    };

    user.deletePhotoById = function(requirements) {
        return $http.post("/backend/user/photo/delete", requirements);
    };

    return user;
};

module.exports = ["$http", UserService];
