<%@ include file="/common/taglibs.jsp"%>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		//Footer Items
		$('.colombia').hover(function() {
			$("img", this).attr("src", "${ctx}/img/work-colombia-hover.png");
		}, function() {
			$("img", this).attr("src", "${ctx}/img/work-colombia.png");
		});

		$('.brazil').hover(function() {
			$("img", this).attr("src", "${ctx}/img/work-brazil-hover.png");
		}, function() {
			$("img", this).attr("src", "${ctx}/img/work-brazil.png");
		});

		$('.lamerica').hover(function() {
			$("img", this).attr("src", "${ctx}/img/work-lamerica-hover.png");
		}, function() {
			$("img", this).attr("src", "${ctx}/img/work-lamerica.png");
		});

	});
</script>


<!--Footer section-->
<div class="footer">
	<div id="where-work-main">
		<div style="float: left;">
			<h5 style="clear: right; width: 150px; color: #fff;">Where we
				work</h5>
		</div>
		<div id="where-work" style="clear: right;">
			<table style="width: 100%;">
				<colgroup>
					<col width="33%" />
					<col width="33%" />
					<col />
				</colgroup>
				<tr>
					<td><div class="col-left colombia">
							<a href="${ctx}/casestudy/colombian.jspx"><img
								src="${ctx}/img/work-colombia.png" alt="Colombia" /> </a>
						</div>
						<div class="col-right">
							<a href="${ctx}/casestudy/colombian.jspx">Colombia <br />Lowland
								Rice
							</a>
						</div></td>
					<td>
						<div class="col-left brazil">
							<a href="${ctx}/casestudy/brazilian.jspx"><img
								src="${ctx}/img/work-brazil.png" alt="Brazil" /> </a>
						</div>
						<div class="col-right">
							<a href="${ctx}/casestudy/brazilian.jspx">Brazil <br />Upland
								Rice
							</a>
						</div>
					</td>
					<td><div class="col-left lamerica">
							<a href="${ctx}/casestudy/lamerica.jspx"><img
								src="${ctx}/img/work-lamerica.png" alt="Latin-America" /> </a>
						</div>
						<div class="col-right">
							<a href="${ctx}/casestudy/lamerica.jspx">Latin America <br />Rice
							</a>
						</div></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="footer_contents">
		<table>
			<tr>
				<td><div class="collab">
						<a href="http://ciat.cgiar.org/" target="_blank"><img
							src="${ctx}/img/logo_ciat.png" alt="CIAT" /> </a>
					</div></td>
				<td class="left-border">
					<div class="collab">
						<a href="http://ccafs.cgiar.org/" target="_blank"><img
							src="${ctx}/img/logo.png" alt="CCAFS" /> </a>
					</div>
				</td>
				<td class="left-border"><div class="collab">
						<a href="https://www.embrapa.br/" target="_blank"><img
							src="${ctx}/img/logo_embrapa.png" alt="EMBRAPA" /> </a>
					</div></td>
			</tr>

			<tr>
				<td></td>
				<td><div id="tpe_copyright">
						<a href="http://www.ccafs-tpe.org" target="_blank">CCAFS
							Target Population Environments Platform</a> <br /> Copyright &#169;
						2014
					</div></td>
				<td><div id="follow_us">
						<a href="https://twitter.com/ccafstpe"><img
							src="${ctx}/img/twitter.png" alt="twitter" /></a>&nbsp<a
							href="www.facebook.com/ccafstpe"><img
							src="${ctx}/img/facebook.png" alt="facebook" /></a>&nbsp<a
							href="www.youtube.com/ccafstpe"><img
							src="${ctx}/img/youtube.png" alt="in" /></a>&nbsp<a
							href="www.flickr.com/tpe_platform"><img
							src="${ctx}/img/flickr.png" alt="in" /></a>&nbsp<a
							href="www.rssfeed.com/ccafs_tpe"><img
							src="${ctx}/img/rss.png" alt="email" /></a>
					</div></td>
			</tr>
		</table>

	</div>
</div>