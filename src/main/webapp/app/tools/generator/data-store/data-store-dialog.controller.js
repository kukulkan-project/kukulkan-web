(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('DataStoreDialogController', DataStoreDialogController);

    DataStoreDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DataStore', 'DataStoreConnection', 'AlertService'];

    function DataStoreDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DataStore, DataStoreConnection, AlertService) {
        var vm = this;

        vm.dataStore = entity;
        vm.clear = clear;
        vm.save = save;
        vm.isTestingConnection = false;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
	        if(vm.isTestingConnection===false){
	        	vm.isSaving = true;
	        	if (vm.dataStore.id !== null) {
	        		DataStore.update(vm.dataStore, onSaveSuccess, onSaveError);
	        	} else {
	        		DataStore.save(vm.dataStore, onSaveSuccess, onSaveError);
	        	}        	
	        }else{
	        	DataStoreConnection.connect(vm.dataStore, onConnectionSuccess, onConnectionError);
	        }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kukulkancraftsmanApp:dataStoreUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError (error) {
            vm.isSaving = false;
        }
        
        function onConnectionSuccess (result) {
            vm.isSaving = false;
        }
        
        function onConnectionError () {
            vm.isSaving = false;
        }
    }
})();
