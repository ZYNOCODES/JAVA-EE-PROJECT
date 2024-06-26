<%
	if(session.getAttribute("token") != null){
		response.sendRedirect("/Home");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="components/header.jsp"/>
	<style>

		@import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

		* {
			margin: 0;
			padding: 0;
			box-sizing: border-box;
			font-family: "Poppins", sans-serif;
		}

		body {
			background: #272727;
			display: flex;
			justify-content: center;
			align-items: center;
			flex-direction: column;
			font-family: 'Montserrat', sans-serif;
			height: 100vh;
			margin: -20px 0 50px;
		}

		h1 {
			font-weight: bold;
			margin: 0;
			color: #272727;
		}

		h2 {
			text-align: center;
			color: #272727;
		}

		p {
			font-size: 14px;
			font-weight: 100;
			line-height: 20px;
			letter-spacing: 0.5px;
			margin: 20px 0 30px;
			color: #272727;
		}

		span {
			font-size: 12px;
			color: #272727;
		}

		a {
			color: #333;
			font-size: 14px;
			text-decoration: none;
			margin: 15px 0;
		}

		button {
			border-radius: 20px;
			border: 1px solid #ABF600;
			background-color: #ABF600;
			color: #272727;
			font-size: 12px;
			font-weight: bold;
			padding: 12px 45px;
			letter-spacing: 1px;
			text-transform: uppercase;
			transition: transform 80ms ease-in;
		}

		button:active {
			transform: scale(0.95);
		}

		button:focus {
			outline: none;
		}

		button.ghost {
			background-color: transparent;
			border-color: #272727;
		}

		form {
			background-color: #FFFFFF;
			display: flex;
			align-items: center;
			justify-content: center;
			flex-direction: column;
			padding: 0 50px;
			height: 100%;
			text-align: center;
		}

		input {
			background-color: #eee;
			border: none;
			padding: 12px 15px;
			margin: 8px 0;
			width: 100%;
		}
		.button {
			border-radius: 20px;
			border: 1px solid #ABF600;
			background-color: #ABF600;
			color: #272727;
			font-size: 12px;
			font-weight: bold;
			padding: 12px 45px;
			letter-spacing: 1px;
			text-transform: uppercase;
			transition: transform 80ms ease-in;
		}

		.button:active {
			transform: scale(0.95);
		}

		.button:focus {
			outline: none;
		}

		.button.ghost {
			background-color: transparent;
			border-color: #272727;
		}
		.container {
			background-color: #fff;
			border-radius: 10px;
			box-shadow: 0 14px 28px rgba(0,0,0,0.25),
			0 10px 10px rgba(0,0,0,0.22);
			position: relative;
			overflow: hidden;
			width: 768px;
			max-width: 100%;
			min-height: 480px;
		}

		.form-container {
			position: absolute;
			top: 0;
			height: 100%;
			transition: all 0.6s ease-in-out;
		}

		.sign-up-container {
			right: 0;
			width: 50%;
			z-index: 2;
		}

		.container.right-panel-active .sign-up-container {
			transform: translateX(-100%);
		}

		.sign-in-container {
			right: 0;
			width: 50%;
			opacity: 0;
			z-index: 1;
		}

		.container.right-panel-active .sign-in-container {
			transform: translateX(-100%);
			opacity: 1;
			z-index: 5;
			animation: show 0.6s;
		}

		@keyframes show {
			0%, 49.99% {
				opacity: 0;
				z-index: 1;
			}

			50%, 100% {
				opacity: 1;
				z-index: 5;
			}
		}

		.overlay-container {
			position: absolute;
			top: 0;
			right: 50%;
			width: 50%;
			height: 100%;
			overflow: hidden;
			transition: transform 0.6s ease-in-out;
			z-index: 100;
		}

		.container.right-panel-active .overlay-container{
			transform: translateX(100%);
		}

		.overlay {
			background: #ABF600;
			background-size: cover;
			color: #272727;
			position: relative;
			left: -100%;
			height: 100%;
			width: 200%;
			transform: translateX(0);
			transition: transform 0.6s ease-in-out;
		}

		.container.right-panel-active .overlay {
			transform: translateX(50%);
		}

		.overlay-panel {
			position: absolute;
			display: flex;
			align-items: center;
			justify-content: center;
			flex-direction: column;
			padding: 0 40px;
			text-align: center;
			top: 0;
			height: 100%;
			width: 50%;
			transform: translateX(0);
			transition: transform 0.6s ease-in-out;
		}

		.overlay-left {
			transform: translateX(-20%);
		}

		.container.right-panel-active .overlay-left {
			transform: translateX(0);
		}

		.overlay-right {
			right: 0;
			transform: translateX(0);
		}

		.container.right-panel-active .overlay-right {
			transform: translateX(20%);
		}

		.social-container {
			margin: 20px 0;
		}

		.social-container a {
			border: 1px solid #DDDDDD;
			border-radius: 50%;
			display: inline-flex;
			justify-content: center;
			align-items: center;
			margin: 0 5px;
			height: 40px;
			width: 40px;
		}

		footer {
			background-color: #222;
			color: #fff;
			font-size: 14px;
			bottom: 0;
			position: fixed;
			left: 0;
			right: 0;
			text-align: center;
			z-index: 999;
		}

		footer p {
			margin: 10px 0;
		}

		footer i {
			color: red;
		}

		footer a {
			color: #3c97bf;
			text-decoration: none;
		}
		.toast{
			display: flex;
			color: red;
			font-weight: 550;
			font-size: 12px;
			text-align: center;
			margin: 0;
			padding: 0;
		}
	</style>
</head>
<body>
<div class="container" id="container">
	<div class="form-container sign-in-container">
		<form action="Login" method="post">
			<h1>Sign in</h1>
			<br>
			<input type="email" placeholder="Email" name="email"/>
			<input type="password" placeholder="Password" name="password"/>
			<input type="submit" class="button" value="Sign In"/>

		</form>
	</div>
	<div class="form-container sign-up-container">
		<form action="SignUp" method="post">
			<h1>Create Account</h1>
			<br>
			<input type="text" placeholder="Name" name="name"/>
			<input type="email" placeholder="Email" name="email"/>
			<input type="password" placeholder="Password" name="password"/>
			<input type="password" placeholder="Confirm password" name="confirmPassword"/>
			<input type="number" placeholder="phone" name="contact"/>
			<p class="toast">${errorMessage}</p>
			<input type="submit" class="button" value="Sign Up"/>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Hello, Friend!</h1>
				<p>Enter your personal details and start journey with us</p>
				<button class="ghost" id="signUp">Sign Up</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Welcome Back!</h1>
				<p>To keep connected with us please login with your personal info</p>
				<button class="ghost" id="signIn">Sign In</button>
			</div>
		</div>
	</div>
</div>
<script>
	const signUpButton = document.getElementById('signUp');
	const signInButton = document.getElementById('signIn');
	const container = document.getElementById('container');

	signInButton.addEventListener('click', () => {
		container.classList.add("right-panel-active");
	});

	signUpButton.addEventListener('click', () => {
		container.classList.remove("right-panel-active");
	});
</script>
</body>
</html>