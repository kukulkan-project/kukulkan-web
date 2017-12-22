(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('ProjectDialogController', ProjectDialogController);

    ProjectDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Project', 'DataStore'];

    function ProjectDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Project, DataStore) {
        var vm = this;

        vm.project = entity;
        vm.clear = clear;
        vm.save = save;
        
        DataStore.getCatalog({}, onSuccessCatalog, onError);
        
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.project.id !== null) {
                Project.update(vm.project, onSaveSuccess, onSaveError);
            } else {
                Project.save(vm.project, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kukulkancraftsmanApp:projectUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }
        function onSuccessCatalog(data, headers) {
        	 vm.dataStores = data;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
        function onError(error) {
        	AlertService.error(error.data.message);
        }


    }
})();
