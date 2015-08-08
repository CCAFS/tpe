<%@ include file="/common/taglibs.jsp"%>
<head>
<!-- <script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<script type="text/javascript" src="${ctx}/script/flickr-tpe.js"></script>
<script type="text/javascript" src="${ctx}/script/tpe-tags.js"></script>
<script type="text/javascript">
	
</script>
</head>

<!-- Section for platform Flickr photos -->
<div class="flickr-stream">
	<h4>Flickr Photo Stream</h4>
</div>
<!--  Platform Tags-->
<div class="post-tags">
	<h4>Post Tags</h4>
	<!-- Platform Tags -->
	<div id="tags">
	
	</div>
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<div id="tags-add">
			<button class="button-add" id="send">Add Tag</button>
			<button class="button-list" id="list">View Tags</button>
		</div>
	</security:authorize>
</div>
<!-- Section for platform news -->
<div class="platform-news">
	<h4>Latest News</h4>
	<div class="news-brief">
		<div>
			<h5>
				<a href="#">Making Smart Decision for a food secure</a>
			</h5>
			<h6>2 Feb by Andrea N</h6>
			<p>The CSA-PFaims to guide stalkholders in optmizing national and
				sub-national agricultural</p>
		</div>
	</div>
	<div class="news-brief">
		<div>
			<h5>
				<a href="#">Making Smart Decision for a food secure</a>
			</h5>
			<h6>2 Feb by Andrea N</h6>
			<p>The CSA-PFaims to guide stalkholders in optmizing national and
				sub-national agricultural</p>
		</div>
	</div>
</div>