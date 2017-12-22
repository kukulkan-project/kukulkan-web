(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('GranularityDialogController', GranularityDialogController);

    GranularityDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Granularity'];

    function GranularityDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Granularity) {
        var vm = this;

        vm.granularity = entity;
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
            if (vm.granularity.id !== null) {
                Granularity.update(vm.granularity, onSaveSuccess, onSaveError);
            } else {
                Granularity.save(vm.granularity, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kukulkancraftsmanApp:granularityUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
