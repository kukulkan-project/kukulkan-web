(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('FileDialogController', FileDialogController);

    FileDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'File', 'Asset'];

    function FileDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, File, Asset) {
        var vm = this;

        vm.file = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        vm.assets = Asset.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.file.id !== null) {
                File.update(vm.file, onSaveSuccess, onSaveError);
            } else {
                File.save(vm.file, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('kukulkancraftsmanApp:fileUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setFile = function ($file, file) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        file.file = base64Data;
                        file.fileContentType = $file.type;
                    });
                });
            }
        };

    }
})();
