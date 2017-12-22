(function() {
    'use strict';

    angular
        .module('${projectName}App')
        .controller('${entity}DetailController', ${entity}DetailController);

    ${entity}DetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', '${entity}'];

    function ${entity}DetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, ${entity}) {
        var vm = this;

        vm.${entityCamelCase}= entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('${projectName}App:${entityCamelCase}Update', function(event, result) {
            vm.${entityCamelCase}= result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
