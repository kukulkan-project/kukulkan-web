(function() {
	'use strict';

	angular.module('kukulkancraftsmanApp').controller(
			'ReportElementDetailController', ReportElementDetailController);

	ReportElementDetailController.$inject = [ '$scope', '$rootScope',
			'$stateParams', 'previousState', 'DataUtils', 'entity',
			'ReportElement' ];

	function ReportElementDetailController($scope, $rootScope, $stateParams,
			previousState, DataUtils, entity, ReportElement) {
		var vm = this;

		vm.reportElement = entity;
		vm.previousState = previousState.name;
		vm.byteSize = DataUtils.byteSize;
		vm.openFile = DataUtils.openFile;
		vm.searchIndicator = function (indicadorStatus, id) {
			var i = 0, len = indicadorStatus.length;
			for (; i < len; i++) {
				console.log(indicadorStatus[i].indicator.nombre)
				if (indicadorStatus[i].indicator.id == id) {
					return indicadorStatus[i].state.name;
				}
			}
			return "N/A";
		}

		var unsubscribe = $rootScope.$on(
				'kukulkancraftsmanApp:reportElementUpdate', function(event,
						result) {
					vm.reportElement = result;
				});
		$scope.$on('$destroy', unsubscribe);
	}
})();
