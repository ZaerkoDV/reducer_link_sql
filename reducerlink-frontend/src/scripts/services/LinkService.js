"use strict";

var LinkService = function($http) {
    var link = {};

    link.getFullLinkList = function(requirements) {
        return $http.post("/backend/link/all/getFullList", requirements);
    };

    link.increseClickNumber = function(idLink) {
        return $http.post("/backend/link/numberLinkVisits/increase", idLink);
    };

    link.uniqualTagList = function() {
        return $http.get("/backend/link/uniqual/getTagList");
    };

    link.searchLinkByTag = function(requirements) {
        return $http.post("/backend/link/byTag/getList", requirements);
    };

    return link;
};

module.exports = ["$http", LinkService];
