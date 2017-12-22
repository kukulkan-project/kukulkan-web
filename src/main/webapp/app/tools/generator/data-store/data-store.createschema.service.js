(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('DataStoreCreateSchema', DataStoreCreateSchema);

    DataStoreCreateSchema.$inject = ['$resource'];

    function DataStoreCreateSchema ($resource) {
        var resourceUrl =  'api/data-stores/create-schema';

        return $resource(resourceUrl, {}, {
            'execute': { method:'POST'}
        });
    }
})();
