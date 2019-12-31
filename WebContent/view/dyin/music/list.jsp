<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>

<body>
	<div style="padding: 0px; margin: 0px;">
		<ul class="breadcrumb" style="margin: 0px;">
			<li><a href="#">你的音乐管理</a></li>
			<li>查询音乐</li>
		</ul>
	</div>
	<form class="form-inline">
		<div class="row alert alert-info" style="margin: 0px; padding: 3px;">

			<div class="form-group">
				<a class="btn btn-success" href="music_add.action">添加音乐</a>
			</div>

		</div>
		<div class="row" style="padding: 15px; padding-top: 0px;">
			<table class="table  table-condensed table-striped">
				<tr>
					<th>编号</th>
					<th>音乐</th>
					<th>作者</th>
					<th>图片</th>
					<th>操作</th>
				</tr>
				<c:forEach items = "${list }" var = "bean">
					<tr>
						<td>${bean.id }</td>
						<td>${bean.music }</td>
						<td>${bean.author }</td>
						<td>
							<a href="${bean.image }" target="_blank">${bean.image }</a>
						</td>
						<th>
							<a class="btn btn-xs btn-success" href="image_list?id=${bean.id }">图片</a>
							<a class="btn btn-xs btn-danger" href="music_del?id=${bean.id }">删除</a>
						</th>
					</tr>
				</c:forEach>

			</table>
		</div>
	</form>
</body>
</html>