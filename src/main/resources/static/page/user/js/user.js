
  
var userApp = angular.module('user', []);

userApp.controller('userCtrl', ['$scope','$http', function($scope,$http){
	var pageIndex = 1;
	var pageSize = 20;
	$scope.getUserList = function(){
		data = {'pageIndex':pageIndex,'pageSize':pageSize};
		$http.post(contextPath+"/user/list?pageSize=" + pageSize + "&pageIndex=" + pageIndex).then(function(response){
			$scope.listData = response.data.data;
		})
	};
	$scope.showModal = function(id, user){
		if(id == '#editModal'){
			$('label').addClass('active');
		}
		$scope.temp = user;
		$(id).openModal();
	};
	$scope.hideModal = function(id){
		delete $scope.temp;
		$(id).closeModal();
	};
	$scope.insert = function(){
		console.log($scope.insertForm);
        $http.post(contextPath+"/user/insert",$scope.insertForm).then(function(response){
        	alert(response.data.returnCode);
        });
	};
	$scope.init =  function(){
		//拿数据
		$scope.getUserList();
		//初始化select
    $('select').material_select();
	};
	$scope.init();
}]);
