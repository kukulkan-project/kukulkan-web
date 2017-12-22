(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('ReportDialogController', ReportDialogController);

    ReportDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Report'];

    function ReportDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Report) {
        var vm = this;

        vm.report = entity;
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
            if (vm.report.id !== null) {
                Report.update(vm.report, onSaveSuccess, onSaveError);
            } else {
                Report.save(vm.report, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kukulkancraftsmanApp:reportUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
