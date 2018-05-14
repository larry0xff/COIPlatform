var adviceApp = angular.module('adviceApp',[]);

adviceApp.controller('adviceListCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.showModal = function(id){
        $(id).openModal();
    };
    var hideModal = function(id){
        delete $scope.insertForm;
        $(id).closeModal();
    };
    var getList = function(){
        $http.post(contextPath + '/adviceCollection/listAll',{status:1}).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.adviceCollections = data.data;
            }
        });
    };
    var getOrgList = function () {
        $http.get(contextPath + '/org/list').then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.orgs = data.data.list;
            }
        });
    };
    var init = function(){
        $('.datepicker').pickadate({
            selectMonths: true, // Creates a dropdown to control month
            selectYears: 15 // Creates a dropdown of 15 years to control year
        });
        $scope.contextPath = contextPath;
        $scope.insertForm = {};
        getList();
        getOrgList();
    }();
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
                Materialize.toast(data.msg, 2000, 'rounded');
                $scope.insert(data.data);
            }
        });
    };
    $scope.insert = function(filename){
        $scope.insertForm.attachmentUrl = filename;
        $http.post(contextPath + "/adviceCollection/save", $scope.insertForm).then(function(response) {
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000, 'rounded');
                hideModal('#addModal');
                delete $scope.insertForm;
                getList();
            }
        });
    };
}]);
adviceApp.controller('adviceDeadListCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.showModal = function(igAdviceCollectionId){
        $scope.igAdviceCollectionId = igAdviceCollectionId;
        $('#handleModal').openModal();
    };
    var hideModal = function(){
        delete $scope.igAdviceCollectionId;
        $('#handleModal').closeModal();
    };
    var getList = function(){
        $http.post(contextPath + '/adviceCollection/listAll',{status:2}).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.adviceCollections = data.data;
            }
        });
    };
    //上传文件后处理结果
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
                hideModal();
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
                Materialize.toast(data.msg, 2000, 'rounded');
                $scope.handle(data.data);
            }
        });
    };
    var init = function(){
        $scope.contextPath = contextPath;
        getList();
    }();
}]);

adviceApp.controller('adviceHistoryListCtrl', ['$scope', '$http', function($scope, $http){
    (function init() {
        getList();
    })();

    function getList(){
        $http.post(contextPath + '/adviceCollection/listAll',{status:3}).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.adviceCollections = data.data;
            }
        });
    }
}]);