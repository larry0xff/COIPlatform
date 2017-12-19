var adviceApp = angular.module('adviceApp',[]);

adviceApp.controller('adviceListCtrl', ['$scope', '$http', function ($scope, $http) {
    var getList = function(){
        $http.post(contextPath + '/adviceCollection/list',{}).then(function(result){
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