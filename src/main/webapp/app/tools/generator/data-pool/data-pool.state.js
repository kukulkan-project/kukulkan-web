(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('data-pool', {
            parent: 'generator',
            url: '/data-pool',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'kukulkancraftsmanApp.dataPool.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/tools/generator/data-pool/data-pools.html',
                    controller: 'DataPoolController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('dataPool');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('data-pool-detail', {
            parent: 'data-pool',
            url: '/data-pool/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'kukulkancraftsmanApp.dataPool.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/tools/generator/data-pool/data-pool-detail.html',
                    controller: 'DataPoolDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('dataPool');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'DataPool', function($stateParams, DataPool) {
                    return DataPool.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'data-pool',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('data-pool-detail.edit', {
            parent: 'data-pool-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/tools/generator/data-pool/data-pool-dialog.html',
                    controller: 'DataPoolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DataPool', function(DataPool) {
                            return DataPool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('data-pool.new', {
            parent: 'data-pool',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/tools/generator/data-pool/data-pool-dialog.html',
                    controller: 'DataPoolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('data-pool', null, { reload: 'data-pool' });
                }, function() {
                    $state.go('data-pool');
                });
            }]
        })
        .state('data-pool.edit', {
            parent: 'data-pool',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/tools/generator/data-pool/data-pool-dialog.html',
                    controller: 'DataPoolDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DataPool', function(DataPool) {
                            return DataPool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('data-pool', null, { reload: 'data-pool' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('data-pool.delete', {
            parent: 'data-pool',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/tools/generator/data-pool/data-pool-delete-dialog.html',
                    controller: 'DataPoolDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DataPool', function(DataPool) {
                            return DataPool.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('data-pool', null, { reload: 'data-pool' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
