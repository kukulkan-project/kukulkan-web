(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('DisciplineDetailController', DisciplineDetailController);

    DisciplineDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Discipline'];

    function DisciplineDetailController($scope, $rootScope, $stateParams, previousState, entity, Discipline) {
        var vm = this;

        vm.discipline = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:disciplineUpdate', function(event, result) {
            vm.discipline = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
