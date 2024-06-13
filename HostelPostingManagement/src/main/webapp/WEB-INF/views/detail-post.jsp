<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<div class="w-75  mb-5">
	<div class="p-3 bg-light mb-2">
		<div id="carouselExampleControls" class="carousel slide mb-4"
			data-ride="carousel">
			<div class="carousel-inner">
				<c:forEach var="image" items="${post.images}" varStatus="status">
					<c:choose>
						<c:when test="${status.first}">
							<div class="carousel-item active" style="height: 32rem">
								<img class="d-block w-100"
									src="<c:url value="/resources/images/${image.fileName}"/>"
									alt="Second slide">
							</div>
						</c:when>
						<c:otherwise>
							<div class="carousel-item" style="height: 32rem">
								<img class="d-block w-100"
									src="<c:url value="/resources/images/${image.fileName}"/>"
									alt="Second slide">
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleControls"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleControls"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
		<c:url var="addToSaveListLink" value="add-save-post">
			<c:param name="postId" value="${post.id}"></c:param>
		</c:url>
		<div class="d-flex">
			<h5>${post.title}</h5>
		</div>
		<a class="btn" href="${contextPath}/${addToSaveListLink}"> <i
			class="bi bi-heart "></i> Lưu tin
		</a>
		<div>
			<span class="text-danger h5">${post.price} triệu/tháng </span><span>-
				${post.area} m <sup>2</sup>
			</span>
		</div>
		<p>${post.shortDescription}</p>
		<p>
			<i class="bi bi-geo-alt h5 mr-1 text-danger"></i>
			${accomodation.address} ${post.district.name }
		</p>
		<p>
			<i class="bi bi-clock h5 mr-1 text-primary"></i> ${post.createdDate}
		</p>
		<p>
			<i class="bi bi-bookmark-plus h5 mr-1 text-danger"></i>Loại:
			${post.category.name}
		</p>
		<p>
			<i class="bi bi-check2-circle h5 mr-1 text-sucess"></i> Tin đã được
			kiểm duyệt
		</p>
	</div>
	<div class="p-3 bg-light mb-2">
		<h5>Đặc điểm</h5>
		<div class="row">
			<div class="col-6">
				<p>
					<i class="bi bi-check"></i> Điều hòa:
					<c:choose>
						<c:when test="${accomodation.isAirCondittion() == true}">Có</c:when>
						<c:otherwise>Không</c:otherwise>
					</c:choose>
				</p>
				<p>
					<i class="bi bi-check"></i> Truyền hình cáp TV:
					<c:choose>
						<c:when test="${accomodation.isCableTv() == true}">Có</c:when>
						<c:otherwise>Không</c:otherwise>
					</c:choose>
				</p>
				<p>
					<i class="bi bi-check"></i> Máy nước nóng:
					<c:choose>
						<c:when test="${accomodation.isHeater() == true}">Có</c:when>
						<c:otherwise>Không</c:otherwise>
					</c:choose>
				</p>
				<p>
					<i class="bi bi-check"></i>Giá điện
					/KWh:${accomodation.electricPrice}
				</p>
			</div>
			<div class="col-6">
				<p>
					<i class="bi bi-check"></i> Internet:
					<c:choose>
						<c:when test="${accomodation.isInternet() == true}">Có</c:when>
						<c:otherwise>Không</c:otherwise>
					</c:choose>
				</p>
				<p>
					<i class="bi bi-check"></i> Chỗ đậu xe:
					<c:choose>
						<c:when test="${accomodation.isParking() == true}">Có</c:when>
						<c:otherwise>Không</c:otherwise>
					</c:choose>
				</p>
				<p>
					<i class="bi bi-check"></i> Giá nước/m<sup>3</sup>:
					${accomodation.waterPrice}
				</p>
				<p>
					<i class="bi bi-check"></i> Trình trạng:
					<c:choose>
						<c:when test="${accomodation.isStatus() == true}">Còn Phòng</c:when>
						<c:otherwise>Hết phòng</c:otherwise>
					</c:choose>
				</p>
			</div>
		</div>
	</div>
	<div class="p-3 bg-light mb-2">
		<h5>Mô tả chi tiết</h5>
		<p>${post.content}</p>
	</div>
	<div class="p-3 bg-light mb-2">
		<h5>Liên hệ với người bán</h5>
		<p>
			<i class="bi bi-check"></i>Anh/Chị: ${post.user.fullName}
		</p>
		<p>
			<i class="bi bi-check"></i>Số điện thoại: ${post.user.phone}
		</p>
	</div>
	<sec:authorize access="hasRole('TENANT')">
		<div class="p-3 bg-light">
			<h5>Báo cáo bài viết</h5>
			<form action="report-post" method="post">
				<input name="postId" class="d-none" value="${post.id}"> <label
					for="inputName" class="col-sm-2 col-form-label">Nội dung </label>
				<div class="col-sm-10">
					<textarea class="form-control" name="content" id="title" rows="2"
						required="required"></textarea>
				</div>
				<button class="btn bg-primary text-white px-4 ml-2 mt-2">Gửi</button>
			</form>
		</div>
	</sec:authorize>
</div>