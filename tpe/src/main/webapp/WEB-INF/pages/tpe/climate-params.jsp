<%@ include file="/common/taglibs.jsp"%>
<head>
<%-- <script type="text/javascript" src="${ctx}/script/climate-params.js"></script>
 --%>
</head>
<h3>Property</h3>
<s:select name="selectedIndicators" listKey="id" listValue="name"
	id="select_indicators" list="indicators" value="preselectedIndicator"
	cssStyle="width:100%;" multiple="true" size="4" required="true" />
<h3>
	Country <span style="color: #990000; font-size: 10px;">(Initially Brazil y Colombia)</span>
</h3>
<s:select name="selectedCountry" listKey="id" listValue="name"
	id="select_country" list="countries" value="preselectedCountry"
	cssStyle="width:100%;" multiple="false" size="2" required="true" />
<!-- <h3>Year(s)</h3> -->
<!-- The div for loading the country years dynamically -->
<!-- <div id="params_years"></div> -->
