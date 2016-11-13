var appModule = angular.module('LogSimulator', 
		['ngRoute','ngResource','LogSimulatorControllers','LogSimulatorServices']);

appModule.config(['$locationProvider', '$routeProvider',
  
  function config($locationProvider, $routeProvider) {
    $routeProvider.
      when('/home', {
    	controller:'HomeController',
        templateUrl: 'view/home.html',
        label : 'Home'        
      }).when('/setup', {
      	controller:'LogSetupController',
          templateUrl: 'view/setup.html',
          label : 'SetupData'               
      }).when('/logstatement/add', {
      	controller:'LogStatementController',
          templateUrl: 'view/add.html',
          label : 'Add Log Statement'               
      }).otherwise({
    	  redirectTo : "/home"
      });
  }
]);