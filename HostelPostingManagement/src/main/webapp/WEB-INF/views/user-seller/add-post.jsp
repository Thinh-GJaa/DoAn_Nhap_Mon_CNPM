
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<div class="mt-3">
	<c:choose>
		<c:when test="${not empty editPost}">
			<h3>Chỉnh sửa bài đăng</h3>
		</c:when>
		<c:otherwise>
			<h3>Thêm bài đăng mới</h3>
		</c:otherwise>
	</c:choose>
	<hr>
</div>
<c:if test="${not empty notify}">
	<div class="text-danger">${notify}</div>
</c:if>

<form class="pl-5 mt-3 mb-3 row" method="post" action="add-post"
	enctype="multipart/form-data">
	<div class="col-6 ">
		<input type="number" name="postId" class="form-control d-none"
			id="waterPrice" value="<c:if test="${not empty editPost}">${post.id}</c:if>"> <input type="number"
			name="accomodationId" class="form-control d-none" id="waterPrice"
			value="<c:if test="${not empty editPost}">${accomodation.id}</c:if>">
		<div class="row mb-3">
			<label for="inputName" class="col-sm-2 col-form-label">Tiêu
				đề </label>
			<div class="col-sm-10">
				<textarea class="form-control" name="title" id="title" rows="2"
					required="required">${post.title}</textarea>
			</div>
		</div>

		<div class="row mb-3">
			<label for="price" class="col-sm-2 col-form-label">Mô tả</label>
			<div class="col-sm-10">
				<textarea class="form-control" name="shortDescription"
					id="shortDescription" rows="2" required="required">${post.shortDescription}</textarea>
			</div>
		</div>

		<div class="row mb-3">
			<label class="col-sm-2 col-form-label">Danh mục</label>
			<div class="col-sm-10">
				<select class="form-select w-100 border-primary p-2 rounded"
					name="categoryId" data-selected="${post.category.id}">
					<c:forEach var="category" items="${listCategories}">
						<option value="${category.id}">${category.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="row mb-3">
			<label class="col-sm-2 col-form-label">Vị trí</label>
			<div class="col-sm-10">
				<select class="form-select w-100 border-primary p-2 rounded"
					name="districtId" data-selected="${post.district.id}">
					<c:forEach var="district" items="${listDistricts}">
						<option value="${district.id}">${district.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="row mb-3">
			<label for="waterPrice" class="col-sm-2 col-form-label">Giá
				nước(m<sup>3</sup>)/đ
			</label>
			<div class="col-sm-10">
				<input type="number" name="waterPrice" class="form-control"
					id="waterPrice" min="0" required="required"
					value="${accomodation.waterPrice }">
			</div>
		</div>

		<div class="row mb-3">
			<label class="col-sm-2 col-form-label">Điều hòa</label>
			<div class="col-sm-10">
				<select class="form-select w-100 border-primary p-2 rounded"
					name="airCondittion" data-selected="${accomodation.airCondittion}">
					<option value="true">Có</option>
					<option value="false">Không</option>
				</select>
			</div>
		</div>

		<div class="row mb-3">
			<label class="col-sm-2 col-form-label">Truyền hình cáp</label>
			<div class="col-sm-10">
				<select class="form-select w-100 border-primary p-2 rounded"
					name="cableTv" data-selected="${accomodation.cableTv}">
					<option value="true">Có</option>
					<option value="false">Không</option>
				</select>
			</div>
		</div>

		<div class="row mb-3">
			<label class="col-sm-2 col-form-label">Máy nước nóng</label>
			<div class="col-sm-10">
				<select class="form-select w-100 border-primary p-2 rounded"
					name="heater" data-selected="${accomodation.heater}">
					<option value="true">Có</option>
					<option value="false">Không</option>
				</select>
			</div>
		</div>

		<div class="row mb-3">
			<label for="file" class="col-sm-2 form-label">Chọn hình
				ảnh </label>
			<div class="col-sm-10">
				<input class="form-control " value="Upload File" type="file"
					name="file" id="file" accept="image/*" required="required"
					multiple="multiple">
			</div>
		</div>

	</div>
	<div class="col-6">
		<div class="row mb-3">
			<label for="content" class="col-sm-2 col-form-label">Nội
				dung </label>
			<div class="col-sm-10">
				<textarea class="form-control" name="content" id="content" rows="2"
					required="required">${post.content}</textarea>
			</div>
		</div>

		<div class="row mb-3">
			<label for="address" class="col-sm-2 col-form-label">Địa
				chỉ</label>
			<div class="col-sm-10">
				<textarea class="form-control" name="address" id="address" rows="2"
					required="required"> ${accomodation.address}</textarea>
			</div>
		</div>

		<div class="row mb-3">
			<label for="price" class="col-sm-2 col-form-label">Giá thuê
				/ triệu đ</label>
			<div class="col-sm-10">
				<input type="number" min="0" name="price" class="form-control"
					id="price" required="required" step="any" value="${post.price }">
			</div>
		</div>

		<div class="row mb-3">
			<label for="area" class="col-sm-2 col-form-label">Diện tích
				sử dụng m<sup>2</sup>
			</label>
			<div class="col-sm-10">
				<input type="number" min="0" name="area" class="form-control"
					id="area" required="required" value="${post.area }">
			</div>
		</div>

		<div class="row mb-3">
			<label for="electricPrice" class="col-sm-2 col-form-label">Giá
				điện (KWh)/đ</label>
			<div class="col-sm-10">
				<input type="number" min="0" name="electricPrice"
					class="form-control" id="electricPrice" required="required"
					value="${accomodation.electricPrice}">
			</div>
		</div>

		<div class="row mb-3">
			<label class="col-sm-2 col-form-label">Kết nối internet</label>
			<div class="col-sm-10">
				<select class="form-select w-100 border-primary p-2 rounded"
					name="internet" data-selected="${accomodation.internet}">
					<option value="true">Có</option>
					<option value="false">Không</option>
				</select>
			</div>
		</div>

		<div class="row mb-3">
			<label class="col-sm-2 col-form-label">Chỗ để xe</label>
			<div class="col-sm-10">
				<select class="form-select w-100 border-primary p-2 rounded"
					name="parking" data-selected="${accomodation.parking}">
					<option value="true">Có</option>
					<option value="false">Không</option>
				</select>
			</div>
		</div>

		<div class="row mb-3">
			<label class="col-sm-2 col-form-label">Còn phòng</label>
			<div class="col-sm-10">
				<select class="form-select w-100 border-primary p-2 rounded"
					name="status" data-selected="${accomodation.status}">
					<option value="true">Có</option>
					<option value="false">Không</option>
				</select>
			</div>
		</div>

	</div>

	<button type="submit" class="btn btn-primary">Đăng bài</button>
	<button type="button" onclick="reset()"
		class="ml-2 btn btn-outline-secondary">Reset</button>
</form>
<script>
	function reset() {
		document.getElementById("edit-profile-form").reset();
	}
</script>