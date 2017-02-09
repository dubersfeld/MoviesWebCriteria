<spring:message code="title.actorsListWithPhoto" var="pageTitle" />
<spring:message code="date.pattern" var="datePattern" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

  <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="displayContent"><br/>
 
 	<c:forEach items="${actors}" var="actor">
                <spring:message code="personal.id"/>: ${actor.id}<br />
                <spring:message code="personal.firstName"/>: ${actor.firstName}<br />
                <spring:message code="personal.lastName"/>: ${actor.lastName}<br />
                <spring:message code="personal.birthDateDisplay"/>: <fmt:formatDate value="${actor.birthDate}" pattern="${datePattern}" type="date" /><br />              
    		
    
   		<c:choose>
    		<c:when test="${actor.photoAvailable}">
    			<img src="<c:url value="doGetActorPhoto/${actor.photoId}"/>" alt="Photo not found">
    		</c:when>
    		<c:otherwise>
    			<c:out value="Photo not available"/>
    		</c:otherwise>
    	</c:choose>
    	<br/><br/>
    
    
    </c:forEach>
  </jsp:attribute>
  
</template:main>




