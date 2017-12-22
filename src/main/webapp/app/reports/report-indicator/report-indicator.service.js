(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('ReportIndicator', ReportIndicator);

    ReportIndicator.$inject = ['$resource'];

    function ReportIndicator ($resource) {
        var resourceUrl =  'api/report-indicators/:id';

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
