var services = angular.module('LogSimulatorServices', ['ngResource']);

services.factory('LogManagerResource',function($resource){
	
	return $resource('log/:id',{id:"@id"},{
		listLogEntries:{method:'GET',params:{id:''},isArray:false},
		curved:{method:'GET',params:{id:'/curved/'},isArray:false},
		create:{method:'POST',params:{id:'create'},isArray:false},
		update:{method:'POST',params:{id:'update'},isArray:false},
		delete:{method:'POST',params:{id:'create'},isArray:false}
		
	});
	
});