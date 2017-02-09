<spring:message code="index.basicDirectorQueries" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="localeContent"><br/>
 
	Language:<br/>
	<a href="?locale=fr_FR">Français</a><br/>
	<a href="?locale=en_US">English</a>
	<br/><br/>
 </jsp:attribute>

 <jsp:attribute name="navigationContent"><br/>
   
 	<a href="<c:url value="/listAllMovies" />"><spring:message code="movieQueries.listAllMovies"/></a><br/>
	<a href="<c:url value="/numberOfMovies" />"><spring:message code="movieQueries.numberOfMovies"/></a><br/>
	<a href="<c:url value="/getMovie" />"><spring:message code="movieQueries.getMovie"/></a><br/>
	<a href="<c:url value="/getSingleMovie" />"><spring:message code="movieQueries.getSingleMovie"/></a><br/>
	<a href="<c:url value="/createMovie" />"><spring:message code="movieQueries.createMovie"/></a><br/>
	<a href="<c:url value="/deleteMovie" />"><spring:message code="movieQueries.deleteMovie"/></a><br/>
	<a href="<c:url value="/updateMovie" />"><spring:message code="movieQueries.updateMovie"/></a><br/>
 	
 	<br/><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>

  </jsp:attribute>

</template:main>




