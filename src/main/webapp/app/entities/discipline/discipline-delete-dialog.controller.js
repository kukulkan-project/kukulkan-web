(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('DisciplineDeleteController',DisciplineDeleteController);

    DisciplineDeleteController.$inject = ['$uibModalInstance', 'entity', 'Discipline'];

    function DisciplineDeleteController($uibModalInstance, entity, Discipline) {
        var vm = this;

        vm.discipline = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Discipline.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
