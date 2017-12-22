(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('DataPoolDeleteController',DataPoolDeleteController);

    DataPoolDeleteController.$inject = ['$uibModalInstance', 'entity', 'DataPool'];

    function DataPoolDeleteController($uibModalInstance, entity, DataPool) {
        var vm = this;

        vm.dataPool = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DataPool.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
