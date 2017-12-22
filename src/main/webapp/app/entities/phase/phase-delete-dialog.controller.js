(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('PhaseDeleteController',PhaseDeleteController);

    PhaseDeleteController.$inject = ['$uibModalInstance', 'entity', 'Phase'];

    function PhaseDeleteController($uibModalInstance, entity, Phase) {
        var vm = this;

        vm.phase = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Phase.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
