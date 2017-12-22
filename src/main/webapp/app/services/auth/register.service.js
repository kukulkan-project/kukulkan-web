(function () {
    'use strict';

    angular
        .module('kukulkancraftsmanApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
