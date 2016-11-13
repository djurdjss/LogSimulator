var controllers = angular.module('LogSimulatorControllers', ['ngResource']);

controllers.controller('HomeController', [
                        '$scope','$location','LogManagerResource',
        function($scope,$location,LogManagerResource) {
           
           $scope.setupData = function(){
        	   console.log("In setup data");
        	   $location.path("/setup");
           };
}]);


controllers.controller('LogSetupController', [
                      '$scope','$location','LogManagerResource',
        function($scope,$location,LogManagerResource) {
        
	        init = function(){
	        	$scope.logStatements = {data : []};
	        	var LogManager = new LogManagerResource();
	        	
	        	LogManager.$listLogEntries(function(response){
	        		console.log(angular.toJson(response));
	        		$scope.logStatements.data = response.data;
	        	},function(error){
	        		//do nothing for now - just a demo
	        	});
	        };
	        
	        $scope.deleteLogStatement = function(selectedId){
	        	var LogManager = LogManagerResource.get({id:selectedId});
	        	console.log(selectedId);
	        	console.log(angular.toJson(LogManager));
	        	LogManager.$delete({},function(response){
	        		console.log(angular.toJson(response));
	        		
	        		console.log($scope.res);
	        	},function(error){
	        		//do nothing for now - just a demo
	        	});
	        	$location.path("/setup");
	        };
	        
	        $scope.addLogStatement = function(){
	        	$location.path('/logstatement/add');
	        };
	        
	        $scope.viewLogStatement = function(selectedId){
	        	 
	        };
	        
	        $scope.editLogStatement = function(selectedId){
	        	
	        };
	        
	        $scope.simulateLogs = function(){
	        	var LogManager = new LogManagerResource();
	        	LogManager.$simulate({},function(response){
	        		console.log(angular.toJson(response));
	        		
	        		console.log($scope.res);
	        	},function(error){
	        		//do nothing for now - just a demo
	        	});
	        };
	        
	        init();
}]);



controllers.controller('LogStatementController', [
                      '$scope','$location','LogManagerResource',
        function($scope,$location,LogManagerResource) {
        
	        init = function(){
	        	$scope.logEntryRequest = {};
	        	$scope.res = {};
	        };
	       
	        $scope.cancel = function(){
	        	 $location.path("/setup");
	        };
	        
	        $scope.createLogStatement = function(){
	        	var LogManager = new LogManagerResource($scope.logEntryRequest);
	        	LogManager.$create({},function(response){
	        		console.log(angular.toJson(response));
	        		$scope.res.data = response.data;
	        		console.log($scope.res);
	        		$location.path("/setup");
	        	},function(error){
	        		//do nothing for now - just a demo
	        	});
	        };
	        
	        
	        init();
}]);
