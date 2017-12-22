(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('FileController', FileController);

    FileController.$inject = ['DataUtils', 'File'];

    function FileController(DataUtils, File) {

        var vm = this;

        vm.files = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            File.query(function(result) {
                vm.files = result;
                vm.searchQuery = null;
            });
        }
    }
})();
