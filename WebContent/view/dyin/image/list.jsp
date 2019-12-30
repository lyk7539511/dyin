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
			<li><a href="#">图片管理</a></li>
			<li>查询图片</li>
		</ul>
	</div>
	<form class="form-inline">
		<div class="row alert alert-info" style="margin: 0px; padding: 3px;">

			<div class="form-group">
				<!-- 此处id是music的id -->
				<a class="btn btn-success" href="image_add.action?id=${id }">添加图片</a>
			</div>

		</div>
		<div class="row" style="padding: 15px; padding-top: 0px;">
			<table class="table  table-condensed table-striped">
				<tr>
					<th>编号</th>
					<th>图片</th>
					<th>操作</th>
				</tr>
				<c:forEach items = "${list }" var = "bean">
					<tr>
						<td>${bean.id }</td>
						<td>
							<a href="${bean.image }" target="_blank">${bean.image }</a>
						</td>
						<th>
							
							<a class="btn btn-xs btn-danger" href="image_del?id=${bean.id }&mid=${id }">删除</a>
						</th>
					</tr>
				</c:forEach>

			</table>
		</div>
	</form>
</body>
</html>