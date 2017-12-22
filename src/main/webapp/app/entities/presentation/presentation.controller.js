(function() {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .controller('PresentationController', PresentationController);

    PresentationController.$inject = ['$scope', '$sce', 'Principal', 'LoginService', '$state', 'config'];

    function PresentationController ($scope, $sce, Principal, LoginService, $state, config) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.config = config;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        $scope.trustSrc = function(src) {
            return $sce.trustAsResourceUrl(src);
        }
        
        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }
    }
})();
