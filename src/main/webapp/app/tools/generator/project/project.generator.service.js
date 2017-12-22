(function() {
    'use strict';
    angular
        .module('kukulkancraftsmanApp')
        .factory('ProjectGenerator', ProjectGenerator);

    ProjectGenerator.$inject = ['$resource'];

    function ProjectGenerator ($resource) {
        var resourceUrl =  'api/projects/generate';

        return $resource(resourceUrl, {}, {
            'execute': { method:'POST' }
        });
    }
})();
