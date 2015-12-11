<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<title>Tutorials</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".jumper").on("click", function(e) {

			e.preventDefault();

			$("body, html").animate({
				scrollTop : $($(this).attr('href')).offset().top
			}, 600);

		});
	});
</script>
</head>
<body>
	<div id="documentation">
		<div>
			<div class="pane_left"> 
				<div class="guide">
				<h1>How to Visualize the TPE Results?</h1>
					<h6>
						<a class="jumper" href="#introduction">Introduction</a>
					</h6>
					<h6>
						<a class="jumper" href="#homescreen">The Home Screen</a>
					</h6>
					<h6>
						<a class="jumper" href="#navigation">Navigation menu</a>
					</h6>
					<h6>
						<a class="jumper" href="#interactive">Interactive diagram</a>
					</h6>
					<h6>
						<a class="jumper" href="#visualization">Visualization tool</a>
					</h6>
					<h6>
						<a class="jumper" href="#maps">Navigating the map interface</a>
					</h6>
					<h6>
						<a class="jumper" href="#graphics">View and download graphics</a>
					</h6>
					<h6>
						<a class="jumper" href="#query">Query and download data</a>
					</h6>

					<h1 id="introduction">Introduction</h1>
					<p>
						This tutorial will teach you how to use the <span
							style="font-style: italic;">Target Population of
							Environments</span> (TPE) platform. This tutorial has been prepared for
						the scientists (e.g. breeders, physiologists, crop modelers),
						students and other users to help them understand basic
						functionality of the visualization and data presented in this web
						portal.
					</p>
					<p>
						<span style="font-style: italic;">Figure 1 </span>below is an
						overlay dialog form that appears on top of the home page once you
						access the portal. This box provides you with basic information
						regarding our project, and will be displayed every time the home
						page is loaded. You can check the check box to prevent it from
						being shown again.
					</p>
					<div class="guide-big">
						<img alt="tpe-tool" src="${ctx}/resources/user-overlay.png">
						<div class="caption">Figure 1: Overlay Dialog Form</div>
					</div>

					<h1 id="homescreen">The Home Screen</h1>
					<p>
						<span class="item_bold">Figure 2 </span>shows platform's home
						page, containing a navigation menu, and an interactive diagram.
					</p>
					<div class="guide-big">
						<img alt="tpe-tool" src="${ctx}/resources/user-home.png">
						<div class="caption">Figure 2: Home Page with a top
							navigation menu and an interactive diagram</div>
					</div>

					<ul class="guide_list">
						<li><span class="item_bold">Navigation menu:</span> Allows
							access specific features in the TPE portal: the home screen, the
							visualization interface, results for regions where we work, and
							resources (including this tutorial).</li>
						<li><span class="item_bold">Interactive diagram: </span>The
							interactive diagram contains four items, each associated with a
							particular module in the platform. These are: (1) What is TPE?
							(2) Methodology and Case studies, (3) Visualization and (4) How
							can I get involved? Clicking on each item shall open up a new
							page for that specific module.</li>
					</ul>

					<p>Below we provide descriptions of each home page item.</p>

					<h1 id="navigation">Navigation menu</h1>
					<p>
						<span class="item_bold">Figure 3 </span>below shows the navigation
						menu which contains five items:
					</p>

					<ul class="guide_list">
						<li><span class="item_bold">Home: </span>Contains the
							platform overview. Clicking on it will take you to Fig. 1.</li>
						<li><span class="item_bold">Visualization: </span>Provides
							access to a map interface with dynamic graphs that allows
							visualization of our project’s results.</li>
						<li><span class="item_bold">Regions: </span>Provides access
							to project results from the regions where we work.</li>
						<li><span class="item_bold">Resources: </span>Provides links
							to methodology, scientific documentation (journal articles,
							working paper), user tutorials and data downloads.</li>
					</ul>

					<h1 id="interactive">Interactive diagram</h1>
					<p>The Interactive Diagram is the way to get to all important
						features in the TPE website. The diagram links you to four
						sections of the portal, as follows:</p>

					<ol>
						<li>(1) What is TPE? Describes our project.</li>
						<li>(2) Case studies and key results: Access results for the
							different crops and regions where we have conducted work.</li>
						<li>(3) Visualization: Provides access to a map interface
							with dynamic graphs that allows visualization of our project’s
							results.</li>
						<li>(4) Get involved: explains the user how to get involved
							in our project. Getting involved will require you providing your
							name and e-mail. You will then describe whether you are willing
							to share data, or to collaborate on specific analyses.</li>

					</ol>
					<h1 id="visualization">Visualization tool</h1>

					<p>You can access the TPE portal map and graphs in two ways: by
						clicking “Visualization” in the navigation menu, or by clicking
						“Maps and graphics” in the interactive diagram. Figure 5 shows our
						online map application.</p>
					<div class="guide-big">
						<img alt="tpe-tool" src="${ctx}/resources/user-getinvolved.png">
						<div class="caption">Figure 3: Visualization of environment
							stability</div>
					</div>

					<h1 id="maps">Navigating the interface</h1>
					<p>
						The map tool contains four elements: the <span class="item_bold">map
							options pane</span>, a <span class="item_bold">Google Map
							application</span>, <span class="item_bold">the analytics
							(graphics) pane</span>, and the <span class="item_bold">info
							window</span>. In order to visualize results, you’ll need to follow
						these steps:
					</p>

					<ol>
						<li>On the map options pane, select the output map. There are
							three output options: climate, soil, environment groups, and
							stability. The option climate will allow querying meteorological
							data; the soil option will display and allow querying input soil
							parameters; the environment groups option will show the
							environmental groups; and the stability option will display the
							frequency map of the environment groups.</li>
						<li>Select the crop of interest</li>
						<li>Select crop cultivar (variety)</li>
						<li>Select region</li>
					</ol>

					<p>
						Please note when <span style="font-style: italic;">climate</span>
						or <span style="font-style: italic;">soil</span> are selected as
						output, the crop and cultivar variables will not visible (only
						region will).
					</p>

					<p>
						This will show results dynamically on the Google Map application.
						If you selected the <span style="font-style: italic;">environment
							groups</span> option, you can now query environment-specific results.
						Hover or click on the results features (environments) on the map,
						to view the corresponding graphics dynamically on the right pane
						and the info window. The info window shows the details about the
						currently hovered or clicked region.
					</p>

					<h1 id="graphics">View and download graphics</h1>
					<p>You can view the TPE portal graphics as follows:</p>
					<ul class="guide_list">
						<li>Hover or click on the results features on the map
							(environments) to view the corresponding graphics dynamically on
							the right pane as well as the info window. The info window shows
							the details about the currently hovered or clicked feature.</li>
						<li>Click on the magnifying glass icon to enlarge the
							graphic.<img alt="tpe-tool" src="${ctx}/img/zoom-in.png">
						</li>
						<li>Click on the download icon to download or export the
							graphic in different formats (PNG, JPG, PDF and SVG).<img
							alt="tpe-tool" src="${ctx}/img/download_32.png">
						</li>
					</ul>

					<div class="guide-big">
						<img alt="tpe-tool" src="${ctx}/resources/user-graphic.png">
						<div class="caption">Figure 4: View and export graphics</div>
					</div>


					<h1 id="query">Query and download data</h1>
					<p>You will also be able to query and download soil and climate
						data from the system.</p>
					<p>
						On the navigation bar (main menu), click resources and then
						downloads. The query page (Figure 6) will be displayed. The query
						page contains two sections, the <span class="item_bold">
							query options pane</span> and a <span class="item_bold">
							spreadsheet with the results</span> of the query. The following steps
						are considered when querying and downloading the available data:
					</p>

					<ol>
						<li>On the query options pane, select the category. There are
							two categories: climate and soil.</li>
						<li>Select region</li>
						<li>If you selected climate in step 1 above, then select a
							climate variable (maximum or minimum temperature, precipitation
							or downwards shortwave solar radiation)</li>
						<li>The results will be rendered dynamically on the grid
							table.</li>
						<li>Click on the export button below the grid results to
							export the result.</li>

					</ol>

					<div class="guide-big">
						<img alt="tpe-tool" src="${ctx}/resources/user-query.png">
						<div class="caption">Figure 5: Querying of observed data</div>
					</div>
				</div>


			</div>
			<div class="pane_right">
				<jsp:include page="right_pane.jsp" />
			</div>
		</div>
	</div>
</body>
</html>