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
                Materialize.toast(data.msg, 2000, 'rounded');
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
                Materialize.toast(data.msg, 2000, 'rounded');
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
        $http.get(contextPath + "/role/permissionsSelect?igRoleId=" + $scope.temp.igRoleId).then(function(response) {
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
                return;
            }else{
                $scope.selectPermissions = response.data.data.set;
                $scope.selectPermissionsCount = response.data.data.count;
            }
        });
    };
    $scope.setPermission = function(igPermissionId){
        $http.post(contextPath + "/role/setPermission?igPermissionId=" + igPermissionId + "&igRoleId=" + $scope.temp.igRoleId).then(function(response) {
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000, 'rounded');
                $scope.listPermission();
            }
        });
    };
    $scope.rmPermission = function(igPermissionId){
        $http.post(contextPath + "/role/rmPermission?igPermissionId=" + igPermissionId + "&igRoleId=" + $scope.temp.igRoleId).then(function(response) {
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000, 'rounded');
                $scope.listPermission();
            }
        });
    };
    $scope.init = function(){
        $scope.getRolesList();
    };
    $scope.init();
}]);