$(document).ready(function () {
    $(".menu").hover(function () {
        $(this).children("ul").slideDown();
    }, function () {
        $(this).children("ul").slideUp();

    })

    $(function () {
        var slider = $("#pics .slider");
        //获取图片
        var imgCon = $("#pics .slider li");
        //除第一张其余的图片全部隐藏
        imgCon.not(imgCon.eq(0)).hide();
        //定义页码
        var num = $("#pics .num")
        //获取li标签的长度
        //find()方法返回备选元素的后代元素
        var len = slider.find("li").length;
        var html_page = "";
        index = 0;
        //添加页码
        for (var i = 0; i < len; i++) {
            if (i === 0) {
                html_page += "<li class='active'>" + ('') + "</li>"
            }
            else {
                html_page += "<li>" + ('') + "</li>"
            }
        }
        //输出原点
        num.html(html_page)
        //显示索引对应的图片
        function showPic(index) {
            imgCon.eq(index).show().siblings("li").hide();
            num.find("li").eq(index).addClass("active").siblings("li").removeClass("active")
        }
        //原点点击事件
        $(".num li").click(function () {
            index = $(this).index()
            showPic(index)
        })

        // 点击左侧按钮，如果页码小于零则页码改为2
        $(".prev").click(function () {
            index--;
            if (index < 0) {
                index = 2;
            }
            showPic(index)
        })
        // 点击右侧按钮，如果页码大于2则页码改为0
        $(".next").click(function () {
            index++;
            if (index > 2) {
                index = 0;
            }
            showPic(index)
        })
        //图片轮播
        $("#pics").hover(function () {
            clearInterval(window.timer)
        }, function () {
            window.timer = setInterval(function () {
                showPic(index);
                index++;
                if (index === len) {
                    index = 0
                }
            }, 2000)
        }).trigger("mouseleave")//触发备选元素的指定事件

    })
})
