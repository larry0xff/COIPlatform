var orgApp = angular.module('orgApp', []);
orgApp.controller('orgCtrl', ['$scope', '$http', function($scope, $http){
    //一些基础的方法
    $scope.showModal = function(id, user) {
        if(id == '#updateModal') {
            $('label').addClass('active');
        }
        $scope.temp = user;
        $(id).openModal();
    };
    $scope.hideModal = function(id) {
        delete $scope.temp;
        $(id).closeModal();
        $scope.getOrgList();
    };
    //请求方法
    $scope.getOrgList = function(){
        $http.get(contextPath + "/org/list").then(function(response){
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.orgList = data.data.list;
                $scope.orgCount = data.data.count;
            }
        });
    };
    $scope.insert = function(){
        $http.post(contextPath + '/org/save', $scope.insertForm).then(function(response){
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000, 'rounded');
                $scope.hideModal('#addModal');
            }
        });
    };
    $scope.delete = function(){
        $http.get(contextPath + "/org/delete?igOrgId=" + $scope.temp.igOrgId).then(function(response){
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000, 'rounded');
                $scope.hideModal('#delModal');
            }
        });
    };
    $scope.update = function(){
        $http.post(contextPath + '/org/update', $scope.temp).then(function(response){
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000, 'rounded');
                $scope.hideModal('#updateModal');
            }
        });
    };
    //初始化方法
    $scope.init = function(){
        $scope.getOrgList();
    };
    $scope.init();
}]);