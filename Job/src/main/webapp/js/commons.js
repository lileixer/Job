
  var index=0;
    //改变图片
    function ChangeImg() {
        index++;
        var a=document.getElementsByClassName("img-slide");
        if(index>=a.length) index=0;
        for(var i=0;i<a.length;i++){
            a[i].style.display='none';
        }
        a[index].style.display= "block";
    }
    //设置定时器，每隔两秒切换一张图片
    setInterval(ChangeImg,3000);

  var index1=0;
    //改变图片
    function ChangeImg1() {
        index1++;
        var a=document.getElementsByClassName("imgslide");
        if(index1>=a.length) index1=0;
        for(var i=0;i<a.length;i++){
            a[i].style.display='none';
        }
        a[index1].style.display='block';
    }
    //设置定时器，每隔两秒切换一张图片
    setInterval(ChangeImg1,4000);