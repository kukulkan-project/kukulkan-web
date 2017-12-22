(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('ProblemDomainDeleteController',ProblemDomainDeleteController);

    ProblemDomainDeleteController.$inject = ['$uibModalInstance', 'entity', 'ProblemDomain'];

    function ProblemDomainDeleteController($uibModalInstance, entity, ProblemDomain) {
        var vm = this;

        vm.problemDomain = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ProblemDomain.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
