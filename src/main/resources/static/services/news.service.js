(function () {
    'use strict';


    angular.module('app').factory('newService', newsService);
    newService.$inject = ['$http'];
    function newService{
        var service = {};

        service.loadNewsForUser = loadNewsForUser;
        return service;

        function loadNewsForUser(user){
            return
        }
    }

})();