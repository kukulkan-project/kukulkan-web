(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('DataStoreRunScript', DataStoreRunScript);

    DataStoreRunScript.$inject = ['$resource'];
    function DataStoreRunScript ($resource) {
    	
        var resourceUrl =  'api/data-stores/run-script';

        return $resource(resourceUrl, {}, {
            'execute': { method:'POST'}
        });
    }
})();
