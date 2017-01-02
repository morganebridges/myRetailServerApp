'use strict';

// Declare app level module which depends on views, and components
angular.module('helpApp', [
    'ngRoute'

]).
config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
    $locationProvider.hashPrefix('!');
    $routeProvider.when('/help', {
        templateUrl : 'index.html',
        controller : 'helpApp'
    });
    $routeProvider.when('/config', {
        templateURL : 'OperationsView/config.html',
        controller : 'OperationsViewCTRL'
    });

    $routeProvider.when('/install', {
        templateURL : 'OperationsView/config.html',
        controller : 'OperationsdViewCTRL'
    });
    //$routeProvider.otherwise({redirectTo: 'index'});


}])
    .controller('helpAppCTRL', ['$scope', '$routeParams', '$http',
    function($scope, $routeParams, $http){
        $scope.editMode = false;
        $scope.products = [];
        $scope.selected = {};

        //form models
        $scope.name='';
        $scope.value='';
        $scope.currency_code='';
        console.log("Controller running");

        $http.get("http://52.39.83.97:8686/products/getlist")
            .then(
                function successCalback(response){

                    var products = [];
                    products = response.data;
                    console.log(products);
                    for(var i = 0; i < products.length; i++){
                        $scope.products[products[i].id - 1] = products[i];
                    }
                    console.log($scope.products);
                }, function errorCallback(response){
                    console.log(response);
                }
            );

        $scope.select = function(id){
            console.log(id);
            $scope.editMode = true;
            $scope.selected = $scope.products[id-1]
            $scope.name = $scope.selected.name;
            $scope.value = $scope.selected.price.value;
            $scope.currency_code = $scope.selected.price.currency_code;
        }
        $scope.save = function(){
            console.log($scope.selected);
            if($scope.name)
                $scope.selected.name = $scope.name;
            if($scope.value)
                $scope.selected.price.value = $scope.value;
            if($scope.currencyCode)
                $scope.selected.price.currency_code = $scope.currency_code;
            console.log($scope.selected);
            $scope.products[$scope.selected.id -1] = $scope.selected;
            $scope.editMode = false;
            $http.put("http://52.39.83.97:8686/products/update", $scope.selected)
            .then(function(response){
                console.log("Successful update");
                console.log(response);
            });
            $scope.selected = {};



        }
        $scope.cancel = function(){
            $scope.selected = {};
            $scope.editMode = false;
        }

    }]);

