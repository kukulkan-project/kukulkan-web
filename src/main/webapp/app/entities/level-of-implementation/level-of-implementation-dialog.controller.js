(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('LevelOfImplementationDialogController', LevelOfImplementationDialogController);

    LevelOfImplementationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'LevelOfImplementation'];

    function LevelOfImplementationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, LevelOfImplementation) {
        var vm = this;

        vm.levelOfImplementation = entity;
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
            if (vm.levelOfImplementation.id !== null) {
                LevelOfImplementation.update(vm.levelOfImplementation, onSaveSuccess, onSaveError);
            } else {
                LevelOfImplementation.save(vm.levelOfImplementation, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kukulkancraftsmanApp:levelOfImplementationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
