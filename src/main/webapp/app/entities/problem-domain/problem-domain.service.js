(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('ProblemDomain', ProblemDomain);

    ProblemDomain.$inject = ['$resource'];

    function ProblemDomain ($resource) {
        var resourceUrl =  'api/problem-domains/:id';

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
