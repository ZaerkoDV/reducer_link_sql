"use strict";

var UserController = function($scope, $state, $window, AppService, UserService) {
    var angular = AppService.angular;

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
            birth: 1477048727,
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
                $scope.token.token = response;
                $scope.getUserIdByToken();
            },
            function(error) {
                console.log("Error login");
            }
        );
    };

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
    // не выгружается массив
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

    $scope.redirectToUserLinkList = function() {
        $state.go("user-link-list", { token: $scope.token.token });
    };

    $scope.reditectToLinkEdit = function() {
        $state.go("user-link-edit", { token: $state.params.token });
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

    $scope.redirectToEditProfile = function() {
        $state.go("user-profile-edit", { token: $state.params.token });
        $scope.loadUserProfile();
    };
    //не изменяются занчения на форме хотя данные приходят
    $scope.loadUserProfile = function() {
        AppService.api(
            UserService.getUserIdByToken($scope.token),
            function(response) {
                $scope.id = response;
                AppService.api(
                    UserService.getUserById($scope.id),
                    function(response) {
                        console.log("User load success", response);
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

    /*$scope.updateUserProfile = function() {
    };*/

    /*
    $scope.userPhoto.path = {};
    $scope.loadUserPhoto = function() {
        var photo = jQuery("#photo").get(0);
        var fd = new FormData();
        fd.append("file", photo.files[0]);
        fd.append("id", "3");
        AppService.api(
            ProductService.uploadImage(fd),
            function(response) {
                $scope.userPhoto.id = response.id,
                $scope.userPhoto.name = photo.files[0].name,
                $scope.userPhoto.path = "%URL_PREFIX_BACK%/backend/photo/get/" + response.id;
            },
            function(error) {
                console.log("Upload is failure");
            }
        );
    };
    */
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
