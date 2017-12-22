(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('chat-bot', {
            parent: 'entity',
            url: '/chat-bot',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'kukulkancraftsmanApp.state.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/chat-bot/chat-bot.html',
                    controller: 'ChatBotController',
                    controllerAs: 'vm'
                }
            },            
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        });
    }

})();
