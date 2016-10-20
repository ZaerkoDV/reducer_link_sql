"use strict";

var LinkService = function($http) {
    var link = {};

    link.getFullLinkList = function(requirements) {
        return $http.post("/backend/link/all/getFullList", requirements);
    };

    link.increseClickNumber = function(idLink) {
        return $http.post("/backend/link/numberLinkVisits/increase", idLink);
    };

    return link;
};

module.exports = ["$http", LinkService];
