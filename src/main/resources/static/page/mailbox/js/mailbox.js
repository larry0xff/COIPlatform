var mailboxApp = angular.module('mailboxApp', []);

mailboxApp.controller('mailListCtrl', ['$scope', '$http', '$filter', function($scope, $http, $filter){
    function getList(){
        $http.get(contextPath + '/mailbox/list?status=waitting').then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.mailList = data.data;
            }
        });
    }
    $scope.openModal = function(item){
        $scope.item = item;
        $('#replyModal').openModal();
        $scope.changeReadStatus(item.igMailId, 'Y');
    };
    $scope.closeModal = function(){
        delete $scope.item;
        $('#replyModal').closeModal();
    };
    $scope.checkSwitch = function () {
        $http.get(contextPath + "/manage/switch/check?name=MAILBOX_MODULE").then(function (result) {
            var data = result.data;
            if(data.returnCode != 200) {
                Materialize.toast(data.msg, 2000);
            } else {
                $scope.sw =  data.data;
            }
        });
    };
    //回复信件
    $scope.reply = function(){
        if ($scope.sw.status == 1) {
            Materialize.toast($scope.sw.message, 2000);
            return;
        }
        $http.post(contextPath + '/mailbox/reply?igMailId=' + $scope.item.igMailId + '&reply=' + $scope.replyStr).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                Materialize.toast(data.msg, 2000, 'rounded');
                getList();
                $scope.closeModal();
            }
        });
    };
    //改变信件读取状态
    $scope.changeReadStatus = function(igMailId, isRead){
        $http.get(contextPath + '/mailbox/read?igMailId=' + igMailId + '&isRead=' + isRead).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                getList();
            }
        });
    };
    (function init(){
        getList();
    })();
}]);

mailboxApp.controller('mailHistoryListCtrl', ['$scope', '$http', function($scope, $http){
    function getList(){
        $http.get(contextPath + '/mailbox/list?status=done').then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                Materialize.toast(data.msg, 2000);
            }else{
                $scope.mailList = data.data;
            }
        });
    }
    $scope.openModal = function(item){
        $scope.item = item;
        $('#detailModal').openModal();
    };
    $scope.closeModal = function(){
        delete $scope.item;
        $('#detailModal').closeModal();
    };
    (function init(){
        getList();
    })();
}]);