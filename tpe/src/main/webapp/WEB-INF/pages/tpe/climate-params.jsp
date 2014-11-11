<%@ include file="/common/taglibs.jsp"%>
<head>
<script type="text/javascript" src="${ctx}/script/climate-params.js"></script>

</head>
<h3>Property</h3>
<s:select name="selectedProperties" listKey="id" listValue="name"
	id="select_properties" list="properties" value="preselectedProperty"
	cssStyle="width:100%;" multiple="true" size="4" required="true" />
<h3>Country <span class="select_info">(Initially Brazil y Colombia)</span></h3>
<s:select name="selectedCountry" listKey="id" listValue="name"
	id="select_country" list="countries" value="preselectedCountry"
	cssStyle="width:100%;" multiple="false" size="1" required="true" />
<h3>Sub Region(s)</h3>
<!-- <div id="params_regions"></div> -->
<s:select name="selectedRegions" listKey="id" listValue="name"
	id="select_regions" list="regions" value="preselectedRegions"
	cssStyle="width:100%;" multiple="true" size="4" required="true" />
<h3>Year(s)</h3>
<s:select name="selectedYears" id="select_years" list="years"
	value="preselectedYear" cssStyle="width:100%;" multiple="true" size="4"
	required="true" />
<h3>Station(s)</h3>
<s:select name="selectedStations" listKey="id" listValue="name"
	id="select_stations" list="stations" value="preselectedstations"
	cssStyle="width:100%;" multiple="true" size="4" required="true" />
