(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('State', State);

    State.$inject = ['$resource'];

    function State ($resource) {
        var resourceUrl =  'api/states/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
