<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <a href="<c:url value="/Post?postID=${param.id}"/>" class="card-a">
        <div class="thumb" style="background-image: url('../images/${param.image}');"></div>
        <article class="collection-article">
            <h1>${param.title}</h1>
            <p>${param.description}</p>
        </article>
    </a>
    <c:if test="${!param.isVoted}">
        <form id="postcard" class="form-post-card" method="post" action="Collection">
            <input type="hidden" name="collection" value="${param.collection}"/>
            <input type="hidden" name="post" value="${param.id}"/>
            <input class="card-button" type="submit" value="vote" onclick="submitForm()"/>
        </form>
    </c:if>
</div>