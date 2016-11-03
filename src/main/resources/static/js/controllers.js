var controllers = angular.module('LogSimulatorControllers', ['ngResource']);

controllers.controller('HomeController', [
                        '$scope','$location','LogManagerResource',
        function($scope,$location,ApplicationResource) {
           
           $scope.setupData = function(){
        	   console.log("In setup data");
        	   
        	   var LogManager = new LogManagerResource();
	        	
        	   LogManager.$listLogEntries(function(response){
	        		console.log(angular.toJson(response))
	        		console.log(angular.toJson(response.data));
	        		$location.path("/setup");
	        	},function(error){
	        		//do nothing for now - just a demo
	        	});
           };
}]);


controllers.controller('LogSetupController', [
                      '$scope','$location','LogManagerResource',
        function($scope,$location,ApplicationResource,ApplicationVisualizationService) {
        
	        init = function(){
	        	$scope.applications = {data : []};
	        	var Applications = new ApplicationResource();
	        	
	        	Applications.$queryApplications(function(response){
	        		console.log(angular.toJson(response));
	        		$scope.applications.data = response.data;
	        	},function(error){
	        		//do nothing for now - just a demo
	        	});
	        };
	        
	        $scope.displayApplication = function(selectedId){
	        	ApplicationVisualizationService.setSelectedApplicationId(selectedId);
	        	console.log("after the id is set" + ApplicationVisualizationService.getSelectedApplicationId());
	        	$location.path('/visualize');
	        	console.log("after call");
	        };
	        
	        $scope.displayApplicationGraphCurved = function(selectedId){
	        	ApplicationVisualizationService.setSelectedApplicationId(selectedId);
	        	console.log("after the id is set" + ApplicationVisualizationService.getSelectedApplicationId());
	        	$location.path('/visualize/curved');
	        	console.log("after call");
	        };
	        
	        $scope.displayApplicationGraphImage = function(selectedId){
	        	ApplicationVisualizationService.setSelectedApplicationId(selectedId);
	        	console.log("after the id is set" + ApplicationVisualizationService.getSelectedApplicationId());
	        	$location.path('/visualize/image');
	        	console.log("after call");
	        };
	        
	        init();
}]);
