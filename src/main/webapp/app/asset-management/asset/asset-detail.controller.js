(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('AssetDetailController', AssetDetailController);

    AssetDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Asset', 'LevelOfImplementation', 'Granularity', 'ProblemDomain', 'Phase', 'Discipline', 'State'];

    function AssetDetailController($scope, $rootScope, $stateParams, previousState, entity, Asset, LevelOfImplementation, Granularity, ProblemDomain, Phase, Discipline, State) {
        var vm = this;

        vm.asset = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:assetUpdate', function(event, result) {
            vm.asset = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
