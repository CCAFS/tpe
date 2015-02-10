<%@ include file="/common/taglibs.jsp"%>

<s:select name="selectedCultivar" listKey="id" listValue="name"
	id="select_cultivar" list="cultivars" value="preselectedCultivar"
	cssClass="map-options-select" multiple="false" required="true" />