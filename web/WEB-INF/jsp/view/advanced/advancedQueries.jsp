<spring:message code="index.advancedQueries" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">


	<jsp:attribute name="localeContent"><br/>
		Language:<br/>
		<a href="?locale=fr_FR">Français</a><br/>
		<a href="?locale=en_US">English</a>
		<br/><br/>
 	</jsp:attribute>

   	<jsp:attribute name="navigationContent"><br/>
   
 		<a href=<c:url value="/actorMovies" />><spring:message code="advancedQueries.actorMovies"/></a><br/>
		<a href="<c:url value="/movieActors" />"><spring:message code="advancedQueries.movieActors"/></a><br/>
		<a href="<c:url value="/directorMovies" />""><spring:message code="advancedQueries.directorMovies"/></a><br/>
		<a href="<c:url value="/directorActors" />"><spring:message code="advancedQueries.directorActors"/></a><br/>
		<a href="<c:url value="/actorDirectors" />"><spring:message code="advancedQueries.actorDirectors"/></a><br/>
		<a href="<c:url value="/createActorMovie" />"><spring:message code="advancedQueries.createActorMovie"/></a><br/>
		<a href="<c:url value="/createActorMovieTrans" />">Transaction</a><br/>
 	
		<br/><br/>

		<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>

	</jsp:attribute>

</template:main>




