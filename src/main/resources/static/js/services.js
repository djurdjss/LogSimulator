var services = angular.module('LogSimulatorServices', ['ngResource']);

services.factory('LogManagerResource',function($resource){
	
	return $resource('log/:action',{id:"@id"},{
		listLogEntries:{method:'GET',params:{action:''},isArray:false},
		create:{method:'POST',params:{action:'create'},isArray:false},
		update:{method:'POST',params:{action:'update'},isArray:false},
		delete:{method:'DELETE',params:{action:'delete',id:"@id"},isArray:false},
		simulate:{method:'POST',params:{action:'simulate'},isArray:false}
	});
	
});