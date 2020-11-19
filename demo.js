//导航栏：鼠标经过时，改变背景颜色
var navLis = document.querySelectorAll('.navLis');
console.log(navLis.length);
for (var i = 0; i < navLis.length; i++) {
    //console.log(navLis[i]);
    navLis[i].onmouseover = function () {
        for (var i = 0; i < navLis.length; i++) {
            navLis[i].style.backgroundColor = '#f8f8f8';

        }
        this.style.backgroundColor = '#e7e7e7';
    }
    navLis[i].onmouseout = function () {
        for (var i = 0; i < navLis.length; i++) {
            navLis[i].style.backgroundColor = '#f8f8f8';

        }
    }

}
//下拉栏，当鼠标经过时，下拉栏显示出更多选项
var dropDown = document.getElementById('Dropdown');
var dul = dropDown.querySelector('ul');
var dli = dropDown.querySelectorAll('li');
dropDown.onmouseover = function () {
    dul.style.display = 'block';
}
dropDown.onmouseout = function () {
    dul.style.display = 'none';
}

//下拉栏时，当鼠标指向某个选项，背景颜色发生改变
for (var i = 0; i < dli.length; i++) {
    dli[i].onmouseover = function () {
        for (var i = 0; i < dli.length; i++) {
            dli[i].style.backgroundColor = '#f8f8f8';

        }
        this.style.backgroundColor = '#e7e7e7';
    }
    dli[i].onmouseout = function () {
        for (var i = 0; i < dli.length; i++) {
            dli[i].style.backgroundColor = '#f8f8f8';

        }
    }

}

//动态生成小圆圈
var pic = document.querySelector('.pic');
var picBox = document.querySelector('.picBox');
var w = document.querySelector('.w')
var picUl = pic.querySelector('ul');
var picLis = picUl.querySelectorAll('li');
var ol = pic.querySelector('.circle');
var arrowR = document.querySelector('.arrow-2');
var arrowL = document.querySelector('.arrow-1');

picBox.onmouseover = function () {
    clearInterval(timer);
    timer = null;//清除定时器变量
}

picBox.onmouseout = function () {
    timer = setInterval(function () {
        arrowR.click();
    }, 2000);
}
//有多少张图片就生成多少个小li
for (var i = 0; i < picLis.length; i++) {
    var li = document.createElement('li');
    li.setAttribute('index', i);
    ol.appendChild(li);
    //点击小圆圈，绑定事件
    li.onclick = function () {
        for (var i = 0; i < ol.children.length; i++) {
            ol.children[i].className = '';
        }
        this.className = 'current';

        var index = this.getAttribute('index');
        num = index;
        circle = index;
        var picWidth = picBox.offsetWidth;
        // console.log(index);
        // console.log(picWidth);
        animate(picUl, -index * picWidth);
    }


}
ol.children[0].className = 'current';
var first = picUl.children[0].cloneNode(true);
picUl.appendChild(first);
// console.log(picLis);

var num = 0;
var circle = 0;
var flag = true;
arrowL.onclick = function () {
    if (flag) {
        flag = false;
        if (num == 0) {
            num = picUl.children.length - 1;
            picUl.style.left = -num * picWidth + 'px';

        }
        num--;

        var picWidth = picBox.offsetWidth;
        animate(picUl, -num * picWidth,function () {
            flag = true;
        });
        circle--;
        if (circle < 0)
            circle = ol.children.length - 1;

        for (var i = 0; i < ol.children.length; i++) {
            ol.children[i].className = '';
        }
        ol.children[circle].className = 'current';


    }

}

arrowR.onclick = function () {

    if (flag) {
        flag = false;
        if (num == picUl.children.length - 1) {
            picUl.style.left = 0;
            num = 0;
        }
        num++;
        var picWidth = picBox.offsetWidth;
        animate(picUl, -num * picWidth,function()
        {
            flag=true;
        });
        circle++;
        if (circle == ol.children.length)
            circle = 0;

        for (var i = 0; i < ol.children.length; i++) {
            ol.children[i].className = '';
        }
        ol.children[circle].className = 'current';

    }


}

//自动播放功能
var timer = setInterval(function () {
    arrowR.click();
}, 2000);