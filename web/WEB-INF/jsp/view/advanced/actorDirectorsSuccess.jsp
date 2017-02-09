<spring:message code="title.actorDirectorsSuccess" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="displayContent"><br/>
	
	<spring:message code="advancedQueries.actorDirectorsSuccess">
		<spring:argument value="${firstName}"/>
		<spring:argument value="${lastName}"/>
	</spring:message>
	<br/><br/>

	<c:forEach items="${directors}" var="director">
       <c:out value="${director.firstName} ${director.lastName}"/><br />
                                       
    </c:forEach>

  </jsp:attribute>
  
</template:main>