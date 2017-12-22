(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('data-store', {
            parent: 'generator',
            url: '/data-store?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'kukulkancraftsmanApp.dataStore.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/tools/generator/data-store/data-stores.html',
                    controller: 'DataStoreController',
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
                    $translatePartialLoader.addPart('dataStore');
                    $translatePartialLoader.addPart('tableTypes');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('data-store-detail', {
            parent: 'data-store',
            url: '/data-store/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'kukulkancraftsmanApp.dataStore.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/tools/generator/data-store/data-store-detail.html',
                    controller: 'DataStoreDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('dataStore');
                    $translatePartialLoader.addPart('tableTypes');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'DataStore', function($stateParams, DataStore) {
                    return DataStore.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'data-store',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('data-store-detail.edit', {
            parent: 'data-store-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/tools/generator/data-store/data-store-dialog.html',
                    controller: 'DataStoreDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DataStore', function(DataStore) {
                            return DataStore.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('data-store.new', {
            parent: 'data-store',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/tools/generator/data-store/data-store-dialog.html',
                    controller: 'DataStoreDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                url: null,
                                driverClass: null,
                                schema: null,
                                username: null,
                                password: null,
                                tableTypes: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('data-store', null, { reload: 'data-store' });
                }, function() {
                    $state.go('data-store');
                });
            }]
        })
        .state('data-store.edit', {
            parent: 'data-store',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/tools/generator/data-store/data-store-dialog.html',
                    controller: 'DataStoreDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DataStore', function(DataStore) {
                            return DataStore.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('data-store', null, { reload: 'data-store' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('data-store.delete', {
            parent: 'data-store',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/tools/generator/data-store/data-store-delete-dialog.html',
                    controller: 'DataStoreDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DataStore', function(DataStore) {
                            return DataStore.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('data-store', null, { reload: 'data-store' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
