<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${param.condition}">
        <c:set var="toastMessage" value="Voting has expired forrr this collection" />
        <c:set var="onclick" value="showToast('${toastMessage}')" />
        <div class="card">
            <a href="<c:url value="/VotingResult?cardID=${param.id}"/>" class="card-a">
                <div class="thumb" style="background-image: url('../images/${param.image}');"></div>
                <article class="collection-article">
                    <h1>${param.name}</h1>
                    <p>${param.description}</p>
                    <span>expire en ${param.endDate}</span>
                </article>
            </a>
        </div>
    </c:when>
    <c:otherwise>
        <div class="card">
            <a href="<c:url value="/Collection?cardID=${param.id}"/>" class="card-a">
                <div class="thumb" style="background-image: url('../images/${param.image}');"></div>
                <article class="collection-article">
                    <h1>${param.name}</h1>
                    <p>${param.description}</p>
                    <span>expire en ${param.endDate}</span>
                </article>
            </a>
        </div>
    </c:otherwise>
</c:choose>