(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('ProblemDomainDetailController', ProblemDomainDetailController);

    ProblemDomainDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ProblemDomain'];

    function ProblemDomainDetailController($scope, $rootScope, $stateParams, previousState, entity, ProblemDomain) {
        var vm = this;

        vm.problemDomain = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('kukulkancraftsmanApp:problemDomainUpdate', function(event, result) {
            vm.problemDomain = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
