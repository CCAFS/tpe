<%@ include file="/common/taglibs.jsp"%>
<head>
</head>

<h3>
	Crop <span style="color: #979a9e; font-size: 10px;">(Initially
		Rice &#38; Beans)</span>
</h3>
<s:select name="selectedCrop" listKey="id" listValue="name"
	id="select_crop" list="crops" value="preselectedCrop"
	cssClass="map-options-select" multiple="false" required="true" />
<h3>Cultivar</h3>
<!-- The div for loading the crop cultivars dynamically -->
<div id="params_cultivars"></div>
<h3>
	Region
</h3>
<s:select name="selectedCountry" listKey="id" listValue="name"
	id="select_country" list="countries" value="preselectedCountry"
	cssClass="map-options-select" multiple="false" required="true" />