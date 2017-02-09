<spring:message code="title.welcome" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">


  <jsp:attribute name="localeContent"><br/>
 
	Language:<br/>
	<a href="?locale=fr_FR">Français</a><br/>
	<a href="?locale=en_US">English</a>
	<br/><br/>
  </jsp:attribute>

  <jsp:attribute name="navigationContent"><br/>
   
	<a href="actorQueries"><spring:message code="index.basicActorQueries"/></a><br/>
   
	<a href="directorQueries"><spring:message code="index.basicDirectorQueries"/></a><br/>

	<a href="movieQueries"><spring:message code="index.basicMovieQueries"/></a><br/>

	<a href="advancedQueries"><spring:message code="index.advancedQueries"/></a>
 	
  </jsp:attribute>

</template:main>