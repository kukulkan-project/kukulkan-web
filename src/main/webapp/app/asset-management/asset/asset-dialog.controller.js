(function() {
	'use strict';

	angular.module('kukulkancraftsmanApp').controller('AssetDialogController',
			AssetDialogController);

	AssetDialogController.$inject = [ '$timeout', '$scope', '$stateParams',
			'$uibModalInstance', 'DataUtils', 'entity', 'Asset',
			'LevelOfImplementation', 'Granularity', 'ProblemDomain', 'Phase',
			'Discipline', 'State' ];

	function AssetDialogController($timeout, $scope, $stateParams,
			$uibModalInstance, DataUtils, entity, Asset, LevelOfImplementation,
			Granularity, ProblemDomain, Phase, Discipline, State) {
		var vm = this;

		vm.asset = entity;
		vm.clear = clear;
		vm.save = save;
		vm.levelofimplementations = LevelOfImplementation.query();
		vm.granularities = Granularity.query();
		vm.problemdomains = ProblemDomain.query();
		vm.phases = Phase.query();
		vm.disciplines = Discipline.query();
		vm.states = State.query();
		vm.byteSize = DataUtils.byteSize;
		vm.openFile = DataUtils.openFile;

		$timeout(function() {
			angular.element('.form-group:eq(1)>input').focus();
		});

		function clear() {
			$uibModalInstance.dismiss('cancel');
		}

		function save() {
			vm.isSaving = true;
			if (vm.asset.id !== null) {
				Asset.update(vm.asset, onSaveSuccess, onSaveError);
			} else {
				Asset.save(vm.asset, onSaveSuccess, onSaveError);
			}
		}

		function onSaveSuccess(result) {
			$scope.$emit('kukulkancraftsmanApp:assetUpdate', result);
			$uibModalInstance.close(result);
			vm.isSaving = false;
		}

		function onSaveError() {
			vm.isSaving = false;
		}

		vm.setFile = function($file, asset) {
			if ($file) {
				DataUtils.toBase64($file, function(base64Data) {
					$scope.$apply(function() {
						asset.file = base64Data;
						asset.fileContentType = $file.type;
					});
				});
			}
		};
	}
})();
