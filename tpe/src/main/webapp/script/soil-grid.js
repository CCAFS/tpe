$(document)
		.ready(
				function() {

					_title = "Soil Data";
					$
							.ajax({
								type : "GET",
								url : "soilGrid.action",
								data : $('#params_form').serialize(),
								dataType : "json",
								success : function(result) {
									var columnData = result.gridModel, columnNames = result.colNames, columnModel = result.colModel, rws = result.rows, pg = result.page, tot = result.total, rec = result.record,captionTitle=result.captionTitle;

									$("#grid").jqGrid({

										datatype : 'local',
										data : columnData,
										colNames : columnNames,
										colModel : columnModel,
										gridview : true,
										pager : "#pager",
										height : "auto",
										// width : "auto",
										rownumbers : true,
										autowidth : true,
										// shrinkToFit : true,
										// forceFit : true,
										shrinkToFit : false,
										// scroll : true,
										caption : captionTitle,
										emptyrecords : "Empty records",
										// height : 200,
										rowNum : 50,
										rowList : [ 50, 100, 150 ],
										viewrecords : true,
										// cellEdit : true,
										toppager : true,
										cloneToTop : true
									// mtype : 'POST',

									}); // end jqgrid;
									// jq("#grid").jqGrid('navGrid', '#pager');
									$("#grid").jqGrid('navGrid', '#pager', {
										search : false,
										refresh : false,
										reloadAfterSubmit : true,
										refreshtext : 'Reload',
										viewtext : 'View',
										cloneToTop : true,
										view : true
									}, {});
								}
							});
				});