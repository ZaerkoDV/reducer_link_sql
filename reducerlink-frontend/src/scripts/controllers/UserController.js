"use strict";

var UserController = function($scope, $state, $window, AppService, UserService) {
    var angular = AppService.angular;

    $scope.idUser = "";

    $scope.token = {
        token: $state.params.token
    };

    $scope.login = {
        login: "",
        password: ""
    };

    $scope.userSecurity = {
        login: "",
        password: "",
        user: {
            id: "",
            firstName: "",
            lastName: "",
            middleName: "",
            birth: 1477296578,
            status: "active"
        }
    };

    $scope.link = {
        tag: "",
        fullUrl: "",
        comment: "",
        user: {
            id: ""
        }
    };

    $scope.redirectToUserLinkList = function() {
        $state.go("user-link-list", { token: $scope.token.token });
    };

    $scope.reditectToLinkEdit = function() {
        $state.go("user-link-edit", { token: $state.params.token });
    };

    $scope.redirectToEditProfile = function() {
        $state.go("user-profile-edit", { token: $state.params.token });
        $scope.loadUserProfile();
    };

    $scope.signup = function() {
        var userSecurityContext = angular.copy($scope.userSecurity);

        AppService.api(
            UserService.signup(userSecurityContext),
            function(response) {
                $state.go("link-list");
            },
            function(error) {
                console.log("Error signup");
            }
        );
    };

    $scope.enterLogin = function() {
        var loginContext = angular.copy($scope.login);
        AppService.api(
            UserService.login(loginContext),
            function(response) {
                $state.go("user-link-list", { token: response });
            },
            function(error) {
                console.log("Error login");
            }
        );
    };
    /*use ng-init=getUserIdByToken() for call user-link-list state*/
    $scope.getUserIdByToken = function() {
        AppService.api(
            UserService.getUserIdByToken($scope.token),
            function(response) {
                $scope.idUser = response;
                $scope.getUserLinkByIdUser($scope.idUser);
            },
            function(error) {
                console.log("Error load id user");
            }
        );
    };

    $scope.getUserLinkByIdUser = function(id) {
        AppService.api(
            UserService.getAllUserLink(id),
            function(response) {
                $scope.linkHistoryList = response.list;
            },
            function(error) {
                console.log("Error load user list link");
            }
        );
    };

    $scope.deleteLink = function(idLink) {
        $scope.link = {
            id: idLink
        };
        AppService.api(
            UserService.deleteLinkById($scope.link),
            function(response) {
                $scope.getUserIdByToken();
                console.log("Success deleted");
            },
            function(error) {
                console.log("Error load user list link");
            }
        );
    };

    $scope.editLink = function() {
    };

    // не выводиться объект
    $scope.loadUserProfile = function() {
        AppService.api(
            UserService.getUserIdByToken($scope.token),
            function(response) {
                $scope.idUser = response;
                AppService.api(
                    UserService.getUserById($scope.idUser),
                    function(response) {
                        $scope.userSecurity.user = response;
                        console.log("Success load");
                    },
                    function(error) {
                        console.log("Error load");
                    }
                );
            },
            function(error) {
                console.log("Error load id user");
            }
        );
    };
    /*$scope.updateUserProfile = function() {
    };*/
    $scope.userPhoto = {};
    $scope.loadUserPhoto = function() {
        AppService.api(
            UserService.getUserIdByToken($scope.token),
            function(response) {
                var photo = jQuery("#photo").get(0);
                var fd = new FormData();
                fd.append("file", photo.files[0]);
                fd.append("id", response.id);
                AppService.api(
                    UserService.createPhoto(fd),
                    function(response) {
                        $scope.userPhoto.id = response.id;
                        $scope.userPhoto.name = photo.files[0].name;
                        $scope.userPhoto.path = "%URL_PREFIX_BACK%/backend/photo/get/" + response.id;
                    },
                    function(error) {
                        console.log("Photo Load is failure");
                    }
                );
            },
            function(error) {
                console.log("Error load id user");
            }
        );
    };

    $scope.deletePhotoById = function(idPhoto) {
        $scope.photo = {
            id: idPhoto
        };
        AppService.api(
            UserService.deletePhotoById($scope.photo),
            function(response) {
                console.log("Delete photo success");
            },
            function(error) {
                console.log("Delete photo failure");
            }
        );
    };

    $scope.createLink = function() {
        AppService.api(
            UserService.getUserIdByToken($scope.token),
            function(response) {
                $scope.link.user.id = response.id;
                var userLinkContext = angular.copy($scope.link);
                AppService.api(
                    UserService.createLink(userLinkContext),
                    function(response) {
                        $scope.link = {};
                    },
                    function(error) {
                        console.log("Error create link");
                    }
                );
            },
            function(error) {
                console.log("Error load id user");
            }
        );
    };

    $scope.today = function() {
        $scope.birth = new Date();
    };

    $scope.today();

    $scope.open1 = function() {
        $scope.popup1.opened = true;
    };

    $scope.setDate = function(year, month, day) {
        $scope.birth = new Date(year, month, day);
    };

    $scope.dateOptions = {
        formatYear: "yy",
        startingDay: 1
    };

    $scope.popup1 = {
        opened: false
    };
};

module.exports = ["$scope", "$state", "$window", "AppService", "UserService", UserController];
