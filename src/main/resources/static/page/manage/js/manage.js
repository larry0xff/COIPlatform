var manageApp = angular.module('manageApp', []);

manageApp.controller('switchListCtrl', ['$http', '$scope', function($http, $scope){
    function getList(){
        $http.get(contextPath + '/manage/switch/listA').then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.switchList = data.data;
            }
        });
    }
    $scope.changeStatus = function(igSwitchId, status){
        $http.get(contextPath + '/manage/switch/changeStatus?igSwitchId=' + igSwitchId + '&status=' + status).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                getList();
            }
        });
    };
    (function(){
        getList();
    })();
}]);