$(function () {
    //获取学生基本信息
    $.post("user/findAStudent",{},function (data) {
        var load = "uploadFile/" + data.photo;
        var resum = "uploadFile/" + data.resume;
        $("#photo").html("<img src='"+load+"' class='avatar' />")
        $("#name").html(data.name);
        $("#tel").html(data.tel);
        $("#email").html(data.email);
        $("#department").html(data.department);
        $("#major").html(data.major);
        $("#student_number").html(data.student_number);
        $("#sex").html(data.sex);
        $("#resume").html("<embed v-show='pdfShow' width='900' height='700' src='"+resum+"'>");
    });
})