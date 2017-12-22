(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('DataStore', DataStore);

    DataStore.$inject = ['$resource'];

    function DataStore ($resource) {
        var resourceUrl =  'api/data-stores/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'getCatalog': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' },
            'testConnection': { method:'POST'}
        });
    }
})();
