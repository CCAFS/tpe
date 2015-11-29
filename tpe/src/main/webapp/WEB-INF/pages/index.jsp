<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>TPE Platform</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script type="text/javascript"
	src="${ctx}/script/home-overlay-dialog.js"></script>
<script type="text/javascript">
	
</script>
</head>

<body>
	<div id="home_container">
		<div id="home_panel">
			<div id="panel-left" class="panel-box-small">
				<img class="middle-img" src="${ctx}/img/1-left.png" height="462"
					width="294" />
			</div>
			<div id="panel-top" class="panel-box-big">
				<img class="middle-img" src="${ctx}/img/2-top.png" height="294"
					width="462" />
			</div>
			<div id="panel-bottom" class="panel-box-big">
				<img class="middle-img" src="${ctx}/img/4-bottom.png" height="294"
					width="462" />
			</div>
			<div id="panel-right" class="panel-box-small">
				<img class="middle-img" src="${ctx}/img/3-right.png" height="462"
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

	<div id="overlay-dialog">
		<a href="#" class="close">X</a>
		<h3>
			Welcome to <a href="#">CCAFS-TPE Platform</a>
		</h3>
		<div class="overlay-body">
			<h4>What is CCAFS-TPE Platform?</h4>
			<p>CCAFS-TPE Platform, is an online visualization of the Target
				Population of Environments using Climate, Soil and Stability data
				for Lowland rice, Upland rice and Rice initially from Colombia,
				Brazil and Latin America respectively.</p>

			<h4>Who are you?</h4>
			<div id="applied">
				<div id="applied-info">
					<p>Applications of CCAFS TPE Methodology includes visualization
						of Target Population of Environments for Upland and Lowland rice.
					</p>
					<p>
						<a href="#">See how CCAFS-TPE can help or applied to your
							specific research.</a>
					</p>
					<p>
						<input type="checkbox" id="dont-show-again">Don't show
						this again
					</p>
				</div>
				<div id="applied-list">
					<div id="app-res" class="apps">
						<img class="middle-img" src="${ctx}/img/app-res.png" height="100"
							width="100" />
						<h5>Researcher</h5>
					</div>
					<div id="app-aca" class="apps">
						<img class="middle-img" src="${ctx}/img/app-aca.png" height="100"
							width="100" />
						<h5>Academic</h5>
					</div>
					<div id="app-pol" class="apps">
						<img class="middle-img" src="${ctx}/img/app-pol.png" height="100"
							width="100" />
						<h5 style="text-align: center;">Policy or</h5>
						<h5 style="text-align: center;">Decision Marker</h5>
					</div>
					<div id="app-gov" class="apps">
						<img class="middle-img" src="${ctx}/img/app-gov.png" height="100"
							width="100" />
						<h5 style="text-align: center;">Governmental</h5>
						<h5 style="text-align: center;">Staff</h5>
					</div>
				</div>
			</div>
		</div>
	</div>
<%-- <img src="${ctx}/images/aca.png" alt="Foo" height="142" width="142"> --%>
</body>
</html>