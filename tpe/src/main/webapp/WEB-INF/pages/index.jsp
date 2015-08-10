<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>TPE Platform</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/script/home-overlay-dialog.js"></script>
<script>
	$(document).ready(function() {

	});
</script>
</head>

<body>
	<div id="home_container">
		<div id="home_panel">
			<div id="panel-left" class="panel-box-small">
				<img class="middle-img" src="img/left-home.png" height="462"
					width="294" />
			</div>
			<div id="panel-top" class="panel-box-big">
				<img class="middle-img" src="img/top-home.png" height="294"
					width="462" />
			</div>
			<div id="panel-bottom" class="panel-box-big">
				<img class="middle-img" src="img/bottom-home.png" height="294"
					width="462" />
			</div>
			<div id="panel-right" class="panel-box-small">
				<img class="middle-img" src="img/right-home.png" height="462"
					width="294" />
			</div>
		</div>
	</div>
	<!-- The home panel infos-->
	<div id="home-panel-infos">
		<h2></h2>
		<span id="panel-info">Infos</span>
		<div id="panel-img"></div>
	</div>
	<div id="popup" class="modal-box">
		<header>
			<a href="#" class="js-modal-close close">×</a>
			<h3>
				<!-- <a href="#">TPE Platform</a> -->
				Welcome
			</h3>
		</header>
		<div class="modal-body">
			<h4>What is CCAFS-TPE Platform?</h4>
			<p>CCAFS-TPE Platform, is an online visualization of the Target
				Population of Environments using Climate, Soil and Stability data
				for Lowland rice, Upland rice and Rice initially from Colombia,
				Brazil and Latin America respectively.</p>

			<h4>What you can do with TPE Platform?</h4>
			<p>The platform provides results as Google maps overlays and
				dynamic interactive graphics.</p>
			<p>You can also export the graphics to different formats.</p>

			<h4>You can also get involved.</h4>
			<p>The platform allows you to get involved by contributing your
				methodology and data. There is a get involved section where you can
				contact us for more information.</p>
		</div>
		<footer>
			<a href="#" class="js-modal-close">Close</a>
		</footer>
	</div>
	<a class="js-open-modal" href="#" data-modal-id="popup"> Click me </a>
</body>
</html>