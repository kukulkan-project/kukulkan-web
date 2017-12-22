(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('level-of-implementation', {
            parent: 'entity',
            url: '/level-of-implementation?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'kukulkancraftsmanApp.levelOfImplementation.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/level-of-implementation/level-of-implementations.html',
                    controller: 'LevelOfImplementationController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('levelOfImplementation');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('level-of-implementation-detail', {
            parent: 'level-of-implementation',
            url: '/level-of-implementation/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'kukulkancraftsmanApp.levelOfImplementation.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/level-of-implementation/level-of-implementation-detail.html',
                    controller: 'LevelOfImplementationDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('levelOfImplementation');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'LevelOfImplementation', function($stateParams, LevelOfImplementation) {
                    return LevelOfImplementation.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'level-of-implementation',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('level-of-implementation-detail.edit', {
            parent: 'level-of-implementation-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/level-of-implementation/level-of-implementation-dialog.html',
                    controller: 'LevelOfImplementationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LevelOfImplementation', function(LevelOfImplementation) {
                            return LevelOfImplementation.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('level-of-implementation.new', {
            parent: 'level-of-implementation',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/level-of-implementation/level-of-implementation-dialog.html',
                    controller: 'LevelOfImplementationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                description: null,
                                briefDescription: null,
                                order: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('level-of-implementation', null, { reload: 'level-of-implementation' });
                }, function() {
                    $state.go('level-of-implementation');
                });
            }]
        })
        .state('level-of-implementation.edit', {
            parent: 'level-of-implementation',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/level-of-implementation/level-of-implementation-dialog.html',
                    controller: 'LevelOfImplementationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LevelOfImplementation', function(LevelOfImplementation) {
                            return LevelOfImplementation.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('level-of-implementation', null, { reload: 'level-of-implementation' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('level-of-implementation.delete', {
            parent: 'level-of-implementation',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/level-of-implementation/level-of-implementation-delete-dialog.html',
                    controller: 'LevelOfImplementationDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['LevelOfImplementation', function(LevelOfImplementation) {
                            return LevelOfImplementation.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('level-of-implementation', null, { reload: 'level-of-implementation' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
