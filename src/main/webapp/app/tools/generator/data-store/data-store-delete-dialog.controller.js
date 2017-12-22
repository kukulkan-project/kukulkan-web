(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('DataStoreDeleteController',DataStoreDeleteController);

    DataStoreDeleteController.$inject = ['$uibModalInstance', 'entity', 'DataStore'];

    function DataStoreDeleteController($uibModalInstance, entity, DataStore) {
        var vm = this;

        vm.dataStore = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DataStore.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
