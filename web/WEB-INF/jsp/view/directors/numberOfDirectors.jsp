<spring:message code="title.numberOfDirectors" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

  <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="displayContent"><br/>
  
 	<spring:message code="numberOfDirectors.result">
		<spring:argument value="${number}"/>
	</spring:message>
  
  </jsp:attribute>
  
</template:main>




