<spring:message code="title.directorActorsSuccess" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

  <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="displayContent"><br/>
	
	<spring:message code="advancedQueries.directorActorsSuccess">
		<spring:argument value="${firstName}"/>
		<spring:argument value="${lastName}"/>
	</spring:message>
	<br/><br/>

	<c:forEach items="${actors}" var="actor">
           <c:out value = "${actor.firstName} ${actor.lastName}"/><br />
                                    
    </c:forEach>

  </jsp:attribute>
  
</template:main>