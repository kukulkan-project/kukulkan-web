(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('GranularityDeleteController',GranularityDeleteController);

    GranularityDeleteController.$inject = ['$uibModalInstance', 'entity', 'Granularity'];

    function GranularityDeleteController($uibModalInstance, entity, Granularity) {
        var vm = this;

        vm.granularity = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Granularity.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
