function isOkPass() {
	const passWord = document.getElementById("password").value;
	const phone = document.getElementById("phone").value;
	const pre_passWord = document.getElementById("pre-password").value;
	const pattern_password = "^(?=.*[a-z]{1,})(?=.*[A-Z]{1,})(?=.*[0-9]{1,})(?=.*[!@#\$%\^&\*]{1,})(?=.{8,})";
	const isOkPassWord = passWord.match(pattern_password);
	console.log("da vao");
	if (passWord !== pre_passWord) {
		swal({
			title: "Cảnh báo!",
			text: "Mật khẩu và xác nhận mật khẩu không trùng nhau !",
			icon: "warning",
		});
		return false
	}
	
	if (phone.length < 10 || phone.length > 12) {
		swal({
			title: "Cảnh báo!",
			text: "Số điện thoại phải có từ 10 đến 11 chữ số !",
			icon: "warning",
		});
		return false
	}
	
	if (passWord.length < 8) {
		swal({
			title: "Cảnh báo!",
			text: "Mật khẩu phải lớn hơn hoặc bằng 8 kí tự !",
			icon: "warning",
		});
		return false
	}

	if (!isOkPassWord) {
		swal({
			title: "Cảnh báo!",
			text: "Mật khẩu phải có ít nhất 1 ký tự hoa, 1 ký tự thường, 1 chữ số và một ký tự đặt biệt!",
			icon: "warning",
		});
		return false
	}
	return true
} 