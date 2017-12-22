(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('ProjectDetailController', ProjectDetailController);

    ProjectDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Project', 'ProjectGenerator'];

    function ProjectDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Project, ProjectGenerator) {
        var vm = this;

        vm.project = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.generate = generate;
        
        var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:projectUpdate', function(event, result) {
            vm.project = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        function generate(){
        	ProjectGenerator.execute(vm.project, onConnectionSuccess, onConnectionError);
        }
        
        function onConnectionSuccess (result) {
            $scope.$emit('kukulkancraftsmanApp:projectUpdate', result);
            vm.isSaving = false;
        }
        
        function onConnectionError () {
            vm.isSaving = false;
        }
    }
})();
