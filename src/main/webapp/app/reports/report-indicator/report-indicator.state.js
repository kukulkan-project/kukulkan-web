(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('report-indicator', {
            parent: 'report',
            url: '/report-indicator?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'kukulkancraftsmanApp.reportIndicator.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/reports/report-indicator/report-indicators.html',
                    controller: 'ReportIndicatorController',
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
                    $translatePartialLoader.addPart('reportIndicator');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('report-indicator-detail', {
            parent: 'report-indicator',
            url: '/report-indicator/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'kukulkancraftsmanApp.reportIndicator.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/reports/report-indicator/report-indicator-detail.html',
                    controller: 'ReportIndicatorDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('reportIndicator');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ReportIndicator', function($stateParams, ReportIndicator) {
                    return ReportIndicator.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'report-indicator',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('report-indicator-detail.edit', {
            parent: 'report-indicator-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/reports/report-indicator/report-indicator-dialog.html',
                    controller: 'ReportIndicatorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ReportIndicator', function(ReportIndicator) {
                            return ReportIndicator.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('report-indicator.new', {
            parent: 'report-indicator',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/reports/report-indicator/report-indicator-dialog.html',
                    controller: 'ReportIndicatorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nombre: null,
                                briefDescription: null,
                                description: null,
                                order: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('report-indicator', null, { reload: 'report-indicator' });
                }, function() {
                    $state.go('report-indicator');
                });
            }]
        })
        .state('report-indicator.edit', {
            parent: 'report-indicator',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/reports/report-indicator/report-indicator-dialog.html',
                    controller: 'ReportIndicatorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ReportIndicator', function(ReportIndicator) {
                            return ReportIndicator.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('report-indicator', null, { reload: 'report-indicator' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('report-indicator.delete', {
            parent: 'report-indicator',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/reports/report-indicator/report-indicator-delete-dialog.html',
                    controller: 'ReportIndicatorDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ReportIndicator', function(ReportIndicator) {
                            return ReportIndicator.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('report-indicator', null, { reload: 'report-indicator' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
