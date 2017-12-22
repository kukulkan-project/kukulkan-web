(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('Phase', Phase);

    Phase.$inject = ['$resource'];

    function Phase ($resource) {
        var resourceUrl =  'api/phases/:id';

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
