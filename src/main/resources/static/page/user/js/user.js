var userApp = angular.module('user', []);

userApp.controller('userCtrl', ['$scope','$http', function($scope,$http){
	
	var pageIndex = 1;
	var pageSize = 20;
	$scope.getUserList = function(){
		data = {'pageIndex':pageIndex,'pageSize':pageSize};
		$http.post(contextPath+"/user/list?pageSize=" + pageSize + "&pageIndex=" + pageIndex).then(function(response){
			$scope.listData = response.data.data;
		})
	}
	
	$scope.getUserList();
}]);
