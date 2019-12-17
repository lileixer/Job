$(function () {
    var name = null;
    //查询用户名
    $.get("user/findUserName",{ },function (data) {
        name = data.name;
    });

    $(".boxc_b").click(function () {
        if (name == null)alert("请先登录");
        else
            $.get("message/saveMessage",$(".msg").serialize(),function (data) {
                window.location.reload(); //刷新当前页面.
            });
    });
    $.post("message/getMessage",{},function (data) {
        var nextbox = $(".nextbox");

        $(data).each(function () {
            //3.创建<option>
            var msg = "<div class='a'>" +
                            "<div class='b'></div>" +
                            "</span>"+this.inputdate+"</span><br>" +
                            "<p style='padding:4px'>"+this.user+": &nbsp;"+this.msg+"</p>" +
                        "</div>";
            //4.调用select的append追加option
            nextbox.append(msg);
            $(".boxc_c").text("");
        });
    })
    //alert(1);
    $(".boxc_c").keydown(function(event){
        var len =$(".boxc_c").text().length;
        if(len > 70){
            alert("够了，你别输入了，哪儿那么多话儿！");
        }
    });
    var dateDom = new Date();
    //获取本地时间，年月日时分
    var year = dateDom.getFullYear();
    var month = dateDom.getMonth()+1;
    var day = dateDom.getDate();
    var hour = dateDom.getHours();
    var min = dateDom.getMinutes();

});