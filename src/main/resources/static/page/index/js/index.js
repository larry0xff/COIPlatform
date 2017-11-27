$(function (){
  $('.button-collapse').sideNav({
    menuWidth: 300, // Default is 240
    edge: 'left', // Choose the horizontal origin
    closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
    }
  );
});

var index = angular.module('index', []);

index.controller('indexCtrl',['$scope', function($scope){
	$scope.changeContent = function(url){
		$('#contentIframe').attr('src', url);
	};
	$scope.showNav = function(){
		$('.button-collapse').sideNav("show");
	}
}]);
