"use strict";

var LinkController = function($scope, $state, $window, AppService, LinkService) {
    var angular = AppService.angular;

    $scope.requirements = {
        orderBy: "id",
        orderAsc: true,
        pageNum: 1,
        pageSize: 4
    };

    $scope.getFullLinkList = function() {
        AppService.api(
            LinkService.getFullLinkList($scope.requirements),
            function(response) {
                console.log("Loading product list : success");
                $scope.linkHistoryList = response.list;
            },
            function(error) {
                console.log("Loading full link list : error");
            }
        );
    };

    $scope.getFullLinkList();

    $scope.numberOfClick = {
        id: ""
    };

    $scope.increseClickNumber = function(idLink) {
        $scope.numberOfClick.id = idLink;
        AppService.api(
            LinkService.increseClickNumber($scope.numberOfClick),
            function(response) {
                console.log("Number of click increse : successfuly", response);
                $scope.getFullLinkList();
            },
            function(error) {
                console.log("Loading full link list : error");
            }
        );
    };

    $scope.reditectToSignup = function() {
        $state.go("signup");
    };

    $scope.reditectToLogin = function() {
        $state.go("login");
    };
};

module.exports = ["$scope", "$state", "$window", "AppService", "LinkService", LinkController];
