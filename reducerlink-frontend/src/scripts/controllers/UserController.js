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
            firstName: "",
            lastName: "",
            middleName: "",
            birth: 1477048727,
            status: "active"
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
                $scope.getUserLinkByToken();
            },
            function(error) {
                console.log("Error login");
            }
        );
    };

    $scope.getUserLinkByToken = function() {
        AppService.api(
            UserService.getUserIdByToken($scope.token),
            function(response) {
                $scope.id = response;
                AppService.api(
		            UserService.getAllUserLink($scope.id),
		            function(response) {
		                $scope.userLinkList = response.list;
		            },
		            function(error) {
		                console.log("Error load user list link");
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
