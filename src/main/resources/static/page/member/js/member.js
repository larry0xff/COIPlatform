var memberApp = angular.module('memberApp', []);

memberApp.controller('listCtrl', ['$scope', '$http', function($scope, $http){
    $scope.showModal = function(id, user){
        $scope.temp = user;
        $(id).openModal();
    };
    $scope.hideModal = function(id){
        delete $scope.temp;
        $(id).closeModal();
    }
    $scope.list = function(){
        $scope.pageParam = '?page=' + $scope.param.page + '&pageSize=' + $scope.param.pageSize;
        $http.post(contextPath + '/member/list' + $scope.pageParam).then(function(response){
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                if(data.data.count == 0){
                    Materialize.toast("没了！", 2000);
                    $scope.param.page -= 1;
                    return;
                }
                $scope.memberList = data.data.list;
                $scope.memberCount = data.data.count;
            }
        });
    };
    $scope.search = function(){
        if($scope.condition == '' || $scope.condition == null){
            $scope.list();
            return;
        }
        $http.post(contextPath + '/member/search', $scope.condition).then(function(response){
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.memberList = data.data.list;
                $scope.memberCount = data.data.count;
            }
        });
    };
    $scope.whitchPage = function(page){
        if(page == 0 ){
            Materialize.toast('你正在第一页!', 2000);
            return;
        }
        if($scope.memberCount < $scope.param.pageSize){
            Materialize.toast('没了!', 2000);
            return;
        }
        $scope.param.page = page;
        $scope.list();
    };
    $scope.del = function(){
        $http.get(contextPath + '/member/delete?igMemberId=' + $scope.temp.igMemberId).then(function(response){
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000, 'rounded');
                $scope.hideModal('#delModal');
                $scope.list();
            }
        });
    };
    $scope.reset = function(){
        $http.get(contextPath + '/member/resetPassword?igMemberId=' + $scope.temp.igMemberId
            + '&username=' + $scope.temp.username).then(function(response){
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000, 'rounded');
                $scope.hideModal('#resetModal');
                $scope.list();
            }
        });
    };
    $scope.init = function(){
        $scope.param = {
            page: 1,
            pageSize: 40
        };
        $scope.list();
    };

    $scope.init();

}]);

memberApp.controller('bulkCtrl', ['$http', '$scope', function($http, $scope){
    $scope.upload = function(){
        var file = document.getElementById('uploadfile').files[0];
        var fd = new FormData();
        fd.append('file', file);
        fd.append('type', 2);
        $http({
            url: contextPath + '/upload/file',
            method: 'POST',
            data: fd,
            headers: {'Content-Type':undefined}
        }).then(function(response){
            var data = response.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.bulkinsert(data.data);
            }
        });
    };

    $scope.bulkinsert = function(filename){
        console.log('upload success, file name is', filename);
    }
}]);