(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('ReportElementDeleteController',ReportElementDeleteController);

    ReportElementDeleteController.$inject = ['$uibModalInstance', 'entity', 'ReportElement'];

    function ReportElementDeleteController($uibModalInstance, entity, ReportElement) {
        var vm = this;

        vm.reportElement = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ReportElement.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
