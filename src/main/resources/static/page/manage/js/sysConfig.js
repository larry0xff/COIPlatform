var manageApp = angular.module('manageApp', []);

manageApp.controller('sysConfigCtrl', ['$http', '$scope', function($http, $scope){
    $scope.showModal = function(id) {
        $(id).openModal();
    };
    $scope.hideModal = function(id) {
        delete $scope.insertForm, $scope.temp;
        $(id).closeModal();
        $scope.getList();
    };

    //获取列表
    $scope.getList = function (){
        $http.get(contextPath + '/config/list').then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.configList = data.data;
            }
        });
    };

    $scope.save = function() {
        $http.post(contextPath + '/config/save', $scope.insertForm).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.configList = data.data;
                $scope.hideModal('#saveModal');
                $scope.getList();
            }
        });
    };

    $scope.showUpdate = function (config) {
        $scope.temp = config;
        $scope.showModal('#updateModal');
        $("label").addClass("active");
    };

    $scope.upload = function() {
        var file = document.getElementById('uploadfile').files[0];
        var fd = new FormData();
        fd.append('file', file);
        fd.append('type', 2);
        $http({
            url: contextPath + '/file/upload',
            method: 'POST',
            data: fd,
            headers: {'Content-Type':undefined}
        }).then(function(response){
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000, 'rounded');
                $scope.imgUrl = contextPath + "file/download?showname=1&filename=" +  data.data;
            }
        });
    };

    $scope.update = function () {
        $http.post(contextPath + '/config/update?igConfigId=' + $scope.temp.igConfigId, $scope.temp).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.configList = data.data;
                $scope.hideModal('#updateModal');
                $scope.getList();
            }
        });
    };
    (function(){
        $scope.getList();
    })();
}]);