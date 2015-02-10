<%@ include file="/common/taglibs.jsp"%>
<head>
<%-- <script type="text/javascript" src="${ctx}/script/soil-params.js"></script> --%>

</head>

<!-- <h3>Soil Texture</h3> -->
<%-- <s:select name="selectedTextures" listKey="id" listValue="name"
	id="select_textures" list="textures" value="preselectedTextures"
	cssStyle="width:100%;" multiple="true" size="4" required="true" /> --%>
<%-- <h3>Properties</h3>
<s:select name="selectedProperties" listKey="id" listValue="name"
	id="select_properties" list="properties" value="preselectedProperty"
	cssStyle="width:100%;" multiple="true" size="4" required="true" /> --%>
<h3>
	Region <span style="color: #990000; font-size: 10px;">(Initially
		Brazil &#38; Colombia)</span>
</h3>
<s:select name="selectedCountry" listKey="id" listValue="name"
	id="select_country" list="countries" value="preselectedCountry"
	cssClass="map-options-select" multiple="false" required="true" />