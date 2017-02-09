<spring:message code="title.noResultDirectorActors" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

  <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
	<a href="<c:url value="${backPage}"/>"><spring:message code="backPage"/></a>
  </jsp:attribute>
  
  <jsp:attribute name="displayContent"><br/>
  
  	<spring:message code="advancedQueries.directorActorsNoResult">
		<spring:argument value="${firstName}"/>
		<spring:argument value="${lastName}"/>
	</spring:message>
  	
  </jsp:attribute>
  
</template:main>