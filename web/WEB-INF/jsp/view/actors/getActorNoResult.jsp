<spring:message code="title.noResultActor" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
 	<a href="<c:url value="${backPage}" />"><spring:message code="backPage"/></a><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
</template:main>