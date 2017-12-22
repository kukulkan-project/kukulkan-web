(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('StateDeleteController',StateDeleteController);

    StateDeleteController.$inject = ['$uibModalInstance', 'entity', 'State'];

    function StateDeleteController($uibModalInstance, entity, State) {
        var vm = this;

        vm.state = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            State.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
