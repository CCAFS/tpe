<%@ include file="/common/taglibs.jsp"%>
<head>
<%-- <script type="text/javascript" src="${ctx}/script/tpe-params.js"></script> --%>
</head>

<h3>
	Crop <span style="color: #990000; font-size: 10px;">(Initially Rice y Beans)</span>
</h3>
<s:select name="selectedCrop" listKey="id" listValue="name"
	id="select_crop" list="crops" value="preselectedCrop"
	cssStyle="width:100%;" multiple="false" size="2" required="true" />
<h3>Cultivar</h3>
<!-- The div for loading the crop cultivars dynamically -->
<div id="params_cultivars"></div>
<h3>
	Country <span style="color: #990000; font-size: 10px;">(Initially Brazil y Colombia)</span>
</h3>
<s:select name="selectedCountry" listKey="id" listValue="name"
	id="select_country" list="countries" value="preselectedCountry"
	cssStyle="width:100%;" multiple="false" size="2" required="true" />
<!-- <h3>Year(s)</h3> -->
<!-- The div for loading the country years dynamically -->
<!-- <div id="params_years"></div> -->
<h3>Scenario</h3>
<s:select name="selectedScenario" id="select_scenario" list="scenarios"
	value="preselectedScenario" cssStyle="width:100%;" multiple="false"
	size="3" required="true" />
<%-- <h3>Sowing Window</h3>
<s:select name="selectedWindow" id="select_window" list="swindows"
	value="preselectedWindow" cssStyle="width:100%;" multiple="false"
	size="2" required="true" /> --%>