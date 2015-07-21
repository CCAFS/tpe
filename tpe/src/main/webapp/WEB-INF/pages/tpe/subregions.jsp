<%@ include file="/common/taglibs.jsp"%>

<s:select name="selectedRegions" listKey="id" listValue="name"
	id="select_regions" list="regions" value="preselectedRegions"
	cssStyle="width:100%;" multiple="true" required="true" />