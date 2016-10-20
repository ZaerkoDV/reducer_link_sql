"use strict";

var HttpInterceptor = function($q) {
    return {
        request: function(config) {
            if (config.url.startsWith("/views/")) {
                config.url = "%URL_PREFIX_FRONT%" + config.url;
            } else if (config.url.startsWith("/backend/")) {
                config.url = "%URL_PREFIX_BACK%" + config.url;
            }

            return config;
        },
        response: function(response) {
            if (response.config.url.startsWith("%URL_PREFIX_BACK%/backend/")) {
                if (response.data.status === "ok") {
                    return response.data.data;
                }

                return $q.reject(response.data);
            }

            return response;
        },
        responseError: function(rejection) {
            if (rejection.config.url.startsWith("%URL_PREFIX_BACK%/backend/")) {
                if (typeof rejection.data === "object" && rejection.data.status === "error") {
                    return $q.reject(rejection.data);
                }

                return $q.reject({
                    status: "error",
                    errorCode: "server",
                    data: rejection.data
                });
            }

            return rejection;
        }
    };
};

module.exports = ["$q", HttpInterceptor];
