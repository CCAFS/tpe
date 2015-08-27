<%@ include file="/common/taglibs.jsp"%>
<head>
</head>
<h3>
	Region
</h3>
<s:select name="selectedCountry" listKey="id" listValue="name"
	id="select_country" list="countries" value="preselectedCountry"
	cssClass="map-options-select" multiple="false" required="true" />
