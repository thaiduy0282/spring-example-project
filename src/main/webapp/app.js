(function() {
  'use strict';

  angular
    .module('myApp', [
      'ngAnimate',
      'ngAria',
      'ngMessages',
      'ngMaterial',
      'md.data.table'
    ])
    .controller("appController", function($scope, $http) {
      $scope.query = {
        order: 'id',
        limit: 5,
        page: 1
      };

      function getData() {
        $http.get("/exercise/rest/user/listUser")
          .then(function(response) {
            $scope.users = response.data;
          }, function(error) {
            console.log(error);
          });
      }

      getData();

    });
})()