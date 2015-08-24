<%@ include file="/common/taglibs.jsp"%>
<html>
<head>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqgrid/4.6.0/css/ui.jqgrid.css">

<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/flick/jquery-ui.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"
	type="text/javascript"></script>


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqgrid/4.6.0/js/jquery.jqGrid.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqgrid/4.6.0/js/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="${ctx}/script/soil-grid.js"></script>

<title>Climate</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#csvExport,#xls_export_all").click(function (e) {
		    /* window.open('data:application/vnd.ms-excel,' + $('#dvData').html()); */
		   window.open('data:application/vnd.ms-excel,' + encodeURIComponent(exportGridToCSV('grid')));
		    e.preventDefault();
		});
	});
 
</script>
<style type="text/css">
</style>
</head>
<div id="load_jqgrid">
	<div id="jqgrid">
		<table id="grid"></table>
		<div id="pager"></div>
		<div class="query_export_buttons">
			<s:submit id="xls_export_all" value="Export XLS" />
		</div>
	</div>
</div>
<form id="formstyle" action="#" method="post" name="formstyle">
	<input type="hidden" name="csvExport" id="csvExport" value="CSV Export" /><input
		type="hidden" name="xlsTitle" id="xlsTitle" value="" />
</form>
</html>