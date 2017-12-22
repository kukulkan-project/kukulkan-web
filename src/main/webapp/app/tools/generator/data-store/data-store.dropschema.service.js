(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('DataStoreDropSchema', DataStoreDropSchema);

    DataStoreDropSchema.$inject = ['$resource'];

    function DataStoreDropSchema ($resource) {
        var resourceUrl =  'api/data-stores/drop-schema';

        return $resource(resourceUrl, {}, {
            'execute': { method:'POST'}
        });
    }
})();
