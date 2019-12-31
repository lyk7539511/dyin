<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>二手抖音音乐人</title>
    <script src="js/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="css/dyin.css">
    

    <script>
        var distance = 120;
        $(function () {
            var $leftpointer = $('.left-pointer');
            var $rightpointer = $('.right-pointer');
            var $marquee = $('.musician-music-marquee');
            var musicSize = parseInt('${musicSize }');
            $marquee.css('width',(1 + parseInt('${musicSize }'))*240 + 'px');
            $marquee.css('margin-left',distance + 'px');
            
            $leftpointer.on('click',function () {   //点击左按钮
                if (distance < 0){
                    distance += 240;
                    $marquee.animate({
                        'margin-left':(distance+'px')
                    },300);
                }
            });
            $rightpointer.on('click',function () {  //点击右按钮
            	
            	var windowWidth = $(window).width();
            	if(windowWidth <= 1200)
            		windowWidth = 1200;
                if (distance > (1200 - 240 * musicSize)){
                    distance -= 240;
                    $marquee.animate({
                        'margin-left':(distance+'px')
                    },300);
                }
            });
            //鼠标移入移出图片动画
            $('.musician-music').hover(function () {
                $(this).children('.musician-music-logo').addClass('musician-music-logo-active');    //children()用来找到当前对象的孩子
                $(this).children('.musician-music-name').addClass('musician-music-name-active');
            },function () {
                $(this).children('.musician-music-logo').removeClass('musician-music-logo-active');    //children()用来找到当前对象的孩子
                $(this).children('.musician-music-name').removeClass('musician-music-name-active');
            });

            var $video = $('.musician-video');
            var $start = $('.musician-start');
            var $song = $('.musician-song');	//song是一个数组
            $song.eq(0).css('background','#f1f1f2');
            $song.on('click',function () {
                var video = $(this).attr('video');
                if ($video.attr('src') !== video){  //如果点击的是同一个视频，则不发生任何变化

                    $video.attr('src',video);   //设置标签属性
                    $(this).css('background','#f1f1f2');   //设置标签样式，将选中的背景变为灰色
                    $(this).siblings().css('background','#ffffff'); //将其他背景变为白色

                    //点击其他视频默认直接播放
                    $video.get(0).play();
                    $start.css('display','none');
                    $start.attr('play','1');
                }


            });

            //播放
            var $player = $('.musician-player');
            $player.on('click',function () {
                if ($(this).attr('src')==='image/player_close.svg'){
                    $video.get(0).muted = false;
                    $(this).attr('src','image/player.svg');
                }else {
                    $video.get(0).muted = true;
                    $(this).attr('src','image/player_close.svg');
                }
            });


            $start.on('click',function () {
                var $play = $(this).attr('play');
                if ($play === '0'){ //0为暂停状态
                    $video.get(0).play();   //播放
                    $(this).attr('play','1');
                    $(this).css('display','none');
                }
            })
            $video.on('click',function () {
                var $play = $start.attr('play');
                if ($play === '0'){ //0为暂停状态
                    $video.get(0).play();   //播放
                    $start.attr('play','1');
                    $start.css('display','none');
                }else {
                    $video.get(0).pause();   //暂停
                    $start.attr('play','0');
                    $start.css('display','inline-block');
                }
            });

        })
    </script>
</head>
<body>
    <div class="musician-header">
        <img class="musician-logo" src="image/musician-logo.png" width="164">
        <div class="musician-nav">
            <div style="color: red">登录</div>
            <div>国风征集季</div>
            <div style="border-bottom: red 2px solid">首页</div>
        </div>
    </div>
    <div class="musician-ad">
        <div class="musician-slogan">你的音乐，世界看见</div>
        <div class="musician-join">立即入驻</div>
    </div>

    <div>
        <div class="musician-listen">让你的音乐在抖音被更多人听见</div>
        <div class="musician-text">自由上传音乐文件并与更多人分享，丰富你的资料并收获更多关注。</div>
        <div class="musician-text">随时追踪音乐的数据动态！</div>
        <div style="text-align: center;margin-top: 20px">
            <img src="image/joinus.png" width="1000"/>
        </div>
        <div style="text-align: center">
            <div class="musician-join2" style="display: inline-block">立即入驻</div>
        </div>
    </div>

    <div class="musician-music-container">
        <div class="musician-listen">抖音音乐</div>
        <div class="musician-text">数以亿计用户在等待聆听并分享你的音乐。</div>
        <div class="musician-music-marquee">
        	<c:forEach items="${musicList }" var="bean">
        		<a class="musician-music" href="index_dapp.action?id=${bean.id }">
                	<img class="musician-music-logo" width="180" height="180" src="${bean.image }"/>
                	<span class="musician-music-name">${bean.music }</span>
            	</a>
        	</c:forEach>

        </div>
        <img class="left-pointer" src="image/left-pointer.svg"/>
        <img class="right-pointer" src="image/right-pointer.svg"/>

    </div>

    <div class="musician-people-container">
        <div class="musician-listen">抖音音乐人</div>
        <c:forEach items="${starList }" var="bean">
        	<div class="musician-people">
            	<div class="musician-star">
                	<img src="${bean.image }" width="94" height="94"/>
            	</div>
            	<div class="musician-people-name">${bean.name }</div>
        	</div>
        </c:forEach>        
    </div>

    <div class="musician-wrapper">
        <div class="musician-cloth">
            <div class="musician-song-container">
                <div class="musician-song-list">
                <c:forEach items="${umcList }" var="bean">
                	<div class="musician-song" video = "${bean.video }">
                        <img src="${bean.image }" height="40">
                        <div class="musician-singer">
                            <div style="font-weight: bold">${bean.title }</div>
                            <div>${bean.author }</div>
                        </div>
                    </div>
                </c:forEach>
                </div>
                <video class="musician-video" loop muted src="${videoString }" ></video>
                <img class="musician-player" src="image/player_close.svg"/>
                <img play="0" class="musician-start" src="image/start.svg"/>

            </div>
        </div>
    </div>


</body>
</html>