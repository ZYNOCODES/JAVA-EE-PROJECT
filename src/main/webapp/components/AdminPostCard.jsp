<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <a href="<c:url value="/Post?postID=${param.id}"/>" class="card-a">
        <div class="thumb" style="background-image: url('../images/${param.image}');"></div>
        <article class="collection-article">
            <h1>${param.title}</h1>
            <p>${param.title}</p>
        </article>
    </a>
    <form class="form-post-card"
          action="Collection" method="get">
        <input type="hidden" name="postID" value="${param.id}"/>
        <input type="hidden" name="action" value="delete"/>
        <input class="card-button" type="submit" value="Delete" name="delete"/>
    </form>
</div>
