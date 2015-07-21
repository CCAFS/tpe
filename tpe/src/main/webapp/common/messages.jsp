<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>  
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<% if (request.getAttribute("struts.valueStack") != null) {
%>
<%-- ActionError Messages - usually set in Actions --%>
<s:if test="hasActionErrors()">
	<div class="error" id="errorMessages"><s:iterator value="actionErrors">
		<s:property />
		<br />
	</s:iterator></div>
</s:if>

<%-- FieldError Messages - usually set by validation rules --%>
<s:if test="hasFieldErrors()">
	<div class="error" id="errorMessages"><s:iterator value="fieldErrors">
		<s:iterator value="value">
			<s:property />
			<br />
		</s:iterator>
	</s:iterator></div>
</s:if>

<%-- Action Messages - usually set in Actions --%>
<s:if test="hasActionMessages()">
	<div class="error" id="actionMessages"><s:iterator value="actionMessages">
		<s:property />
		<br />
	</s:iterator></div>
</s:if>

<%
	}
%>

<%-- Success Messages --%>
<c:if test="${not empty messages}">
	<div class="message" id="successMessages"><c:forEach var="msg" items="${messages}">
		<c:out value="${msg}" />
		<br />
	</c:forEach></div>
	<c:remove var="messages" scope="session" />
</c:if>

<%-- Error Messages (on JSPs, not through Struts --%>
<c:if test="${not empty errors}">
	<!--<div class="error" id="errorMessages">
        <c:forEach var="error" items="${errors}">
            <img src="<c:url value="/img/warning.gif"/>"
                alt="<fmt:message key="icon.warning"/>" class="icon" />
            <c:out value="${error}"/><br />
        </c:forEach>
    </div>
    <c:remove var="errors" scope="session"/>
-->
</c:if>
