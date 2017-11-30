$(function (){
  $('.button-collapse').sideNav({
    menuWidth: 300, // Default is 240
    edge: 'left', // Choose the horizontal origin
    closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
    }
  );
});

var index = angular.module('index', []);

index.controller('indexCtrl',['$scope','$http', function($scope, $http){
	$scope.changeContent = function(url){
		$('#contentIframe').attr('src', url);
	};
	$scope.showNav = function(){
		$('.button-collapse').sideNav("show");
	};
	$scope.logout = function(){
		window.location = contextPath + '/logout';
	};
	$scope.showModal = function(id){
        $(id).openModal();
	};
	$scope.changePsw = function(){
		if($scope.changeForm.newPsw != $scope.changeForm.newPswRepeat){
            Materialize.toast('新密码不一致！', 2000);
            return;
		}
		$http.post(contextPath + '/user/updatePsw?oldPsw=' + $scope.changeForm.oldPsw + "&newPsw=" + $scope.changeForm.newPsw ).then(function(response){
			var data = response.data;
			if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000, 'rounded');
                delete $scope.changeForm;
                $('#updatePsw').closeModal();
			}
		});
	};
	
}]);
