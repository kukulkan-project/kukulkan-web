(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('problem-domain', {
            parent: 'entity',
            url: '/problem-domain?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'kukulkancraftsmanApp.problemDomain.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/problem-domain/problem-domains.html',
                    controller: 'ProblemDomainController',
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
                    $translatePartialLoader.addPart('problemDomain');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('problem-domain-detail', {
            parent: 'problem-domain',
            url: '/problem-domain/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'kukulkancraftsmanApp.problemDomain.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/problem-domain/problem-domain-detail.html',
                    controller: 'ProblemDomainDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('problemDomain');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ProblemDomain', function($stateParams, ProblemDomain) {
                    return ProblemDomain.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'problem-domain',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('problem-domain-detail.edit', {
            parent: 'problem-domain-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/problem-domain/problem-domain-dialog.html',
                    controller: 'ProblemDomainDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProblemDomain', function(ProblemDomain) {
                            return ProblemDomain.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('problem-domain.new', {
            parent: 'problem-domain',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/problem-domain/problem-domain-dialog.html',
                    controller: 'ProblemDomainDialogController',
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
                    $state.go('problem-domain', null, { reload: 'problem-domain' });
                }, function() {
                    $state.go('problem-domain');
                });
            }]
        })
        .state('problem-domain.edit', {
            parent: 'problem-domain',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/problem-domain/problem-domain-dialog.html',
                    controller: 'ProblemDomainDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProblemDomain', function(ProblemDomain) {
                            return ProblemDomain.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('problem-domain', null, { reload: 'problem-domain' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('problem-domain.delete', {
            parent: 'problem-domain',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/problem-domain/problem-domain-delete-dialog.html',
                    controller: 'ProblemDomainDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ProblemDomain', function(ProblemDomain) {
                            return ProblemDomain.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('problem-domain', null, { reload: 'problem-domain' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
