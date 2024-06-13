
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<div class="text-center m-auto">
	<div class="bg-light border p-2 mt-3">Đăng ký</div>
	<c:if test="${not empty error}">
		<div class="text-danger bg-light ml-2">
			<c:out value="${error}"></c:out>
			<br />
		</div>
	</c:if>
	<form class="border p-2 bg-white" action="register" method="post">

		<!-- User name input -->
		<div class="form-outline mb-4">
			<input type="text" id="username" name="username" class="form-control"
				required="required" placeholder="Nhập User name" />
		</div>

		<!-- Email input -->
		<div class="form-outline mb-4">
			<input type="email" id="email" name="email" class="form-control"
				required="required" placeholder="Nhập email" />
		</div>

		<div class="form-outline mb-4">
			<input type="text" name="fullName" class="form-control"
				required="required" placeholder="Nhập tên đầy đủ" />
		</div>

		<div class="form-outline mb-4">
			<select class="form-select w-100 border p-2 rounded" name="gender">
				<option value="1">Nam</option>
				<option value="0">Nữ</option>
			</select>
		</div>

		<div class="form-outline mb-4">
			<select class="form-select w-100 border p-2 rounded" name="userType">
				<option value="1">Người thuê trọ</option>
				<option value="2">Người cho thuê trọ</option>
			</select>
		</div>

		<div class="form-outline mb-4">
			<input type="number" name="phone" class="form-control" id="phone"
				required="required" placeholder="Nhập sdt" maxlength="11" />	
		</div>

		<div class="form-outline mb-4">
			<input type="text" name="address" class="form-control"
				required="required" placeholder="Nhập địa chỉ" />
		</div>

		<!-- Password input -->
		<div class="form-outline mb-4">
			<input type="password" id="password" name="password"
				class="form-control" required="required"
				placeholder="Nhập mật khẩu" />
		</div>

		<!-- Password confirm input -->
		<div class="form-outline mb-4">
			<input type="password" id="pre-password" name="pre-password"
				class="form-control" required="required" placeholder="Nhập lại mật khẩu" />
		</div>
		<!-- Submit button -->
		<button type="submit" onclick="return isOkPass()"
			class="btn btn-success btn-block mb-2">Register</button>

		<!-- Register buttons -->
		<p>
			<a class="nav-link" href="login">Click here to login</a>
		</p>
	</form>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/register.js"/>"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
