(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('FileDetailController', FileDetailController);

    FileDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'File', 'Asset'];

    function FileDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, File, Asset) {
        var vm = this;

        vm.file = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:fileUpdate', function(event, result) {
            vm.file = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
