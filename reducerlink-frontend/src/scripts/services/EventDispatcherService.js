"use strict";

var EventDispatcherService = function() {
    function EventDispatcher() {
        var eventsMap = {};

        this.addEventListener = function(eventName, listener) {
            if (typeof eventsMap[eventName] === "undefined") {
                eventsMap[eventName] = [];
            }

            if (eventsMap[eventName].indexOf(listener) < 0) {
                eventsMap[eventName].push(listener);
            }
        };

        this.dispatchEvent = function(eventName, args) {
            args = args || [];
            var list = eventsMap[eventName];

            if (typeof list !== "undefined") {
                for (var i = 0, len = list.length; i < len; i++) {
                    list[i].apply(null, args);
                }
            }
        };
    }

    return {
        createInstance: function(admin) {
            var eventDispatcher = new EventDispatcher();
            admin.addEventListener = eventDispatcher.addEventListener;
            return eventDispatcher;
        }
    };
};

module.exports = [EventDispatcherService];
