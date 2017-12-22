(function () {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .factory('Config', Config);

    Config.$inject = ['$resource'];

    function Config ($resource) {
        var service = $resource('api/config/externalApps', {}, {
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            }
        });

        return service;
    }
})();
