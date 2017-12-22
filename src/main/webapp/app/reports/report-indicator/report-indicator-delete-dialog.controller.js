(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('ReportIndicatorDeleteController',ReportIndicatorDeleteController);

    ReportIndicatorDeleteController.$inject = ['$uibModalInstance', 'entity', 'ReportIndicator'];

    function ReportIndicatorDeleteController($uibModalInstance, entity, ReportIndicator) {
        var vm = this;

        vm.reportIndicator = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ReportIndicator.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
