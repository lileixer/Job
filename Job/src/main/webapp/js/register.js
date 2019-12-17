$(function () {
   $.get("department/getPartDepartment",{},function (data) {
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
function checkName(){
    var name = $("#name").val();

    if (name.length > 0){
        $("#name").css("border","1px solid green");
        return true;
    } else {
        $("#name").css("border","1px solid red");
        return false;
    }
}
//校验专业
function checkMajory(){
    var major = $("#major").val();

    if (major.length > 0){
        $("#major").css("border","1px solid green");
        return true;
    } else {
        $("#major").css("border","1px solid red");
        return false;
    }
}
//校验学号
function checkNumber(){
    var number = $("#student_number").val();

    if (number.length > 0){
        $("#student_number").css("border","1px solid green");
        return true;
    } else {
        $("#student_number").css("border","1px solid red");
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
//校验照片
function checkPhoto(){
    var photo = $("#photo").val();

    if (photo.length > 0){
        $("#photo").css("border","1px solid green");
        return true;
    } else {
        $("#photo").css("border","1px solid red");
        return false;
    }
}
//校验简历
function checkResume(){
    var resume = $("#resume").val();

    if (resume.length > 0){
        $("#resume").css("border","1px solid green");
        return true;
    } else {
        $("#resume").css("border","1px solid red");
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
        if(checkUsername() && checkPassword() && checkEmail() && checkName() && checkTelephone() && checkMajory()
            && checkNumber() && checkDepartment() && checkPhoto() && checkResume()){
            $.post("user/register",$(this).serialize(),function (data) {
                if (data.flag){
                    location.href = "register_ok.html";
                }else {
                    $("#errorMsg").html(data.errorMsg);
                }
            });
            var formData = new FormData();
            formData.append("photo",$("#photo")[0].files[0]);
            formData.append("resume",$("#resume")[0].files[0]);
            $.ajax({
                url: 'user/saveFile',
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function(data) {
                },
                error: function(data) {
                }
            });
        }
        return false;
    });

    //当某一组件失去焦点时，调用对应的校验方法
    $("#username").blur(checkUsername);
    $("#password").blur(checkPassword);
    $("#email").blur(checkEmail);
    $("#name").blur(checkName);
    $("#tel").blur(checkTelephone);
    $("#major").blur(checkMajory);
    $("#student_number").blur(checkNumber);
    $("#department").blur(checkDepartment);
    $("#photo").blur(checkPhoto);
    $("#resume").blur(checkResume);
});