(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('DataStoreDetailController', DataStoreDetailController);

    DataStoreDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DataStore', 'DataStoreConnection', 'DataStoreRunScript', 'DataStoreCreateSchema', 'DataStoreDropSchema'];

    function DataStoreDetailController($scope, $rootScope, $stateParams, previousState, entity, DataStore, DataStoreConnection, DataStoreRunScript, DataStoreCreateSchema, DataStoreDropSchema) {
        var vm = this;

        vm.dataStore = entity;
        vm.previousState = previousState.name;
        vm.action = action;
        vm.option = '';
		vm.editorOptions = {
				useWrapMode : true,
				showGutter: true,
				mode: 'mysql',
				theme:'twilight',
				firstLineNumber: 1,
				onLoad: vm.aceLoaded
			};
		vm.aceLoaded  = function (_editor) {
			// Options
		    _editor.setReadOnly(false);
		};

        vm.isTestingConnection = false;

        var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:dataStoreUpdate', function(event, result) {
            vm.dataStore = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        function action(option){
        	if(vm.option==='testConnection'){
        		DataStoreConnection.connect(vm.dataStore, onConnectionSuccess, onConnectionError);        		
        	}else if(vm.option==='runScript'){
        		DataStoreRunScript.execute(vm.dataStore, onConnectionSuccess, onConnectionError);
        	}else if(vm.option==='createSchema'){
        		DataStoreCreateSchema.execute(vm.dataStore, onConnectionSuccess, onConnectionError);
        	}else if(vm.option==='dropSchema'){
        		DataStoreDropSchema.execute(vm.dataStore, onConnectionSuccess, onConnectionError);
        	}
        }
        
        function onConnectionSuccess (result) {
            vm.isSaving = false;
        }
        
        function onConnectionError () {
            vm.isSaving = false;
        }
    }
})();
