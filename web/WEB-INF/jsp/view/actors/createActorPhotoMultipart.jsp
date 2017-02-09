<spring:message code="title.createActorPhoto" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="formContent"><br/>
  
  	<h2><spring:message code="createActorPhoto.photoData"/></h2>
	<form:form method="POST" enctype="multipart/form-data" action="createActorPhotoMulti" modelAttribute="actorPhotoMulti">
   	<table>
    <tr>
    	<td><form:label path="uploadedFile"><spring:message code="createActorPhoto.photoFile"/>:</form:label></td>
        <td><input type="file" name="uploadedFile" /></td>
        <td><form:errors path="uploadedFile" class="errors"/></td>  
    </tr>	
    <tr>
        <td><form:label path="actorId"><spring:message code="createActorPhoto.actorId"/>:</form:label></td>
        <td><form:input path="actorId" /></td>
        <td><form:errors path="actorId" class="errors"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="form.valid"/>"/>
        </td>
    </tr>
  	</table>  
	</form:form>
  
  </jsp:attribute>
  
  

</template:main>




