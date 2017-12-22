(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('DataPoolController', DataPoolController);

    DataPoolController.$inject = ['DataPool'];

    function DataPoolController(DataPool) {

        var vm = this;

        vm.dataPools = [];

        loadAll();

        function loadAll() {
            DataPool.query(function(result) {
                vm.dataPools = result;
                vm.searchQuery = null;
            });
        }
    }
})();
