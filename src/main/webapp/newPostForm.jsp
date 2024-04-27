<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lv
  Date: 4/27/2024
  Time: 7:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <jsp:include page="components/header.jsp"/>
    <style>
        @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap");
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Poppins", sans-serif;
        }

        body {
            background: #ABF600;
            font-family: 'Montserrat', sans-serif;
            height: 100vh;
            margin: 0;
            -webkit-font-smoothing: antialiased;
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
        .container-form {
            position: relative;
            width: 100%;
            padding: 25px;
            border-radius: 8px;
        }
        .container-form header {
            font-size: 1.5rem;
            color: #333;
            font-weight: 500;
            text-align: center;
        }
        .container-form .form {
            margin-top: 30px;
        }
        .form{
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
        .form .input-box {
            width: 100%;
            margin-top: 20px;
        }
        .input-box label {
            color: #333;
        }
        .form :where(.input-box input, .select-box) {
            position: relative;
            height: 50px;
            width: 100%;
            outline: none;
            font-size: 1rem;
            color: #707070;
            margin-top: 8px;
            border: 1px solid #ddd;
            border-radius: 6px;
            padding: 0 15px;
        }
        .input-box input:focus {
            box-shadow: 0 1px 0 rgba(0, 0, 0, 0.1);
        }
        .form .column {
            display: flex;
            column-gap: 15px;
        }
        .form .gender-box {
            margin-top: 20px;
        }
        .gender-box h3 {
            color: #333;
            font-size: 1rem;
            font-weight: 400;
            margin-bottom: 8px;
        }
        .form :where(.gender-option, .gender) {
            display: flex;
            align-items: center;
            column-gap: 50px;
            flex-wrap: wrap;
        }
        .form .gender {
            column-gap: 5px;
        }
        .gender input {
            accent-color: #272727;
        }
        .form :where(.gender input, .gender label) {
            cursor: pointer;
        }
        .gender label {
            color: #707070;
        }
        .address :where(input, .select-box) {
            margin-top: 15px;
        }

        .select-box {
            width: 100%;
            outline: none;
            border: none;
            color: #707070;
            font-size: 1rem;
        }
        .form button {
            height: 55px;
            width: 100%;
            color: #fff;
            font-size: 1rem;
            font-weight: 400;
            border: none;
            margin-top: 10px;
            border-radius: 10px;
            cursor: pointer;
            transition: all 0.2s ease;
            background: #272727;
        }
        .form button:hover {
            background: #272727;
        }
        /*Responsive*/
        @media screen and (max-width: 500px) {
            .form {
                flex-wrap: wrap;
            }
        }
        .toast{
            display: flex;
            color: red;
            font-weight: 550;
            font-size: 12px;
            text-align: center;
            margin-top: 30px;
            padding: 0;
        }
    </style>
</head>
<body>
<jsp:include page="components/navbar.jsp">
    <jsp:param name="condition" value=""/>
</jsp:include>
<section class="container-form">
    <header>Registration Form</header>
    <form action="AddNewPost" method="post" class="form">
        <select class="select-box" name="collection">
            <option value="">sélectionne une collection</option>
            <c:forEach items="${cards}" var="Card">
                <option value="${Card.getId()}">${Card.getName()}</option>
            </c:forEach>
        </select>
        <div class="input-box">
            <label>Titre</label>
            <input type="text" name="title" placeholder="Entrez un title" />
        </div>
        <div class="input-box">
            <label>Discription</label>
            <input type="text" name="description" placeholder="Entrez une description" />
        </div>
        <div class="input-box">
            <label>Image</label>
            <input type="file" name="image" placeholder="Entrez une image" />
        </div>
        <p class="toast">${errorMessage}</p>
        <button>Ajouter</button>
    </form>
</section>
</body>
</html>
