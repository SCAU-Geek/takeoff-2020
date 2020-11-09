
$(function () {
    var timer = null;
    var liW = $(".banner ul li:first").innerWidth();
    var n = 0;
    timer = setInterval(function () {
        play();
    },2000)
    function play() {
        $(".banner ul").animate({left: -liW},function () {
            $(this).css("left",0).find("li:first").appendTo(this);
        })
        n++;
        if(n>2){
            n = 0;
        }
        $(".banner .num span").eq(n).addClass("on").siblings().removeClass("on");
    }
    
    $(".banner").hover(function () {
        clearInterval(timer);
    },function () {
        timer = setInterval(function () {
            play();
        },2000)
    })
    
    $(".next").click(function () {
        play();
    })
    $(".prev").click(function () {
        $(".banner ul").css("left",-liW).find("li:last").prependTo(".banner ul");
        $(".banner ul").animate({left:0})
        
        n--;
        if(n<0){
            n = 2;
        }
        $(".banner .num span").eq(n).addClass("on").siblings().removeClass("on");
    })
    $(".banner .num span").each(function (index) {
        $(this).click(function () {
            if(n < index){
                for(var i = n;i<index;i++){
                    $(".banner ul").animate({left: -liW},100,function () {
                        $(this).css("left",0).find("li:first").appendTo(this);
                    })
                }
            }else if(n>index){
                for(var i = n;i>index;i--){
                    $(".banner ul").css("left",-liW).find("li:last").prependTo(".banner ul");
                    $(".banner ul").animate({left:0},100);
                }
            }
            n = index;
            $(".banner .num span").eq(n).addClass("on").siblings().removeClass("on");
        })
    })
})