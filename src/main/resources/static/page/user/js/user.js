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
        $scope.temp = user;
		if(id == '#editModal') {
			$('label').addClass('active');
		}
		if(id == '#rolesModal') {
            $scope.listRole();
		}
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
                Materialize.toast(data.msg, 2000);
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
                Materialize.toast(data.msg, 2000);
				$scope.hideModal('#delModal');
				$scope.getUserList();
			}
		});
	};
	$scope.listRole = function(){
        $http.get(contextPath + "/user/roles?igUserId=" + $scope.temp.igUserId).then(function(response) {
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
                return;
            }else{
                $scope.roles = response.data.data.set;
                $scope.rolesCount = response.data.data.count;
            }
        })
	};
	$scope.rmRole = function(igUserId, igRoleId){
        $http.post(contextPath + "/user/rmRole?igUserId=" + igUserId + "&igRoleId=" + igRoleId).then(function(response) {
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000);
                $scope.listRole();
            }
        });
	}
	$scope.init = function() {
		//拿数据
		$scope.getUserList();
	};
	$scope.init();
}]);