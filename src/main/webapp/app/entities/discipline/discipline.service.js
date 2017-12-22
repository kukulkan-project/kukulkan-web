(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('Discipline', Discipline);

    Discipline.$inject = ['$resource'];

    function Discipline ($resource) {
        var resourceUrl =  'api/disciplines/:id';

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
