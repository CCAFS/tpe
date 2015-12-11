<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>TPE</title>
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
			Welcome to the CCAFS Target Population of Environments Platform
			<!-- Welcome to <a href="#">CCAFS-TPE Platform</a> -->
		</h3>
		<div class="overlay-body">
			<h4>What is CCAFS-TPE Platform?</h4>
			<p>The CCAFS-TPE platform is a web-based tool that allows
				visualizing environmental classifications and stress patterns of
				growing environments for agricultural crops.</p>

			<h4>Who is it for?</h4>
			<div id="applied">
				<div id="applied-info">
					<p>The tool has been designed for crop breeders to gain
						understanding on the main stresses and their spatio-temporal
						variations. We believe that model-based classifications can
						positively impact breeding practice by allowing better targeted
						germplasm selection.</p>
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
				</div>
			</div>
		</div>
	</div>
	<%-- <img src="${ctx}/images/aca.png" alt="Foo" height="142" width="142"> --%>
</body>
</html>