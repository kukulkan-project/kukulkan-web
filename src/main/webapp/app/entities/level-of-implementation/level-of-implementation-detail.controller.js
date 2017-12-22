(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('LevelOfImplementationDetailController', LevelOfImplementationDetailController);

    LevelOfImplementationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LevelOfImplementation'];

    function LevelOfImplementationDetailController($scope, $rootScope, $stateParams, previousState, entity, LevelOfImplementation) {
        var vm = this;

        vm.levelOfImplementation = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:levelOfImplementationUpdate', function(event, result) {
            vm.levelOfImplementation = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
