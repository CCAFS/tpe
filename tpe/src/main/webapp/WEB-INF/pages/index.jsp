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
			window.location = "documentation.jspx";
		});
		$('#panel-bottom').on("click", function() {
			window.location = "contribute.jspx";
		});
		$('#panel-left').on("click", function() {
			window.location = "visualization.jspx";
		});
		$('#panel-right').on("click", function() {
			window.location = "resources.jspx";
		});
		//Panel info events
		//Left panel (Visualization)
		$('#panel-left,#panel-bottom,#panel-top,#panel-right').mousemove(function(e){
			//$('#home-panel-infos').css({'top':e.clientY,'left':e.clientX});
			var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0)
var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0)
var d = $(document).scrollTop();
			var pos= $(this).position();
		
			//If left panel
			if($(this).is('#panel-left')){
			$('#home-panel-infos h2').css('color', '#ef2d38').text('Visualization');
			$('#home-panel-infos span').html('Click to visualize TPE results (Climate, Soil, Stability and TPE)');
			$("#panel-img").html('<img class="panel-img" src="img/visualization.png" />');
			}
			else if($(this).is('#panel-bottom')){
				$('#home-panel-infos h2').css('color', '#a6d832').text('Get Involved');
				$('#home-panel-infos span').html('Click to learn how you can get involved in this study.');
				$("#panel-img").html('<img class="panel-img" src="img/getinvolved.png" />');
				}
			else if($(this).is('#panel-right')){
				$('#home-panel-infos h2').css('color', '#6ba2dc').text('Resources');
				$('#home-panel-infos span').html('Click to access more resources.');
				}
			else if($(this).is('#panel-top')){
				$('#home-panel-infos h2').css('color', '#f47216').text('Documentation');
				$('#home-panel-infos span').html('Click to view the scientific documentation.');
				}
			$('#home-panel-infos').offset({
		        left: e.pageX,
		        top: e.pageY+20
		    });
			
			$('#home-panel-infos').show();
			//Change the div position if partially visible.
			// if (pos.top > h + d || pos.top > h - d){
			if (e.pageY > h + d || e.pageY > h - d){
				$('#home-panel-infos').offset({
			        left: e.pageX,
			        top: e.pageY-($(this).height()-30)
			    });
		    }
		    //horizontal
		    //#ef2d38 red
		    //orange #f47216
		    //#a6d832 green
		    //#6ba2dc blue
		   // if (pos.left < 0 - $(this).width() || pos.left > w){
		   /*  if (e.pageX< 0 - $(this).width() || e.pageX > w){
		    	$('#home-panel-infos').offset({
			        left: e.pageX-$(this).height(),
			        top: e.pageY
			    });
		    } */
		    
		/* 	if($('#home-panel-infos').visible(true)){
				$('#home-panel-infos').offset({
			        left: e.pageX,
			        top: e.pageY-$(this).height()
			    }).slide( 2000 );
			} */
			
	    });
		
		 $("#panel-left,#panel-bottom,#panel-top,#panel-right").mouseout(function(){
		        $("#home-panel-infos").hide();
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
		<div id="panel-img"></div>
	</div>
</body>
</html>