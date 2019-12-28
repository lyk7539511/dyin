<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="js/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="js/bootstrap.min.js"></script>

<script src="js/jquery.ajaxupload.js"></script>
<link rel="stylesheet" href="layer/theme/default/layer.css"/>
<script src="layer/layer.js"></script>

<script type="text/javascript">
	$(function() {
		$.ajaxUploadSettings.name = "file";	//文件是一个变量值
		var loading = null;
		$('#upload-video').ajaxUploadPrompt({	//上传按钮调用ajaxUploadPrompt方法
			url: '/dyin/video_upload.action',	//上传的地址
			beforeSend: function () {	//上传之前
				loading = layer.load();	//弹窗等待，转圈
			},
			success: function (data) {	//上传成功
				if (loading) {
					layer.close(loading);	//关闭转圈
				}
				alert('上传成功');
			},
			error: function () {		//上传失败
				if (loading) {
					layer.close(loading);	//关闭转圈
				}
				alert('上传失败');
			}
		})
	});
</script>

</head>
<body>
	<div style="padding: 0px; margin: 0px;">
		<ul class="breadcrumb" style="margin: 0px;">
			<li><a href="#">主页管理</a></li>
			<li>背景视频</li>
		</ul>
	</div>
	<form class="form-inline">
		<div class="row alert alert-info"  style="margin:0px; padding:3px;" >
			<input id="upload-video" type="button" class="btn btn-success" value="上传视频" />
		</div>
	</form>
</body>
</html>