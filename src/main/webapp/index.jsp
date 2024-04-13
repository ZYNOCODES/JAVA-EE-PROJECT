<%
	if(session.getAttribute("token") == null){
		response.sendRedirect("/Login");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>Freelancer - Start Bootstrap Theme</title>
	<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
	<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
		crossorigin="anonymous"></script>
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
		rel="stylesheet" type="text/css" />
	<link
		href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
		rel="stylesheet" type="text/css" />
	<style>
		* {
			box-sizing: border-box;
		}

		body {
			background: #FFFFFF;
			font-family: 'Montserrat', sans-serif;
			height: 100vh;
			margin: 0;
		}
		.container{
			width: 100%;
			height: 60px;
			display: flex;
			align-items: center;
			justify-content: space-between;
			flex-direction: row;
			padding: 10px;
			background: #272727;
			margin: 0;
		}
		.navbar-brand{
			color: #ABF600;
		}
		.nav-link{
			display: block;
			padding: 0.5rem 1rem;
			border-radius: 10px;
			color: #272727;
			font-weight: 500;
			text-decoration: none;
			background-color: #ABF600;
			cursor: pointer;
		}
		.navbar-nav{
			display: flex;
			flex-direction: row;
			justify-content: center;
			align-items: center;
			gap: 20px;
		}
		.title{
			color: #ABF600;
			font-weight: 600;
			font-size: 16px;
		}
	</style>
</head>
<body id="page-top">
	<nav
		class="navbar"
		id="mainNav">
		<div class="container">
			<h2 class="navbar-brand">My home page</h2>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav">
					<li class="nav-item">
						<h3 class="title">${session.getAttribute("token")}</h3>
					</li>
					<li class="nav-item mx-0 mx-lg-1">
						<form id="logout-form" action="Logout" method="post">
							<input type="submit" class="nav-link py-3 px-0 px-lg-3 rounded" value="Logout">
						</form>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<header class="masthead bg-primary text-white text-center">
	</header>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
