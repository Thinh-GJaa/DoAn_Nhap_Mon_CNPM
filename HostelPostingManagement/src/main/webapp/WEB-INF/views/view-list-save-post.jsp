<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="row text-center justify-content-center">
	<c:forEach var="post" items="${listPosts}">

		<c:url var="detailPostLink" value="detail-post">
			<c:param name="postId" value="${post.id}"></c:param>
		</c:url>

		<c:url var="removeSaveListLink" value="remove-save-post">
			<c:param name="postId" value="${post.id}"></c:param>
		</c:url>

		<div class="card col-3 mt-2 mr-4 px-3 pt-2">
			<img
				src="<c:url value="/resources/images/${post.images.iterator().next().fileName}"/>"
				class="card-img-top" style="height: 15rem" alt="...">
			<div class="card-body">
				<p class="card-text text-truncate">${post.title}</p>
			</div>

			<div class="text-center">
				<p class="card-text">${post.area}&nbsp;m<sup>2</sup>
				</p>
				<p class="card-text">${post.price}&nbsp;triệu/tháng</p>
			</div>

			<div class="card-body">
				<a href="${contextPath}/${detailPostLink}" class="btn btn-primary">
					<i class="bi bi-info-circle-fill"></i> Detail
				</a> <a class="btn btn-warning" href="${contextPath}/${removeSaveListLink}"> <i
					class="bi bi-heart"></i> Bỏ lưu tin
				</a>
			</div>
		</div>
	</c:forEach>
</div>