(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('grammar', {
            parent: 'generator',
            url: '/grammar',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'global.menu.tools.grammar'
            },
            views: {
                'content@': {
                    templateUrl: 'app/tools/generator/grammar/grammar.html',
                    controller: 'GrammarController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('grammar');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
