<%@ include file="/common/taglibs.jsp"%>
<head>
<%-- <script type="text/javascript" src="${ctx}/script/soil-params.js"></script> --%>

</head>

<h3>Soil Texture</h3>
<s:select name="selectedTextures" listKey="id" listValue="name"
	id="select_textures" list="textures" value="preselectedTexture"
	cssStyle="width:100%;" multiple="true" size="4" required="true" />
<h3>Properties</h3>
<s:select name="selectedProperties" listKey="id" listValue="name"
	id="select_properties" list="properties" value="preselectedProperty"
	cssStyle="width:100%;" multiple="true" size="4" required="true" />
<h3>Country <span class="select_info">(Initially Brazil y Colombia)</span></h3>
<s:select name="selectedCountry" listKey="id" listValue="name"
	id="select_country" list="countries" value="preselectedCountry"
	cssStyle="width:100%;" multiple="false" size="1" required="true" />
<h3>Sub Region(s)</h3>
<!-- The div for loading the country sub regions dynamically -->
<div id="params_regions"></div>