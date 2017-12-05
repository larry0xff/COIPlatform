var memberApp = angular.module('memberApp', []);

memberApp.controller('listCtrl', ['$scope', '$http', function($scope, $http){
    $scope.list = function(){
        $http.post(contextPath + '/member/list', $scope.param).then(function(response){
            var data = response.data;
            if(data.returnCode != 200){

            }
        });
    };
    $scope.init = function(){


    };

}]);