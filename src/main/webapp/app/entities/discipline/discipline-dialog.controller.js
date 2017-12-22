(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('DisciplineDialogController', DisciplineDialogController);

    DisciplineDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Discipline'];

    function DisciplineDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Discipline) {
        var vm = this;

        vm.discipline = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.discipline.id !== null) {
                Discipline.update(vm.discipline, onSaveSuccess, onSaveError);
            } else {
                Discipline.save(vm.discipline, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kukulkancraftsmanApp:disciplineUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
