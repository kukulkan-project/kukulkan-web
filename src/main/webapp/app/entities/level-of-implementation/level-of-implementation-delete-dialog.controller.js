(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('LevelOfImplementationDeleteController',LevelOfImplementationDeleteController);

    LevelOfImplementationDeleteController.$inject = ['$uibModalInstance', 'entity', 'LevelOfImplementation'];

    function LevelOfImplementationDeleteController($uibModalInstance, entity, LevelOfImplementation) {
        var vm = this;

        vm.levelOfImplementation = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            LevelOfImplementation.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
