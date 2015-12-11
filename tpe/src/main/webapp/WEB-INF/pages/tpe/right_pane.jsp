<%@ include file="/common/taglibs.jsp"%>
<head>
<!-- <script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<script type="text/javascript" src="${ctx}/script/tpe-tags.js"></script>
<script type="text/javascript">
	
</script>
</head>

<!-- Section for platform news -->
<div class="platform-news">
	<h4>Latest News</h4>
	<div class="news-brief">
		<div>
			<h5>
				<a href="#[hyperlink to WP]">TPE project Working Paper published</a>
			</h5>
			<!-- <h6>2 Feb by Andrea N</h6> -->
			<p>
				Check out our recently published CCAFS Working Paper, providing a
				complete summary of our project.<a href="#[hyperlink to WP]">Read
					the paper here.</a>
			</p>
		</div>
	</div>
	<div class="news-brief">
		<div>
			<h5>
				<a href="#">Upland rice in Central Brazil</a>
			</h5>
			<!-- <h6>2 Feb by Andrea N</h6> -->
			<p>
				TPE project analyses reveal that the upland rice breeding program
				could increase its impact four-fold if targeted germplasm selection
				under drought is performed as part of the breeding process (read
				more) [hyperlink to Brazilian upland rice case study]. Check out our
				paper in Journal of Experimental Botany, <a
					href="http://jxb.oxfordjournals.org/content/66/12/3625">here </a>
				to rice paper.
			</p>
		</div>
	</div>
	<div class="news-brief">
		<div>
			<h5>
				<a href="#">Common bean in Goiás, Brazil</a>
			</h5>
			<!-- <h6>2 Feb by Andrea N</h6> -->
			<p>
				Analyses for common bean (Phaseolus vulgaris L.) indicate that
				drought occurs roughly one in every four seasons across Goiás, with
				yield reductions of up to 50 %. Germplasm selection under drought
				may not be warranted (read more) <a href="${ctx}/casestudy/index.jspx#common-beans-casestudy"> common bean case study. </a>
			</p>
		</div>
	</div>
	<div class="news-brief">
		<div>
			<h5>
				<a href="#">Shifting breeding priorities for sorghum and wheat
					in Australia</a>
			</h5>
			<!-- <h6>2 Feb by Andrea N</h6> -->
			<p>
				Recent research led on sorghum and wheat shows that, during the 21st
				century, drought frequency decreases while the relative importance
				of heat stress increases. Australian sorghum and wheat breeding
				programs need to adjust accordingly<a
					href="http://onlinelibrary.wiley.com/doi/10.1111/gcb.13022/abstract">
					read more.</a>
			</p>
		</div>
	</div>
	<div class="news-brief">
		<div>
			<h5>
				<a href="#">Drought stress profiles for maize in Europe</a>
			</h5>
			<!-- <h6>2 Feb by Andrea N</h6> -->
			<p>
				Harrison and colleagues recently applied a similar method to the TPE
				approach. Their findings suggest increases in frequency of severe
				drought stress across continental Europe. Countering these effects
				will require breeding to focus on earlier maturity<a
					href="http://onlinelibrary.wiley.com/doi/10.1111/gcb.12381/abstract">
					read more </a>
			</p>
		</div>
	</div>
</div>
<!--  Platform Tags-->
<div class="post-tags">
	<h4>Project Keywords</h4>
	<!-- Platform Tags -->
	<div id="tags"></div>
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<div id="tags-add">
			<button class="button-add" id="send"
				onclick="location.href='/tpe/admin/tag/add.jspx'">Add
				Keyword</button>
			<button class="button-add" id="list"
				onclick="location.href='/tpe/admin/tag/list.jspx'">View
				Keywords</button>
		</div>
	</security:authorize>
</div>