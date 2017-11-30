var roleApp = angular.module('roleApp',[]);
roleApp.controller('roleCtrl',['$scope', '$http', function($scope, $http){
    $scope.showModal = function(id, user) {
        $scope.temp = user;
        if(id == '#permissionsModal') {
            $scope.listPermission();
        }
        $(id).openModal();
    };
    $scope.hideModal = function(id) {
        delete $scope.temp;
        $(id).closeModal();
        $scope.getRolesList();
    };
    $scope.getRolesList = function(){
        $http.get(contextPath + "/role/list").then(function(response){
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.roleList = data.data.list;
            }
        });
    };
    $scope.insert = function() {
        console.log($scope.insertForm);
        $http.post(contextPath + "/role/insert", $scope.insertForm).then(function(response) {
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000);
                $scope.hideModal('#addModal');
                $scope.getRolesList();
            }
        });
    };
    $scope.del = function(){
        $http.get(contextPath + "/role/delete?igRoleId=" + $scope.temp.igRoleId).then(function(response) {
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000);
                $scope.hideModal('#delModal');
                $scope.getRolesList();
            }
        });
    };
    $scope.listPermission = function(){
        $http.get(contextPath + "/role/permissions?igRoleId=" + $scope.temp.igRoleId).then(function(response) {
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
                return;
            }else{
                $scope.rolePermissions = response.data.data.set;
                $scope.rolePermissionsCount = response.data.data.count;
            }
        });
        // $http.get(contextPath + "/user/rolesSelect?igUserId=" + $scope.temp.igUserId).then(function(response) {
        //     var data = response.data;
        //     if(data.returnCode != 200){
        //         Materialize.toast(data.msg, 2000);
        //         return;
        //     }else{
        //         $scope.selectRoles = response.data.data.set;
        //         $scope.selectRolesCount = response.data.data.count;
        //     }
        // });
    };
    $scope.init = function(){
        $scope.getRolesList();
    };
    $scope.init();
}]);