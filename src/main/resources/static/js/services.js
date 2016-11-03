var services = angular.module('LogSimulatorServices', ['ngResource']);

services.factory('ApplicationResource',function($resource){
	
	return $resource('application/:id',{id:"@id"},{
		queryApplications:{method:'GET',params:{id:''},isArray:false},
		curved:{method:'GET',params:{id:'/curved/'},isArray:false},
		create:{method:'POST',params:{id:'create'},isArray:false}
	});
	
});

services.factory('GraphResource',function($resource){
	
	return $resource('graph/:id',{id:"@id"},{
	});
	
});

services.factory('GraphImageResource',function($resource){
	
	return $resource('image/:id',{id:"@id"},{
	});
	
});


services.factory('ApplicationVisualizationService',function(ApplicationResource,GraphResource,GraphImageResource){
	var selectedApplicationId = 0;
	var res = {data:''};
	
	return{
		setSelectedApplicationId:function(id){
			this.selectedApplicationId = id;
		},
		getSelectedApplicationId:function(){
			return this.selectedApplicationId;
		},
		getSelectedApplicationVisualizationData:function(callback){
			ApplicationResource.get({id:this.selectedApplicationId},function(response){
        		console.log(angular.toJson(response));
        		res.data = response.data;
        		callback(response.data);
        	},function(error){
        		//do nothing for now - just a demo
        	});
			return res;
		},
		getSelectedApplicationVisualizationDataCurved:function(callback){
			GraphResource.get({id:this.selectedApplicationId},function(response){
        		console.log(angular.toJson(response));
        		res.data = response.data;
        		callback(response.data);
        	},function(error){
        		//do nothing for now - just a demo
        	});
			return res;
		},
		getSelectedApplicationVisualizationDataCurvedWithImageNodes:function(callback){
			GraphImageResource.get({id:this.selectedApplicationId},function(response){
        		console.log(angular.toJson(response));
        		res.data = response.data;
        		callback(response.data);
        	},function(error){
        		//do nothing for now - just a demo
        	});
			return res;
		}
	};
	
});