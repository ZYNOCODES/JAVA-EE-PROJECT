<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedDate"/>
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
			box-shadow: 0 2px 5px rgba(0,0,0,0.1);
			display: flex;
			flex-direction: column;
			border-radius: 20px;
			position: relative;
			min-height: 100%;
			top: 0;
			transition: all .1s ease-in;

			&:hover {
				top: -2px;
				box-shadow: 0 4px 5px rgba(0,0,0,0.2);
			}
		}

		.card-a {
			text-decoration: none;
			color: #FFFFFF;

			.collection-article {
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
				height: auto;
				max-height: 220px;
				width: 250px;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: normal;
			}

			span {
				font-size: 12px;
				font-weight: bold;
				color: #FFFFFF;
				text-transform: lowercase;
				letter-spacing: .05em;
				margin: 2em 0 0 0;
			}

			.thumb {
				padding-bottom: 60%;
				background-size: cover;
				background-position: center center;
				border-radius: 20px;
			}
		}
		.card-button{
			width: 60%;
			height: 47px;
			display: flex;
			justify-content: center;
			align-self: center;
			margin-bottom: 10px;
			border-radius: 10px;
		}


		.item:nth-child(1) {
			@media (min-width: 60em) {
				grid-column: 1 / span 2;
				h1 {
					font-size: 24px;
				}
				p {
					width: 500px;
					white-space: nowrap;
				}
			}
		}

	</style>
</head>
<body>
<jsp:include page="components/navbar.jsp">
	<jsp:param name="condition" value="collection"/>
</jsp:include>
<div class="cards_container">
	<header>
		<h1>Collections</h1>
	</header>
	<div class="band">
		<c:choose>
			<c:when test="${error != null && !error.isEmpty()}">
				<div>
					<h1>${error}</h1>
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${cards}" var="Card">
					<div class="item">
						<c:choose>
							<c:when test="${sessionScope.token.getType() == 'user'}">
								<jsp:include page="components/CollectionCard.jsp">
									<jsp:param name="condition" value="${Card.getEnd_date() lt formattedDate}"/>
									<jsp:param name="id" value="${Card.getId()}"/>
									<jsp:param name="name" value="${Card.getName()}"/>
									<jsp:param name="description" value="${Card.getDescription()}"/>
									<jsp:param name="endDate" value="${Card.getEnd_date()}"/>
								</jsp:include>
							</c:when>
							<c:otherwise>
								<jsp:include page="components/AdminCollectionCard.jsp">
									<jsp:param name="condition" value="${Card.getEnd_date() lt formattedDate}"/>
									<jsp:param name="id" value="${Card.getId()}"/>
									<jsp:param name="name" value="${Card.getName()}"/>
									<jsp:param name="description" value="${Card.getDescription()}"/>
									<jsp:param name="endDate" value="${Card.getEnd_date()}"/>
								</jsp:include>
							</c:otherwise>
						</c:choose>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</div><script>
	function showToast(message) {
		alert(message);
	}
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>