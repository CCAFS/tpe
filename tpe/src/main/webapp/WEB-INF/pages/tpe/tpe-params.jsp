<%@ include file="/common/taglibs.jsp"%>
<head>
 <script type="text/javascript" src="${ctx}/script/tpe-params.js"></script>
</head>

<h3>Crop</h3>
<s:select name="selectedCrop" listKey="id" listValue="name"
	id="select_crop" list="crops" value="preselectedCrop"
	cssStyle="width:100%;" multiple="false" size="1" required="true" />
<h3>Cultivar</h3>
<s:select name="selectedCultivar" listKey="id" listValue="name"
	id="select_cultivar" list="cultivars" value="preselectedCultivar"
	cssStyle="width:100%;" multiple="false" size="1" required="true" />
<h3>Country</h3>
<s:select name="selectedCountry" listKey="id" listValue="name"
	id="select_country" list="countries" value="preselectedCountry"
	cssStyle="width:100%;" multiple="false" size="1" required="true" />
<h3>Year(s)</h3>
<s:select name="selectedYears" id="select_years" list="years"
	value="preselectedYear" cssStyle="width:100%;" multiple="true" size="4"
	required="true" />
<h3>Sowing Window</h3>
<s:select name="selectedWindow" id="select_window" list="swindows"
	value="preselectedWindow" cssStyle="width:100%;" multiple="false"
	size="1" required="true" />