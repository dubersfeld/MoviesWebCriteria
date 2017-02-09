<spring:message code="title.directorsList" var="pageTitle" />
<spring:message code="date.pattern" var="datePattern" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

  <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="displayContent"><br/>
 
 	<c:forEach items="${directors}" var="director">
                <spring:message code="personal.id"/>: ${director.id}<br />
                <spring:message code="personal.firstName"/>: ${director.firstName}<br />
                <spring:message code="personal.lastName"/>: ${director.lastName}<br />
                <spring:message code="personal.birthDateDisplay"/>: <fmt:formatDate value="${director.birthDate}" pattern="${datePattern}" type="date" /><br /><br />              
    </c:forEach>
  
  </jsp:attribute>
  
</template:main>




