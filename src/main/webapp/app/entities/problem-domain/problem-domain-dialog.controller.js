(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('ProblemDomainDialogController', ProblemDomainDialogController);

    ProblemDomainDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ProblemDomain'];

    function ProblemDomainDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ProblemDomain) {
        var vm = this;

        vm.problemDomain = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.problemDomain.id !== null) {
                ProblemDomain.update(vm.problemDomain, onSaveSuccess, onSaveError);
            } else {
                ProblemDomain.save(vm.problemDomain, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kukulkancraftsmanApp:problemDomainUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
