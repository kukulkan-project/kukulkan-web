(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('GranularityDetailController', GranularityDetailController);

    GranularityDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Granularity'];

    function GranularityDetailController($scope, $rootScope, $stateParams, previousState, entity, Granularity) {
        var vm = this;

        vm.granularity = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:granularityUpdate', function(event, result) {
            vm.granularity = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
