(function() {
    'use strict';

    angular
        .module('myApp', [
            'ngAnimate',
            'ngAria',
            'ngMessages',
            'ngMaterial',
            'md.data.table',
            'ngFileUpload'
        ])
        .controller("appController", function($scope, $http, Upload, $mdDialog) {
            $scope.query = {
                order: 'id',
                limit: 5,
                page: 1
            };

            $scope.user = {};

            function getData() {
                $http.get("/exercise/rest/user")
                    .then(function(response) {
                        $scope.users = response.data;
                    }, function(error) {
                        console.log(error);
                    });
            }

            getData();

            $scope.deleteUser = function(ev, id) {
                // Appending dialog to document.body to cover sidenav in docs app
                var confirm = $mdDialog.confirm()
                    .title('Warning')
                    .textContent('Are you sure to delete this User')
                    .ariaLabel('Delete user')
                    .targetEvent(ev)
                    .ok('Please do it!')
                    .cancel('Cancel');

                $mdDialog.show(confirm).then(function() {
                    $http.delete("/exercise/rest/user/" + id)
                        .then(function(response) {
                            getData();
                        }, function(error) {
                            console.log(error);
                        });
                }, function() {

                });
            };

            $scope.clearInfo = function() {
                $scope.user = {};
                $scope.file = null;
            }

            $scope.submit = function() {
                if ($scope.form.file.$valid && $scope.file) {
                    $scope.upload($scope.file);
                }
            };

            $scope.upload = function(file) {
                Upload.upload({
                    url: '/exercise/rest/user',
                    data: {
                        file: file,
                        user: Upload.jsonBlob({
                            "username": $scope.user.name,
                            "password": "123456"
                        })
                    }
                }).then(function(resp) {
                    getData();
                    $scope.clearInfo();
                    console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
                }, function(resp) {
                    console.log('Error status: ' + resp.status);
                }, function(evt) {
                    var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                    console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
                });
            };

        });
})()