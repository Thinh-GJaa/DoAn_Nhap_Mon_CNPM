<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<form action="edit-profile" method="post" id="edit-profile-form">
	<div class="container">
		<h1>Thông tin cá nhân</h1>
		<hr>
		<div class="border rounded">
			<div style="background-color: rgb(237, 240, 243);">
				<h6 class="pt-3 ml-3 ">Cập nhật thông tin cá nhân</h6>
				<hr>
			</div>
			<div class="container">
				<div class="mb-3">
					<label class="form-label" style="font-weight: bold">Họ và
						tên</label> <input type="text" class="form-control" name="fullName"
						placeholder="Nhập họ và tên" value="${user.fullName}" required="required">
				</div>

				<div class="mb-3">
					<label class="form-label" style="font-weight: bold">Số
						điện thoại</label> <input type="text" class="form-control" name="phone"
						placeholder="Nhập số điện thoại" value="${user.phone}" required="required">
				</div>

				<div class="mb-3">
					<label class="form-label" style="font-weight: bold">Email</label> <input
						type="email" class="form-control" name="email"
						placeholder="Nhập Email" value="${user.email}" required="required">
				</div>

				<div class="mb-3">
					<label class="form-label" style="font-weight: bold">Giới
						tính</label>
					<div class="form-outline mb-4">
						<select class="form-select w-100 border p-2 rounded"
							name="gender">
							<option value="1">Nam</option>
							<option value="0">Nữ</option>
						</select>
					</div>
				</div>

				<div class="mb-3">
					<label class="form-label" style="font-weight: bold">Địa
						chỉ</label> <input type="text" class="form-control" name="address"
						placeholder="Enter the address" value="${user.address}" required="required">
				</div>

				<div class="mb-3">
					<button type="submit" class="btn btn-outline-secondary">Lưu</button>
					<button type="button" onclick="reset()" class="btn btn-outline-secondary">Reset
					</button>
				</div>
			</div>	
		</div>
	</div>
</form>
<script>
	function reset() {
		document.getElementById("edit-profile-form").reset();
	}
</script>


