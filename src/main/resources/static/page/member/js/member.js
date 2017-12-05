var memberApp = angular.module('memberApp', []);

memberApp.controller('listCtrl', ['$scope', '$http', function($scope, $http){
    $scope.list = function(){
        $http.post(contextPath + '/member/list' + $scope.param).then(function(response){
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.memberList = data.data.list;
                $scope.memberCount = data.data.count;
            }
        });
    };
    $scope.init = function(){
        $scope.param = '?page=1&pageSize=20';
        $scope.list();
    };
    $scope.init();

}]);