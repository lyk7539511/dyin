<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>二手抖音</title>
<script src="js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="css/dyin.css">
<style>
html, body { /* 仅适用于图片或视频占用一整屏*/
	width: 100%;
	height: 100%;
}
</style>
<script>
	/*************************************************************************
	 // function playerClick() {
	//     var player = document.getElementById('player');
	//     var video = document.getElementById('video');
	//     if (player.getAttribute('src') === 'image/player_close.png'){
	//         player.setAttribute('src','image/player.png');
	//         video.muted = false;
	//     }else{
	//         player.setAttribute('src','image/player_close.png');
	//         video.muted = true;
	//     }
	// }
	 ****************************************************************************/

	/****************************************************************************
	 window.onload = function () {
	    var player = document.getElementById('player');
	    var video = document.getElementById('video');
	    player.onclick = function () {
	        if (player.getAttribute('src') === 'image/player_close.png') {
	            player.setAttribute('src', 'image/player.png');
	            video.muted = false;
	        } else {
	            player.setAttribute('src', 'image/player_close.png');
	            video.muted = true;
	        }
	    }
	}
	 ****************************************************************************/

	//JQuery
	$(function() {
		//音乐播放
		var $player = $('#player'); //Jquery获取标签
		var $video = $('#video');
		$player.on('click', function() {
			if ($player.attr('src') === 'image/player_close.png') {
				$player.attr('src', 'image/player.png');
				$video.get(0).muted = false;
			} else {
				$player.attr('src', 'image/player_close.png');
				$video.get(0).muted = true;
			}

		});

		//下拉菜单鼠标悬停事件
		var $company = $('.index-company');
		var $menu = $('.index-menu')
		$company.hover(function() {
			//鼠标移动到index-company身上，触发事件
			$menu.css('display', 'inline-block');

		}, function() {
			$menu.css('display', 'none');
		});

		$menu.hover(function() {
			//鼠标移动到index-company身上
			$menu.css('display', 'inline-block');

		}, function() {
			$menu.css('display', 'none');
		});

		//鼠标悬停QRCode
		var $qrcode = $('#qrcode');
		var $qrcodeBig = $('#qrcode-big');
		var $app = $('.index-app');
		//鼠标移入小图
		$qrcode.hover(function() {
			//改变属性(JSON格式数据)，变化时间
			$qrcodeBig.animate({ //由小变大
				'width' : '238px',
				'height' : '239px',
				'opacity' : '1' //透明渐变
			}, 300);
			$app.animate({ //苹果安卓下载按钮消失
				'opacity' : '0'
			}, 300);
		});
		//鼠标移出qrcode大图
		$qrcodeBig.hover('', function() {
			$qrcodeBig.animate({ //由大变小
				'width' : '0',
				'height' : '0',
				'opacity' : '0' //透明渐变
			}, 300);
			$app.animate({ //苹果安卓下载按钮出现
				'opacity' : '1'
			}, 300);
		});
		//下载按钮的透明
		$app.eq(0).hover(function() {
			$app.eq(0).css('opacity', '0.5');
		}, function() {
			$app.eq(0).css('opacity', '1');
		});
		$app.eq(1).hover(function() {
			$app.eq(1).css('opacity', '0.5');
		}, function() {
			$app.eq(1).css('opacity', '1');
		});

	})
</script>

</head>

<body>
	<video id="video" class="index-video" autoplay loop muted
		src="${videoString}"></video>
	<div class="index-header">
		<img class="index-logo" src="image/logo.png" />
		<div class="index-nav">
			<div>
				<img id="player" src="image/player_close.png" width="18" />
			</div>
			<div class="index-company">
				企业合作<span class="index-pointer"></span>
			</div>
			<div>机构认证</div>
			<div>抖音MCN</div>
			<div>抖音音乐人</div>
			<div>抖音特效师</div>
			<div>视频上传</div>
			<div>开放平台</div>
		</div>

	</div>
	<div class="index-footer">
		<div style="line-height: 50px">| 音乐 |用户服务协议 | 隐私政策 | 账号找回 | 联系我们
			| 广告投放 | 营业执照 |</div>
		<div>2019 © 抖音 | 京ICP备16016397号-3 | 北京微播视界科技有限公司 | 京B2-20170846</div>
		<div>中国互联网举报中心 | 网络文化许可证-京网文-（2016）2282-264号 |
			客户服务热线：400-992-2556 | 违法和不良信息举报：400-140-2108 | 举报邮箱：jubao@douyin.com
		</div>
		<div>京公网安备 11010802023605号 | 地址 : 北京市海淀区知春路51号4层408</div>
	</div>

	<div class="index-main">
		<img width="450px" style="margin-right: 150px" src="image/slogan.png" />
		<div class="index-download">
			<img class="index-app" height="50" src="image/appstore.png"
				style="margin-right: 8px" /> <img class="index-app" height="50"
				src="image/android.png" style="margin-right: 8px" />
			<div class="index-qrcode" style="margin-right: 150px">
				<img id="qrcode" height="50" src="image/qrcode.png" /> <img
					id="qrcode-big" src="image/qrcode_big.png" />
			</div>

		</div>

	</div>


	<div class="index-popup">
		<div class="index-menu">
			<div>广告合作</div>
			<div>企业认证</div>
		</div>
	</div>


</body>

</html>