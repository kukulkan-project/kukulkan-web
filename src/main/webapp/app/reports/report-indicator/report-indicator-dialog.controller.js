(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('ReportIndicatorDialogController', ReportIndicatorDialogController);

    ReportIndicatorDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'ReportIndicator'];

    function ReportIndicatorDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, ReportIndicator) {
        var vm = this;

        vm.reportIndicator = entity;
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
            if (vm.reportIndicator.id !== null) {
                ReportIndicator.update(vm.reportIndicator, onSaveSuccess, onSaveError);
            } else {
                ReportIndicator.save(vm.reportIndicator, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kukulkancraftsmanApp:reportIndicatorUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
