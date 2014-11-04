<%@ include file="/common/taglibs.jsp"%>
<head>
<script type='text/javascript'>
	$(document).ready(function() {
	});
</script>
</head>

<s:select name="selectedRegions" listKey="id" listValue="name"
	id="select_regions" list="regions" value="preselectedRegions"
	cssStyle="width:100%;" multiple="true" size="4" required="true" />