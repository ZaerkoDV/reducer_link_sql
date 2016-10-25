"use strict";

var LinkController = function($scope, $state, $window, AppService, LinkService) {
    var angular = AppService.angular;

    $scope.requirements = {
        orderBy: "id",
        orderAsc: true,
        pageNum: 1,
        pageSize: 4
    };
    /*use ng-init="getFullLinkList()" for call this function on home state*/
    $scope.getFullLinkList = function() {
        AppService.api(
            LinkService.getFullLinkList($scope.requirements),
            function(response) {
                console.log("Loading list : success");
                $scope.linkHistoryList = response.list;
            },
            function(error) {
                console.log("Loading full link list : error");
            }
        );
    };

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

    $scope.getListUniqualTag = function() {
        AppService.api(
            LinkService.uniqualTagList(),
            function(response) {
                $scope.uniqualTagList = response;
                console.log("Loading tag list is success");
            },
            function(error) {
                console.log("Loading tag list is failure");
            }
        );
    };

    $scope.tagListCriteria = {
        tag: "",
        orderBy: "id",
        orderAsc: true
    };
    /*use ng-init="getListLinkByTag()"  for call this function on link-list-serach state*/
    $scope.getListLinkByTag = function() {
        $scope.tagListCriteria.tag = $state.params.tag;
        AppService.api(
            LinkService.searchLinkByTag($scope.tagListCriteria),
            function(response) {
                $scope.listLinkWithTag = response;
                console.log("Loading list link by tag is successfuly");
            },
            function(error) {
                console.log("Loading list link by tag is failed");
            }
        );
    };

    $scope.authorInfo = function() {
    };

    $scope.reditectToLinkListSearch = function(tag) {
        $state.go("link-list-search", { tag: tag });
    };

    $scope.reditectToSignup = function() {
        $state.go("signup");
    };

    $scope.reditectToLogin = function() {
        $state.go("login");
    };

    $scope.reditectToAllLink = function() {
        $state.go("home");
    };
};

module.exports = ["$scope", "$state", "$window", "AppService", "LinkService", LinkController];
