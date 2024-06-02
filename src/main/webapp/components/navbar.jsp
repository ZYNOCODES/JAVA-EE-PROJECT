<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar" id="mainNav">
    <div class="container">
        <h2 class="navbar-brand">VOTING</h2>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav">
                <c:if test="${sessionScope.token.getType() == 'admin'}">
                    <c:choose>
                        <c:when test="${param.condition == 'collection'}">
                            <li class="nav-item">
                                <form action="AddNewCollection" method="get">
                                    <input type="submit" class="nav-link py-3 px-0 px-lg-3 rounded" value="add new collection">
                                </form>
                            </li>
                        </c:when>
                        <c:when test="${param.condition == 'post'}">
                            <li class="nav-item">
                                <form action="AddNewPost" method="get">
                                    <input type="submit" class="nav-link py-3 px-0 px-lg-3 rounded" value="add new post">
                                </form>
                            </li>
                        </c:when>
                        <c:when test="${param.condition == 'updatepost'}">
                            <li class="nav-item">
                                <a href="UpdatePost?postID=${param.id}">
                                    <input type="button" class="nav-link py-3 px-0 px-lg-3 rounded" value="update post">
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <!-- Default case if none of the conditions are met -->
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <li class="nav-item mx-0 mx-lg-1">
                    <form id="logout-form" action="Logout" method="post">
                        <input type="submit" class="nav-link py-3 px-0 px-lg-3 rounded" value="Logout">
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>
