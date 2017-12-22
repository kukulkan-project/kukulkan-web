(function () {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('DataPool', DataPool);

    DataPool.$inject = ['$resource'];

    function DataPool($resource) {
        var resourceUrl = 'api/data-pools/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true },
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method: 'PUT' }
        });
    }
})();
