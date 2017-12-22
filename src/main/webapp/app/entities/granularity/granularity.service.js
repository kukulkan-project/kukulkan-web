(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('Granularity', Granularity);

    Granularity.$inject = ['$resource'];

    function Granularity ($resource) {
        var resourceUrl =  'api/granularities/:id';

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
