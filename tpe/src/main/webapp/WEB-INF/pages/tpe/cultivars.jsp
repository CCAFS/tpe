<%@ include file="/common/taglibs.jsp"%>

<s:select name="selectedCultivar" listKey="id" listValue="name"
	id="select_cultivar" list="cultivars" value="preselectedCultivar"
	cssStyle="width:100%;" multiple="false" size="1" required="true" />