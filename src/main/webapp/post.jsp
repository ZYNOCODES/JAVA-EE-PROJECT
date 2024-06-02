<%--
  Created by IntelliJ IDEA.
  User: lv
  Date: 4/27/2024
  Time: 7:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="components/header.jsp"/>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Poppins", sans-serif;
        }

        body {
            background: #272727;
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
            background: #ABF600;
            margin: 0;
            z-index: 99;
        }
        .navbar-brand{
            color: #272727;
        }
        .nav-link{
            display: block;
            padding: 0.5rem 1rem;
            border-radius: 10px;
            color: #ABF600;
            font-weight: 500;
            text-decoration: none;
            background-color: #272727;
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
            color: #272727;
            font-weight: 600;
            font-size: 16px;
        }

        img {
            width: 100%;
            display: block;
        }

        p {
            line-height: 1.3;
            color: white
        }
        @media (min-width: 1170px) {
            p {
                font-size: 1.2rem;
            }
        }

        .two-thirds-image-left-one-third-text-right {
            position: relative;
        }
        @media (min-width: 700px) {
            .two-thirds-image-left-one-third-text-right {
                display: grid;
                grid-template-columns: repeat(12, 1fr);
                grid-column-gap: 1rem;
            }
        }
        @media (min-width: 1170px) {
            .two-thirds-image-left-one-third-text-right {
                grid-column-gap: 2rem;
            }
        }
        @media (min-width: 700px) {
            .two-thirds-image-left-one-third-text-right .image {
                grid-column: 1/8;
                height: 100%;
            }
            .two-thirds-image-left-one-third-text-right .image img {
                height: 100vh;
                -o-object-fit: cover;
                object-fit: cover;
                position: sticky;
                top: 0;
            }
        }
        @media (min-width: 1170px) {
            .two-thirds-image-left-one-third-text-right .image {
                grid-column: 1/9;
            }
        }
        .two-thirds-image-left-one-third-text-right .text {
            padding: 1rem;
        }
        @media (min-width: 700px) {
            .two-thirds-image-left-one-third-text-right .text {
                grid-column: 8/span all;
            }
        }
        @media (min-width: 1170px) {
            .two-thirds-image-left-one-third-text-right .text {
                grid-column: 9/span all;
            }
        }
        .two-thirds-image-left-one-third-text-right .text .title-content {
            color: white;
            font-size: 3rem;
            margin: 2rem 0;
        }
        @media (min-width: 700px) {
            .two-thirds-image-left-one-third-text-right .text .title-content {
                font-size: 4rem;
            }
        }
        .two-thirds-image-left-one-third-text-right .text .lead {
            font-style: italic;
            font-weight: 400;
            font-size: 1.2rem;
            margin: 1rem 0 3rem;
            line-height: 1.5;
        }
        @media (min-width: 700px) {
            .two-thirds-image-left-one-third-text-right .text .lead {
                font-size: 1.4rem;
            }
        }

        .one-by-one {
            display: grid;
            grid-template-columns: 1fr;
            grid-gap: 0.5rem;
            margin: 0.5rem;
        }

        .wide-tall-cap-left {
            display: grid;
            grid-template-columns: 1fr;
            grid-gap: 0.5rem;
            margin: 0.5rem;
        }
        @media (min-width: 600px) {
            .wide-tall-cap-left {
                grid-template-columns: repeat(12, 1fr);
            }
        }
        @media (min-width: 600px) {
            .wide-tall-cap-left .image-1 {
                grid-row: 1;
                grid-column: 1/-1;
            }
        }
        @media (min-width: 600px) {
            .wide-tall-cap-left .image-2 {
                grid-row: 2;
                grid-column: 6/span 7;
            }
        }
        .wide-tall-cap-left .caption {
            max-width: 70vw;
        }
        @media (min-width: 600px) {
            .wide-tall-cap-left .caption {
                font-size: 0.8rem;
                margin: 2rem 0.5rem;
            }
        }
        @media (min-width: 600px) {
            .wide-tall-cap-left .caption-1 {
                grid-row: 2;
                grid-column: 1/span 2;
            }
        }
        @media (min-width: 600px) {
            .wide-tall-cap-left .caption-2 {
                grid-row: 2;
                grid-column: 3/span 2;
            }
        }

        .three-col-text {
            -moz-columns: 3;
            columns: 3;
            -moz-column-width: 300px;
            column-width: 300px;
            padding: 1rem;
        }
        .three-col-text p:first-child {
            margin-top: 0;
        }

    </style>
</head>
<body>
    <jsp:include page="components/navbar.jsp">
        <jsp:param name="condition" value="updatepost"/>
        <jsp:param name="id" value="${post.getId()}"/>
    </jsp:include>
    <c:choose>
        <c:when test="${error != null && !error.isEmpty()}">
            <div>
                <h1>${error}</h1>
            </div>
        </c:when>
        <c:otherwise>
            <div class="two-thirds-image-left-one-third-text-right">
                <div class="image"><img src="../images/${post.getImage()}"/></div>
                <div class="text">
                    <h1 class="title-content">${post.getTitle()}</h1>
                    <p>${post.getDescription()}</p>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>
