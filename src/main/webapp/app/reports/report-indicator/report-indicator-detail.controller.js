(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('ReportIndicatorDetailController', ReportIndicatorDetailController);

    ReportIndicatorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'ReportIndicator'];

    function ReportIndicatorDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, ReportIndicator) {
        var vm = this;

        vm.reportIndicator = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:reportIndicatorUpdate', function(event, result) {
            vm.reportIndicator = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
