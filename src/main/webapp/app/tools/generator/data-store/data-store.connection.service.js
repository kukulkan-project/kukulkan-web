(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('DataStoreConnection', DataStoreConnection);

    DataStoreConnection.$inject = ['$resource'];

    function DataStoreConnection ($resource) {
        var resourceUrl =  'api/data-stores/test';

        return $resource(resourceUrl, {}, {
            'connect': { method:'POST'}
        });
    }
})();
