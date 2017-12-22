(function() {
    'use strict';

    angular
        .module('${projectName}App')
        .factory('${entity}Search', ${entity}Search);

    ${entity}Search.$inject = ['$resource'];

    function ${entity}Search($resource) {
        var resourceUrl =  'api/_search/${entityCamelCasePlural}/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
