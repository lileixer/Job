$(function () {
    $.get("department/getAllDepartment",{},function (data) {
        var department = $("#department");

        $(data).each(function () {
            //3.创建<option>
            var option = "<option name='"+this.name+"'>"+this.name+"</option>";

            //4.调用select的append追加option
            department.append(option);
        });
    })
});
function checkUsername() {
    //获取用户名值
    var username = $("#username").val();
    //定义正则username
    var reg_username = /^\w{4,20}$/;

    //判断给出提示信息
    var flag = reg_username.test(username);

    if (flag) {
        $("#username").css("border","1px solid green");
    }else {
        //用户名不合法
        $("#username").css("border","1px solid red");
    }
    return flag;
}
//校验密码
function checkPassword() {
    //获取用户名值
    var password = $("#password").val();
    //定义正则username
    var reg_password = /^\w{6,20}$/;

    //判断给出提示信息
    var flag = reg_password.test(password);

    if (flag) {
        $("#password").css("border","1px solid green");
    }else {
        //用户名不合法
        $("#password").css("border","1px solid red");
    }
    return flag;
}
//校验邮件
function checkEmail() {
    //获取邮箱
    var email = $("#email").val();
    //定义正则
    var reg_email = /^\w+@\w+\.\w+$/;

    //判断
    var flag = reg_email.test(email);
    if (flag){
        $("#email").css("border","1px solid green");
    }else {
        $("#email").css("border","1px solid red");
    }
    return flag;
}
//校验名字
function checkLeader(){
    var leader = $("#leader").val();

    if (leader.length > 0){
        $("#leader").css("border","1px solid green");
        return true;
    } else {
        $("#leader").css("border","1px solid red");
        return false;
    }
}
//校验地址
function checkAddress(){
    var address = $("#address").val();

    if (address.length > 0){
        $("#address").css("border","1px solid green");
        return true;
    } else {
        $("#address").css("border","1px solid red");
        return false;
    }
}
//校验院系
function checkDepartment(){
    var department = $("#department").val();

    if (department.length > 0){
        $("#department").css("border","1px solid green");
        return true;
    } else {
        $("#department").css("border","1px solid red");
        return false;
    }
}
//校验手机号
function checkTelephone(){
    var telephone = $("#tel").val();
    var reg_telephone = /^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/;

    var flag = reg_telephone.test(telephone);
    if(flag){
        $("#tel").css("border","1px solid green");
    }else {
        $("#tel").css("border","1px solid red");
    }
    return flag;
}

$(function () {
    //表单提交时调用所有校验方法
    $("#registerForm").submit(function () {
        if(checkUsername() && checkPassword() && checkEmail() && checkLeader() && checkTelephone() &&
             checkDepartment() && checkAddress()){
            $.post("boss/register",$(this).serialize(),function (data) {
                if (data.flag){
                    $("#errorMsg").html(data.msg);
                }else {
                    $("#errorMsg").html(data.msg);
                }
            });
        }
        return false;
    });

    //当某一组件失去焦点时，调用对应的校验方法
    $("#username").blur(checkUsername);
    $("#password").blur(checkPassword);
    $("#email").blur(checkEmail);
    $("#leader").blur(checkLeader);
    $("#tel").blur(checkTelephone);
    $("#address").blur(checkAddress);
    $("#department").blur(checkDepartment);
});