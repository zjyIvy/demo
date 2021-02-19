$(function () {

    showUser();

});

function showUser() {
    $.ajax({
        type: "GET", // 请求方式
        url: "/user/findUser", // 目标资源
        cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
        async: true, //默认是true，即为异步方式
        dataType: "json", // 服务器响应的数据类型
        success:function(data){
            $("#name").text(data.name);
            showGroup(data.id);
            showRole(data.id);
            showUserPerm(data.id);
        },
        error:function(){
            //请求出错处理
        }
    });
}

function showGroup(userId) {
    $.ajax({
        type: "GET", // 请求方式
        url: "/group/findGroup", // 目标资源
        data:{
            userId: userId
        }, // 参数
        cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
        async: true, //默认是true，即为异步方式
        dataType: "json", // 服务器响应的数据类型
        success:function(data){
            $("#group").text(data.name);
        }
    });
}

function showRole(userId) {
    $.ajax({
        type: "GET", // 请求方式
        url: "/role/findRole", // 目标资源
        data:{
            userId: userId
        },  // 参数
        cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
        async: true, //默认是true，即为异步方式
        dataType: "json", // 服务器响应的数据类型
        success:function(data){
            var string ="";
            var roleIds = new Array();
            for (var i = 0; i < data.length; i++) {
                roleIds[i] = data[i].id;
                string += (i+1)+"："+data[i].name;
            }
            $("#role").text(string);
            showModule(roleIds);
            showPerm(roleIds);
        }
    });
}

function showModule(roleIds) {
    $.ajax({
        type: "POST", // 请求方式
        url: "/module/findModule", // 目标资源
        data:{
            roleIds: roleIds
        }, // 参数
        cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
        async: true, //默认是true，即为异步方式
        traditional: true,// 阻止深度序列化
        dataType: "json", // 服务器响应的数据类型
        success:function(data){
            var string ="";
            for (var i = 0; i < data.length; i++) {
                string += " "+(i+1)+"："+data[i].name;
            }
            $("#module").text(string);
        }
    });
}

function showPerm(roleIds) {
    $.ajax({
        type: "POST", // 请求方式
        url: "/perm/findPerm", // 目标资源
        data:{
            roleIds: roleIds
        }, // 参数
        cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
        async: true, //默认是true，即为异步方式
        traditional: true,// 阻止深度序列化
        dataType: "json", // 服务器响应的数据类型
        success:function(data){
            var string ="";
            for (var i = 0; i < data.length; i++) {
                string += " "+data[i].id+"："+data[i].name;
            }
            $("#perm").text(string);
        }
    });
}

function showUserPerm(userId) {
    $.ajax({
        type: "POST", // 请求方式
        url: "/perm/findUserPerm", // 目标资源
        data:{
            userId: userId
        }, // 参数
        cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
        async: true, //默认是true，即为异步方式
        traditional: true,// 阻止深度序列化
        dataType: "json", // 服务器响应的数据类型
        success:function(data){
            var string ="";
            for (var i = 0; i < data.length; i++) {
                string += " "+data[i].id+"："+data[i].name;

            }
            $("#userPerm").text(string);
        }
    });
}

function setWarning() {
    $.ajax({
        type: "GET", // 请求方式
        url: "/operate/setWarning",
        cache: false,
        async: true,
        dataType: "json",
        success:function(data){
            alert(data.msg);
        },
        error:function(data){
            //请求出错处理
            if (data.status == 403){
                alert("当前用户无权限操作");
            }
        }
    });
}

function selectMean() {
    $.ajax({
        type: "GET", // 请求方式
        url: "/operate/selectMean",
        cache: false,
        async: true,
        dataType: "json",
        success:function(data){
            alert(data.msg);
        },
        error:function(data){
            //请求出错处理
            if (data.status == 403){
                alert("当前用户无权限操作");
            }
        }
    });
}

function selectMax() {
    $.ajax({
        type: "GET", // 请求方式
        url: "/operate/selectMax",
        cache: false,
        async: true,
        dataType: "json",
        success:function(data){
            alert(data.msg);
        },
        error:function(data){
            //请求出错处理
            if (data.status == 403){
                alert("当前用户无权限操作");
            }
        }
    });
}

function selectMin() {
    $.ajax({
        type: "GET", // 请求方式
        url: "/operate/selectMin",
        cache: false,
        async: true,
        dataType: "json",
        success:function(data){
            alert(data.msg);
        },
        error:function(data){
            //请求出错处理
            if (data.status == 403){
                alert("当前用户无权限操作");
            }
        }
    });
}

function selectReal() {
    $.ajax({
        type: "GET", // 请求方式
        url: "/operate/selectReal",
        cache: false,
        async: true,
        dataType: "json",
        success:function(data){
            alert(data.msg);
        },
        error:function(data){
            //请求出错处理
            if (data.status == 403){
                alert("当前用户无权限操作");
            }
        }
    });
}

function warningImport() {
    $.ajax({
        type: "GET", // 请求方式
        url: "/operate/warningImport",
        cache: false,
        async: true,
        dataType: "json",
        success:function(data){
            alert(data.msg);
        },
        error:function(data){
            //请求出错处理
            if (data.status == 403){
                alert("当前用户无权限操作");
            }
        }
    });
}

function warningExport() {
    $.ajax({
        type: "GET", // 请求方式
        url: "/operate/warningExport",
        cache: false,
        async: true,
        dataType: "json",
        success:function(data){
            alert(data.msg);
        },
        error:function(data){
            //请求出错处理
            if (data.status == 403){
                alert("当前用户无权限操作");
            }
        }
    });
}

function addEquipment() {
    $.ajax({
        type: "GET", // 请求方式
        url: "/operate/addEquipment",
        cache: false,
        async: true,
        dataType: "json",
        success:function(data){
            alert(data.msg);
        },
        error:function(data){
            //请求出错处理
            if (data.status == 403){
                alert("当前用户无权限操作");
            }
        }
    });
}

function setEquipment() {
    $.ajax({
        type: "GET", // 请求方式
        url: "/operate/addEquipment",
        cache: false,
        async: true,
        dataType: "json",
        success:function(data){
            alert(data.msg);
        },
        error:function(data){
            //请求出错处理
            if (data.status == 403){
                alert("当前用户无权限操作");
            }
        }
    });
}

function selectUser() {
    $.ajax({
        type: "GET", // 请求方式
        url: "/operate/selectUser",
        cache: false,
        async: true,
        dataType: "json",
        success:function(data){
            alert(data.msg);
        },
        error:function(data){
            //请求出错处理
            if (data.status == 403){
                alert("当前用户无权限操作");
            }
        }
    });
}

function logout() {
    window.location.href = "/logout";
}