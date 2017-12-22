(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('Grammar', Grammar);

    Grammar.$inject = ['$resource'];

    function Grammar ($resource) {
        var resourceUrl =  'api/grammar/:id';

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
            'update': { method:'PUT' },
            'generateCode': {method:'POST'}
        });
    }
})();
