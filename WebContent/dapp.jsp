<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>APP</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <script src="js/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="css/dyin.css">
    <style>
        html,body{
            background: #161823;
            font-size: 2rem;
        }

    </style>
    <script>
        resetrem();
        //切换屏幕（横屏竖屏）
        window.addEventListener("orientationchange", resetrem);
        //resize：屏幕的大小发生改变就触发监听事件resetrem方法
        window.addEventListener("resize", resetrem);

        function resetrem(){
            var html = document.querySelector("html");//获取到html元素
            var width = html.getBoundingClientRect().width;//获取屏幕的宽度
            if (width > 750){
                html.style.fontSize = 90+"px";
            }else {
                html.style.fontSize = width/8 + "px";
            }
        }

        $(function () {
            setTimeout(function () {
                var $ad = $('.app-ad');
                $ad.css('margin-bottom','-15%');
                $ad.animate({
                    'margin-bottom':'0'
                },500);
            });

        })
    </script>
</head>
<body>
    <div class="app-container">
        <img class="app-bg" src="image/xuemaojiao.jpg"/>
    </div>
    <div class="app-wrapper">
        <div class="app-cloth">
            <div class="app-box">
                <img class="app-logo" src="image/music_cover.png"/>
                <img class="app-cat" src="image/xuemaojiao.jpg"/>
                <img class="app-original" src="image/img_original.png">
                <div class="app-music">学猫叫-完整剪辑版</div>
                <div class="app-singer"><span style="color: #ffcd00">@小潘潘</span></div>
                <div class="app-number">--人在看</div>
            </div>
            <div class="app-flow">
                <div>
                    <div>
                        <img src="image/app_image1.jpg"/>
                    </div>
                </div>
                <div>
                    <div>
                        <img src="image/app_image2.jpg"/>
                    </div>
                </div>
                <div>
                    <div>
                        <img src="image/app_image3.jpg"/>
                    </div>
                </div>
                <div>
                    <div>
                        <img src="image/app_image4.jpg"/>
                    </div>
                </div>
            </div>
            <div class="app-footer">
                <div class="app-ad">
                    <img class="app-ad-icon" src="image/banner_ad.png"/>
                    <div class="app-btn">
                        打开看看
                    </div>
                </div>

            </div>

        </div>
    </div>

</body>
</html>