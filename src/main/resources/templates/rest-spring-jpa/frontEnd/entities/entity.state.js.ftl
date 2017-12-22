(function() {
    'use strict';

    angular
        .module('${projectName}App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('${entityCamelCase}', {
            parent: 'entity',
            url: '/${entityCamelCasePlural}?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: '${projectName}App.${entityCamelCase}.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/${entityHyphenNotation}/${entityHyphenNotationPlural}.html',
                    controller: '${entity}Controller',
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
                    $translatePartialLoader.addPart('${entityCamelCase}');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('${entityCamelCase}-detail', {
            parent: '${entityCamelCase}',
            url: '/${entityCamelCasePlural}/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: '${projectName}App.${entityCamelCase}.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/${entityHyphenNotation}/${entityHyphenNotation}-detail.html',
                    controller: '${entity}DetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('${entityCamelCase}');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', '${entity}', function($stateParams, ${entity}) {
                    return ${entity}.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || '${entityCamelCase}',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('${entityCamelCase}-detail.edit', {
            parent: '${entityCamelCase}-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/${entityHyphenNotation}/${entityHyphenNotation}-dialog.html',
                    controller: '${entity}DialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['${entity}', function(${entity}) {
                            return ${entity}.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('${entityCamelCase}.new', {
            parent: '${entityCamelCase}',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/${entityHyphenNotation}/${entityHyphenNotation}-dialog.html',
                    controller: '${entity}DialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                            	${primaryKey.name}: null,
                          	    <#list properties as property>
                                ${property.name}: null<#sep>, </#sep>
                    			</#list>
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('${entityCamelCase}', null, { reload: '${entityCamelCase}' });
                }, function() {
                    $state.go('${entityCamelCase}');
                });
            }]
        })
        .state('${entityCamelCase}.edit', {
            parent: '${entityCamelCase}',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/${entityHyphenNotation}/${entityHyphenNotation}-dialog.html',
                    controller: '${entity}DialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['${entity}', function(${entity}) {
                            return ${entity}.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('${entityCamelCase}', null, { reload: '${entityCamelCase}' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('${entityCamelCase}.delete', {
            parent: '${entityCamelCase}',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/${entityHyphenNotation}/${entityHyphenNotation}-delete-dialog.html',
                    controller: '${entity}DeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['${entity}', function(${entity}) {
                            return ${entity}.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('${entityCamelCase}', null, { reload: '${entityCamelCase}' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
