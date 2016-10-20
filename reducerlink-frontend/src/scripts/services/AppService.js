"use strict";

var AppService = function($document, EventDispatcherService) {
    global.jQuery = require("jquery");
    global.$ = jQuery;
    require("bootstrap");
    global.$$ = window.bootstrap;
    global.angular = window.angular;

    var admin = {};
    var eventDispatcher = EventDispatcherService.createInstance(admin);

    admin.EVENT_READY = "ready";

    admin.bootstrap = window.bootstrap;

    admin.angular = window.angular;

    admin.api = function(promise, onSuccess, onFailure) {
        promise.then(onSuccess, function(error) {
            if (typeof onFailure === "function" && onFailure(error)) {
                return;
            }
        });
    };

    $document.ready(function() {
        function onReady() {
            eventDispatcher.dispatchEvent(admin.EVENT_READY);
        }

        if (document.URL.indexOf("http://") === -1 && document.URL.indexOf("https://") === -1) {
            console.log("Using Cordova/PhoneGap setting");
            document.addEventListener("deviceready", onReady, false);
        } else {
            console.log("Using web browser setting");
            onReady();
        }
    });

    return admin;
};

module.exports = ["$document", "EventDispatcherService", AppService];
