(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('StateDetailController', StateDetailController);

    StateDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'State'];

    function StateDetailController($scope, $rootScope, $stateParams, previousState, entity, State) {
        var vm = this;

        vm.state = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:stateUpdate', function(event, result) {
            vm.state = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
