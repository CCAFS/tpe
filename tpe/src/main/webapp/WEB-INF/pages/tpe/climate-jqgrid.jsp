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
<script type="text/javascript" src="${ctx}/script/climate-grid.js"></script>

<title>Climate</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#csvExport,#xls_export_all").click(function (e) {
		    /* window.open('data:application/vnd.ms-excel,' + $('#dvData').html()); */
		   window.open('data:application/vnd.ms-excel,' + encodeURIComponent(exportGridToCSV('grid')));
		    e.preventDefault();
		});
	});

	function exportGridToCSV(table) {
		mya = $("#" + table).getDataIDs(); // Get All IDs
		var data = $("#" + table).getRowData(mya[0]); // Get First row to get the
		// labels
		var colNames = new Array();
		var ii = 0;
		for ( var i in data) {
			colNames[ii++] = i;
		} // capture col names

		var html = "<html><head>"
				+ "<style script=&quot;css/text&quot;>"
				+ "table.tableList_1 th {border:1px solid black; text-align:center; "
				+ "vertical-align: middle; padding:5px;}"
				+ "table.tableList_1 td {border:1px solid black; text-align: left; vertical-align: top; padding:5px;}"
				+ "</style></head>"
				+ "<body style=&quot;page:land;&quot;><table>";

		for (var k = 0; k < colNames.length; k++) {
			html = html + "<th>" + colNames[k] + "</th>";
		}
		html = html + "</tr>"; // Output header with end of line
		for (i = 0; i < mya.length; i++) {
			html = html + "<tr>";
			data = $("#" + table).getRowData(mya[i]); // get each row
			for (var j = 0; j < colNames.length; j++) {
				html = html + "<td>" + data[colNames[j]] + "</td>"; // output each Row as
				// tab delimited
			}
			html = html + "</tr>"; // output each row with end of line
		}
		html = html + "</table></body></html>"; // end of line at the end
		//alert(html);
		html = html.replace(/'/g, '&apos;');
		
		
		return html;
		//  var form = "<form name='pdfexportform' action='generategrid' method='post'>";
		//  form = form + "<input type='hidden' name='pdfBuffer' value='" + html + "'>";
		//  form = form + "</form><script>document.pdfexportform.submit();</sc"
//	      + "ript>";
		//  OpenWindow = window.open('', '');
		//  OpenWindow.document.write(form);
		//  OpenWindow.document.close();
	}
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