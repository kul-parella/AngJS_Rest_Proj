var myApp = angular.module('myApp', []);


myApp.controller('myAppController', function myAppController($scope, $http) {

	$scope.getData = function() {
	
		$http({
			method : "GET",
			url : "http://localhost:8080/AngJS_Rest_Proj/webapi/myresource"
		}).then(function mySuccess(response) {
			$scope.resp = response.data;
		}, function myError(response) {
			$scope.resp = response.statusText;
		});
		
	}

});