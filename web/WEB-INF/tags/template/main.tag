<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="htmlTitle" type="java.lang.String" rtexprvalue="true"
              required="true" %>
<%@ attribute name="bodyTitle" type="java.lang.String" rtexprvalue="true"
              required="true" %>
<%@ attribute name="headContent" fragment="true" required="false" %>
<%@ attribute name="navigationContent" fragment="true" required="true" %>
<%@ attribute name="formContent" fragment="true" required="false" %>
<%@ attribute name="displayContent" fragment="true" required="false" %>
<%@ attribute name="localeContent" fragment="true" required="false" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="title.welcome" /> ::
            <c:out value="${fn:trim(htmlTitle)}" /></title>
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css" />
        <link rel="stylesheet"
              href="<c:url value="/resources/stylesheet/main.css" />" />
        
        <jsp:invoke fragment="headContent" />
    </head>
    
    
    <body>
    	<h2><c:out value="${fn:trim(bodyTitle)}" /></h2>
    	<jsp:invoke fragment="localeContent" />
     	<jsp:invoke fragment="navigationContent" />
     	
     	<br/><br/>
     	<jsp:invoke fragment="formContent" />
     	<jsp:invoke fragment="displayContent" />
     
    </body>
    
</html>

