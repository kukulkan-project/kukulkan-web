(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('DataPoolDetailController', DataPoolDetailController);

    DataPoolDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DataPool'];

    function DataPoolDetailController($scope, $rootScope, $stateParams, previousState, entity, DataPool) {
        var vm = this;

        vm.dataPool = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dataPoolGeneratorApp:dataPoolUpdate', function(event, result) {
            vm.dataPool = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
