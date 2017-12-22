(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('ReportElementDialogController', ReportElementDialogController);

    ReportElementDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'ReportElement'];

    function ReportElementDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, ReportElement) {
        var vm = this;

        vm.reportElement = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.reportElement.id !== null) {
                ReportElement.update(vm.reportElement, onSaveSuccess, onSaveError);
            } else {
                ReportElement.save(vm.reportElement, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kukulkancraftsmanApp:reportElementUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
