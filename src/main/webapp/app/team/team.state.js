(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('team', {
            parent: 'app',
            url: '/team',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/team/team.html',
                    controller: 'TeamController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('team');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
