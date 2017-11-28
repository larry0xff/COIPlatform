$(document).ready(function() {
    $('select').material_select();
  });
var userApp = angular.module('user', []);

userApp.controller('userCtrl', ['$scope', '$http', function($scope, $http) {
	var pageIndex = 1;
	var pageSize = 20;
	$scope.getUserList = function() {
		data = {
			'pageIndex': pageIndex,
			'pageSize': pageSize
		};
		$http.post(contextPath + "/user/list?pageSize=" + pageSize + "&pageIndex=" + pageIndex).then(function(response) {
			var data = response.data;
			if(data.returnCode != 200){
				Materialize.toast(data.msg, 2000);
			}else{
				$scope.listData = response.data.data;
			}
		})
	};
	$scope.showModal = function(id, user) {
		if(id == '#editModal') {
			$('label').addClass('active');
		}
		$scope.temp = user;
		$(id).openModal();
	};
	$scope.hideModal = function(id) {
		delete $scope.temp;
		$(id).closeModal();
	};
	$scope.insert = function() {
		console.log($scope.insertForm);
		$http.post(contextPath + "/user/insert", $scope.insertForm).then(function(response) {
			var data = response.data;
			if(data.returnCode != 200){
				Materialize.toast(data.msg, 2000);
			}else{
				$scope.hideModal('#addModal');
				$scope.getUserList();
			}
		});
	};
	$scope.del = function(){
		$http.get(contextPath + "/user/delete?igUserId=" + $scope.temp.igUserId).then(function(response) {
			var data = response.data;
			if(data.returnCode != 200){
				Materialize.toast(data.msg, 2000);
			}else{
				$scope.hideModal('#delModal');
				$scope.getUserList();
			}
		});
	}
	$scope.init = function() {
		//拿数据
		$scope.getUserList();
	};
	$scope.init();
}]);