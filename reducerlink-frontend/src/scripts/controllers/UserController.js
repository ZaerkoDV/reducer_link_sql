"use strict";

var UserController = function($scope, $state, $window, AppService, UserService) {
    var angular = AppService.angular;

    $scope.userPhoto = {};

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
        idLink: $state.params.idLink,
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

    $scope.reditectToLinkEdit = function(idLink) {
        $state.go("user-link-edit", { token: $state.params.token, idLink: idLink });
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
                $state.go("home");
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

    $scope.logout = function() {
        AppService.api(
            UserService.logout($scope.token),
            function(response) {
                $state.go("home");
                console.log("Logout is success");
            },
            function(error) {
                console.log("Logout is failed");
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

    $scope.loadLinkForEdit = function() {
        if ($scope.link.idLink !== undefined) {
            $scope.link = {
                id: $scope.link.idLink,
                tag: "",
                fullUrl: "",
                comment: ""
            };

            AppService.api(
                UserService.getLinkById($scope.link),
                function(response) {
                    $scope.link = response;
                    console.log("Link load successfully");
                },
                function(error) {
                    console.log("Error load link for edit");
                }
            );
        }
    };

    $scope.updateLink = function() {
        AppService.api(
            UserService.updateLinkById($scope.link),
            function(response) {
                $scope.link = response;
            },
            function(error) {
                console.log("Update successfully");
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

    $scope.loadUserProfile = function() {
        AppService.api(
            UserService.getUserIdByToken($scope.token),
            function(response) {
                $scope.idUser = response;
                $scope.loadUserPhoto($scope.idUser.id);
                AppService.api(
                    UserService.getUserById($scope.idUser),
                    function(response) {
                        $scope.userSecurity.user = response;
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
    /*на стр по пути не загружается фотка*/
    $scope.loadUserPhoto = function(idUser) {
        AppService.api(
            UserService.loadIdUserPhotoByIdUser(idUser),
            function(response) {
                $scope.userPhoto.id = response.id;
                $scope.userPhoto.name = "Last saved photo";
                $scope.userPhoto.path = "%URL_PREFIX_BACK%/backend/user/photo/get/" + response.id;
            },
            function(error) {
                console.log("User photo id is not load");
            }
        );
    };

    $scope.updateUserProfile = function() {
        AppService.api(
            UserService.updateUserById($scope.userSecurity.user),
            function(response) {
                $scope.loadUserProfile();
            },
            function(error) {
                console.log("Error load user list link");
            }
        );
    };
    /*на стр по пути не загружается фотка*/
    $scope.updateUserPhoto = function() {
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
                        $scope.userPhoto.path = "%URL_PREFIX_BACK%/backend/user/photo/get/" + response.id;
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
