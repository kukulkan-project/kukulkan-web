(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('LevelOfImplementation', LevelOfImplementation);

    LevelOfImplementation.$inject = ['$resource'];

    function LevelOfImplementation ($resource) {
        var resourceUrl =  'api/level-of-implementations/:id';

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
