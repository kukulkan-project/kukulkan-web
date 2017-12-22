(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('dsl-kukulkan-full', {
            parent: 'entity',
            url: '/dsl-kukulkan-fulls',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'kukulkancraftsmanApp.state.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dsl-kukulkan-full/dsl-kukulkan.html',
                    controller: 'DslController',
                    controllerAs: 'vm'
                }
            },            
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }],
                config: ['Config', function(Config) {
                    return Config.get().$promise;
                }]
            }
        });
    }
})();
