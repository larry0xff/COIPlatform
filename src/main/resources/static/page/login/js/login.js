var loginApp = angular.module('loginApp', []);

loginApp.controller('loginCtrl', ['$scope', '$http','$location', function($scope, $http, $location){
	
	$scope.login = function(){
		var param = {
			username: $scope.ctx.username,
			password: $scope.ctx.password
		}
		console.log(param);
		$http.post(contextPath + '/login',param).then(function(result){
			var data = result.data;
			if(data.returnCode != 10000){
				Materialize.toast(data.msg, 2000);
			}else{
				Materialize.toast(data.msg, 2000);
				window.location = contextPath + '/page/index/index.html';
			}
			
		})
	}
}]);
