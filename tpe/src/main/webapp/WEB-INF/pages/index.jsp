<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/script/slider.js"></script>

<title>CCAFS TPE Platform</title>

</head>

<body>
	<!--TPE Content Area-->
	<div id="tpe_slider">
		<div class="slide">
			<div id="header">
				<div class="wrap">
					<div id="slide-holder">
						<div id="slide-runner">
							<a href=""><img id="slide-img-1"
								src="img/slideshow/nature-photo.jpg" class="slide" alt="" /></a> <a
								href=""><img id="slide-img-2"
								src="img/slideshow/nature-photo1.jpg" class="slide" alt="" /></a> <a
								href=""><img id="slide-img-3"
								src="img/slideshow/nature-photo2.jpg" class="slide" alt="" /></a> <a
								href=""><img id="slide-img-4"
								src="img/slideshow/nature-photo3.jpg" class="slide" alt="" /></a> <a
								href=""><img id="slide-img-5"
								src="img/slideshow/nature-photo4.jpg" class="slide" alt="" /></a> <a
								href=""><img id="slide-img-6"
								src="img/slideshow/nature-photo5.jpg" class="slide" alt="" /></a> <a
								href=""><img id="slide-img-7"
								src="img/slideshow/nature-photo6.jpg" class="slide" alt="" /></a>
							<div id="slide-controls">
								<p id="slide-nav"></p>
							</div>

							<div id="slide_arrows">
								<a id="slide_prev"></a> <a id="slide_next"></a>
							</div>

							<div id="slide_text">
								<p id="slide-info" class="text">
									<strong><span></span></strong>
								</p>
								<p id="slide-readmore" class="text">
									<a href="#">Read More<b>></b></a>
								</p>
							</div>

						</div>

						<!--content featured gallery here -->
					</div>
					<script type="text/javascript">
						if (!window.slider)
							var slider = {};
						slider.data = [
								{
									"id" : "slide-img-1",
									"client" : "nature beauty",
									"desc" : "nature beauty photography",
									"info" : "TPE Slider Image Info One"
								},
								{
									"id" : "slide-img-2",
									"client" : "nature beauty",
									"desc" : "add your description here",
									"info" : "TPE Slider Image Info Two"
								},
								{
									"id" : "slide-img-3",
									"client" : "nature beauty",
									"desc" : "add your description here",
									"info" : "TPE Slider Image Info Three"
								},
								{
									"id" : "slide-img-4",
									"client" : "nature beauty",
									"desc" : "add your description here",
									"info" : "TPE Slider Image Info Four"
								},
								{
									"id" : "slide-img-5",
									"client" : "nature beauty",
									"desc" : "add your description here",
									"info" : "TPE Slider Image Info Five"
								},
								{
									"id" : "slide-img-6",
									"client" : "nature beauty",
									"desc" : "add your description here",
									"info" : "blah blah"
								},
								{
									"id" : "slide-img-7",
									"client" : "nature beauty",
									"desc" : "add your description here",
									"info" : "Pic by Neil Palmer (CIAT). Biofortified rice at the institution's headquarters in Colombia. Please credit accordingly and let us know when you use a CIAT pic. Contact n.palmer@cgiar.org"
								} ];
					</script>
				</div>
			</div>
		</div>
	</div>

	<!--TPE Workshops-->
	<div id="tpe_section">
		<div class="box">
			<div class="tpe_box">
				<div class="box_col">
					<a href="<s:url namespace="/" action="toolIndex" />"><h4>TPE
							Tool</h4> </a> <a href="<s:url namespace="/" action="toolIndex" />"><img
						src="img/tpe_map.jpg" alt="image" /> </a>
					<p>At times crop breeding can feel like steering a rudderless
						ship. There’s no such thing as a compass for climate change, for
						starters, and it’s hard to say whether crop varieties currently in
						development will still be adequate for conditions on the ground 10
						years from now. So what do breeders have to aim at, exactly?</p>
					<p class="readmore">
						<a href="<s:url namespace="/" action="toolIndex" />">Read More
							<b>></b>
						</a>
					</p>
				</div>
			</div>
			<div class="tpe_box">
				<div class="box_col">
					<a href=""><h4>Get Involved</h4> </a> <a href=""> <img
						src="http://dapa.ciat.cgiar.org/wp-content/uploads/2013/02/Blog21.png"
						alt="image" />
					</a>
					<p>The development of the agricultural sector in recent years
						has been marked by great challenges due to new conditions and
						permanent changes in the global climate regime, challenges which
						present an imminent risk to food security for the most vulnerable.
						It is crucial to cooperate and work together across multiple
						countries and institutions to seek strategies and appropriate
						response mechanisms against such eventualities.</p>
					<p class="readmore">
						<a href="">Read More <b>></b>
						</a>
					</p>
				</div>
			</div>
			<div class="tpe_box">
				<div class="box_col">
					<a href="<s:url namespace="/" action="toolIndex" />"><h4>TPE
							Workshops</h4> </a>

				</div>
			</div>

		</div>
	</div>
</body>
</html>