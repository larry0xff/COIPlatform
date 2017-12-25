var adviceApp = angular.module('adviceApp',[]);

adviceApp.controller('adviceListCtrl', ['$scope', '$http', function ($scope, $http) {
    var getList = function(){
        $http.post(contextPath + '/adviceCollection/list?status=1',{}).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.adviceCollections = data.data;
            }
        });
    };
    var init = function(){
        $scope.contextPath = contextPath;
        getList();
    }();

}]);
adviceApp.controller('adviceDeadListCtrl', ['$scope', '$http', function ($scope, $http) {
    var getList = function(){
        $http.post(contextPath + '/adviceCollection/list?status=2',{}).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.adviceCollections = data.data;
            }
        });
    };
    $scope.handle = function(filename){
        var formData = {
            resultAttachmentUrl: filename,
            igAdviceCollectionId: $scope.igAdviceCollectionId
        };
        $http({
            url: contextPath + '/adviceCollection/handle',
            data: formData,
            method: 'POST'
        }).then(function(response){
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{

            }
        });
    };
    $scope.upload = function(){
        var file = document.getElementById('uploadfile').files[0];
        var fd = new FormData();
        fd.append('file', file);
        fd.append('type', 3);
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
                $scope.handle(data.data);
            }
        });
    };
    var init = function(){
        $scope.contextPath = contextPath;
        getList();
    }();
}]);