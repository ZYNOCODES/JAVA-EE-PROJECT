<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

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
	<title>Votin</title>
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
			background: #ABF600;
			font-family: 'Montserrat', sans-serif;
			height: 100vh;
			margin: 0;
			-webkit-font-smoothing: antialiased;
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
		.cards_container{
			width: 100%;
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
			padding: 10px;
		}

		header {
			width: 90%;
			max-width: 1240px;
			margin: 0 auto;
		}
		.band {
			width: 90%;
			max-width: 1240px;
			margin: 0 auto;

			display: grid;

			grid-template-columns: 1fr;
			grid-template-rows: auto;
			grid-gap: 20px;

			@media (min-width: 30em) {
				grid-template-columns: 1fr 1fr;
			}

			@media (min-width: 60em) {
				grid-template-columns: repeat(4, 1fr);
			}
		}

		.card {
			background: #272727;
			text-decoration: none;
			color: #FFFFFF;
			box-shadow: 0 2px 5px rgba(0,0,0,0.1);
			display: flex;
			flex-direction: column;
			min-height: 100%;

		// sets up hover state
		position: relative;
			top: 0;
			transition: all .1s ease-in;

			&:hover {
				top: -2px;
				box-shadow: 0 4px 5px rgba(0,0,0,0.2);
			}

			article {
				padding: 20px;
				flex: 1;

				display: flex;
				flex-direction: column;
				justify-content: space-between;
			}

			h1 {
				font-size: 20px;
				margin: 0;
				color: #FFFFFF;
			}

			p {
				flex: 1;
				line-height: 1.4;
				color: #FFFFFF;
			}

			span {
				font-size: 12px;
				font-weight: bold;
				color: #FFFFFF;
				text-transform: uppercase;
				letter-spacing: .05em;
				margin: 2em 0 0 0;
			}

			.thumb {
				padding-bottom: 60%;
				background-size: cover;
				background-position: center center;
			}
		}

		.item:nth-child(1) {
			@media (min-width: 60em) {
				grid-column: 1 / span 2;

				h1 {
					font-size: 24px;
				}
			}
		}

	</style>
</head>
<body>
<nav class="navbar" id="mainNav">
	<div class="container">
		<h2 class="navbar-brand">VOTING</h2>
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
<div class="cards_container">
	<header>
		<h1>Collections</h1>
	</header>
	<div class="band">
		<div class="item">
			<a href="/Collection" class="card">
				<div class="thumb" style="background-image: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/flex-1.jpg);"></div>
				<article>
					<h1>International Artist Feature: Malaysia</h1>
					<span>Mary Winkler</span>
				</article>
			</a>
		</div>
		<div class="item">
			<a href="/Collection" class="card">
				<div class="thumb" style="background-image: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/flex-2.jpg);"></div>
				<article>
					<h1>Movies</h1>
					<span>Mary Winkler</span>
				</article>
			</a>
		</div>
		<div class="item">
			<a href="/Collection" class="card">
				<div class="thumb" style="background-image: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/flex-3.jpg);"></div>
				<article>
					<h1>Football players</h1>
					<span>Mary Winkler</span>
				</article>
			</a>
		</div>
		<div class="item">
			<a href="/Collection" class="card">
				<div class="thumb" style="background-image: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/strange.jpg);"></div>
				<article>
					<h1>International Artist Feature: Malaysia</h1>
					<span>Mary Winkler</span>
				</article>
			</a>
		</div>
		<div class="item">
			<a href="/Collection" class="card">
				<div class="thumb" style="background-image: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/flex-1.jpg);"></div>
				<article>
					<h1>International Artist Feature: Malaysia</h1>
					<span>Mary Winkler</span>
				</article>
			</a>
		</div>
		<div class="item">
			<a href="/Collection" class="card">
				<div class="thumb" style="background-image: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/flex-1.jpg);"></div>
				<article>
					<h1>International Artist Feature: Malaysia</h1>
					<span>Mary Winkler</span>
				</article>
			</a>
		</div>
		<div class="item">
			<a href="/Collection" class="card">
				<div class="thumb" style="background-image: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/flor.jpg);"></div>
				<article>
					<h1>International Artist Feature: Malaysia</h1>
					<span>Mary Winkler</span>
				</article>
			</a>
		</div>
		<div class="item">
			<a href="/Collection" class="card">
				<div class="thumb" style="background-image: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/strange.jpg);"></div>
				<article>
					<h1>International Artist Feature: Malaysia</h1>
					<span>${test}</span>
				</article>
			</a>
		</div>
		<c:forEach begin="1" end="5">
			<div class="item">
				<a href="/Collection" class="card">
					<div class="thumb" style="background-image: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/strange.jpg);"></div>
					<article>
						<h1>International Artist Feature: Malaysia</h1>
						<span>5555555</span>
					</article>
				</a>
			</div>
		</c:forEach>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>