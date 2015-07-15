<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>TPE Platform</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/script/slider.js"></script>
<script>
	$(document).ready(function() {
		$('#panel-top').on("click", function() {
			window.location = "visualization.jspx";
		});
		$('#panel-bottom').on("click", function() {
			window.location = "contribute.jspx";
		});
		$('#panel-left').on("click", function() {
			window.location = "visualization.jspx";
		});
		$('#panel-right').on("click", function() {
			window.location = "contribute.jspx";
		});
		//Panel info events
		//Left panel (Visualization)
		$('#panel-left').hover(function(e){
			$('#home-panel-infos').css({'top':e.clientY,'left':e.clientX});
			$('#home-panel-infos').show();
			$('#home-panel-infos h2').text('Visualization');
			$('#home-panel-infos span').html('Click to visualize TPE results (Climate, Soil, Stability and TPE)');
	    }, function(){
	        $('#home-panel-infos').hide();
	    });
		
		//Bottom panel (Get Involved)
		$('#panel-bottom').hover(function(e){
			$('#home-panel-infos').css({'top':e.clientY,'left':e.clientX});
			$('#home-panel-infos').show();
			$('#home-panel-infos h2').text('Get Involved');
			$('#home-panel-infos span').html('Click to learn how you can get involved in this study.');
	    }, function(){
	        $('#home-panel-infos').hide();
	    });

	});
</script>
</head>

<body>
	<div id="home_container">
		<div id="home_panel">
			<div id="panel-top" class="panel-box">
				<img class="middle-img" src="img/get-top.png" height="250"
					width="250" />
			</div>
			<div id="panel-right" class="panel-box">
				<img class="middle-img" src="img/get-right.png" height="250"
					width="250" />
			</div>
			<div id="panel-bottom" class="panel-box">
				<img class="middle-img" src="img/get-bottom.png" height="250"
					width="250" />
			</div>
			<div id="panel-left" class="panel-box">
				<img class="middle-img" src="img/get-left.png" height="250"
					width="250" />
			</div>
		</div>
	</div>
	<!-- The home panel infos-->
	<div id="home-panel-infos">
		<h2></h2>
		<span id="panel-info">Infos</span>
	</div>
</body>
</html>