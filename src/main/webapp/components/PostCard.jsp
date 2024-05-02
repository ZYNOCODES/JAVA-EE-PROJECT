<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <a href="<c:url value="/Post?postID=${param.id}"/>" class="card-a">
        <div class="thumb" style="background-image: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/strange.jpg);"></div>
        <article class="collection-article">
            <h1>${param.title}</h1>
            <p>${param.description}</p>
        </article>
    </a>
    <c:if test="${!param.isVoted}">
        <form method="post" action="Collection">
            <input type="hidden" name="collection" value="${param.collection}"/>
            <input type="hidden" name="post" value="${param.id}"/>
            <input class="card-button" type="submit" value="vote" />
        </form>
    </c:if>
</div>