<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
		//上传图片
		$('#upload-image').ajaxUploadPrompt({	//上传按钮调用ajaxUploadPrompt方法
			url: '/dyin/star_image.action',	//上传的地址
			beforeSend: function () {	//上传之前
				loading = layer.load();	//弹窗等待，转圈
			},
			success: function (data) {	//上传成功
				if (loading) {
					layer.close(loading);	//关闭转圈
				}
				$('#image').attr('src',data);
				$('input[name="bean.image"]').val(data);
			},
			error: function () {		//上传失败
				if (loading) {
					layer.close(loading);	//关闭转圈
				}
				alert('上传失败');
			}
		});
	});
</script>

</head>

<body>
	<div style="padding: 0px; margin: 0px;">
		<ul class="breadcrumb" style="margin: 0px;">
			<li><a href="#">音乐人管理</a></li>
			<li>添加音乐人</li>
		</ul>
	</div>

	<form action="star_add.action" method="post" class="form-horizontal">
		<h5 class="page-header alert-info"
			style="padding: 10px; margin: 0px; margin-bottom: 5px;">基本信息</h5>
		<div class="row">
			<div class="col-sm-7">
				<div class="form-group">
					<label class="col-sm-3 control-label">音乐人</label>
					<div class="col-sm-9">
						<input type="text" name="bean.name" class="form-control input-sm"
							placeholder="请输入姓名" />
					</div>
				</div>
			</div>
			<div class="col-sm-7">
				<div class="form-group">
					<label class="col-sm-3 control-label">照片</label>
					<div class="col-sm-9">
						<input id = "upload-image" type = "button" class = "btn btn-success" value = "上传图片"/>
						<input type = "hidden" name = "bean.image">
					</div>
				</div>
				<img width="200" style="margin-left:200px" id = "image" src = ""></img>
			</div>
		</div>
				

		<div class="row">
			<div class="col-sm-3 col-sm-offset-2">
				<input type="submit" class="btn btn-success" value="保存" /> <a
					class="btn btn-warning" href="javascript:history.go(-1)">返回上一级</a>
			</div>
		</div>
	</form>

</body>
</html>
