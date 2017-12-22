(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('GrammarController', GrammarController);

    GrammarController.$inject = ['$scope', '$rootScope', 'Principal', 'Auth', 'JhiLanguageService', '$translate', 'Grammar', 'DataUtils'];

    function GrammarController ($scope, $rootScope, Principal, Auth, JhiLanguageService, $translate, Grammar, DataUtils) {
        var vm = this;

        vm.action = action;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.code ="entity Usuario (usuarios){\n"+
            "\tnombre String required,\n"+
            "\tedad Integer,\n"+
            "\tnumeroCredencial Long,\n"+
            "\tfechaLocalDate LocalDate,\n"+
            "\tfechaZoneDateTime ZonedDateTime,\n"+
            "\timagen Blob,\n"+
            "\tdesc TextBlob\n"+
            "}";
        vm.console ="kukulkan >\n";
        vm.aceLoaded  = function (_editor) {
        	// Options
        	_editor.setReadOnly(false);
        	_editor.setFontSize(20);
        };
        vm.consoleLoaded  = function (_editor) {
        	// Options
        	_editor.setOptions({
        	    readOnly : true,
        	    fontSize: 20
        	})
        };
        vm.editorOptions = {
    			useWrapMode : true,
    			showGutter: true,
    			mode: 'java',
    			firstLineNumber: 1,
    			onLoad: vm.aceLoaded
    			};
        vm.consoleOptions = {
    			useWrapMode : true,
    			showGutter: true,
    			mode: 'java',
    			theme:'twilight',
    			firstLineNumber: 1,
    			onLoad: vm.consoleLoaded
    			};
        

    	var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:grammarUpdate', function(event, result) {
    			vm.generatedDto = result;
          	});
    	
        $scope.$on('$destroy', unsubscribe);
        
        function action(option){
        	vm.console = vm.console+">Generating app...\n";
        	vm.console = vm.console+">Connection to the server...\n";
        	Grammar.generateCode(vm.code, onConnectionSuccess, onConnectionError);
        	vm.console = vm.console+">App Generation in process, please wait...\n";
        }
        
        function onConnectionSuccess (result) {
        	vm.console = vm.console+">Success, app generated :)\n";
        	$scope.$emit('kukulkancraftsmanApp:grammarUpdate', result);
        }
        
        function onConnectionError () {
        	vm.console = vm.console+">Error, please check the console:(\n";
        }
    }
})();
