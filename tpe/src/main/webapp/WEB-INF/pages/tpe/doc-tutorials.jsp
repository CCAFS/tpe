<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<title>TPE Documentation</title>
</head>
<body>
	<div id="documentation">
		<h4 class="navigation">
			<a href="${ctx}/documentation.jspx">Documentation</a> > <a href="#">Tutorials</a>
		</h4>
		<div>
			<div class="pane_left">
				<!--TPE Project Tutorials Area-->
				<div class="guide">
					<h1>How to Visualize the TPE Results?</h1>
					<h1>Introduction</h1>
					<p>
						This tutorial provides an overview of how TPE platform works and
						will teach you how to use the platform. The <span
							style="font-style: italic;">figure 1</span> below shows
						platform's home page with an interactive diagram and <span
							style="font-style: italic;">figure 2</span> is an overlay dialog
						form that appears on top of the home page.
					</p>
					<h1>Audience</h1>
					<p>This tutorial has been prepared for the breeders,
						scientists, students and other users to help them understand basic
						functionality of TPE Visualization.</p>
					<div class="guide-big">
						<img alt="tpe-tool" src="${ctx}/resources/user-home.png">
						<div class="caption">Figure 1: Home Page with an interactive
							diagram</div>
					</div>
					<div class="guide-big">
						<img alt="tpe-tool" src="${ctx}/resources/user-overlay.png">
						<div class="caption">Figure 2: Overlay Dialog Form</div>
					</div>
					<h1>Navigation Menu</h1>
					<p>
						The <span style="font-style: italic;">figure 3</span> below shows
						the navigation menu which contains five items:
					</p>
					<ul class="guide_list">
						<li><span class="item_bold">Home:</span> This contains the
							Platform Overview</li>
						<li><span class="item_bold">Visualization:</span> This
							provides the visualization of results and all the associated
							graphics.</li>
						<li><span class="item_bold">Regions:</span> This provides the
							case studies from the regions where we work.</li>
						<li><span class="item_bold">Documentation:</span> This
							provides the TPE documentations (Project and Scientific)</li>
						<li><span class="item_bold">Resources:</span> This provides
							the links to user tutorials and data downloads.</li>
					</ul>
					<div class="guide-big">
						<img alt="tpe-tool" src="${ctx}/resources/user-menu.png">
						<div class="caption">Figure 3: Main menu</div>
					</div>
					<h1>Home Screen</h1>
					<h4>Using the Home Screen</h4>
					<p>Click Home on the main menu bar. The home page (figure 1)
						will be displayed. The home page contains the initial overlay
						dialog form and interactive diagram:</p>
					<ul class="guide_list">
						<li><span class="item_bold">Initial Overlay Dialog:</span>The
							overlay dialog form (figure 2) will be display everytime a home
							page is loaded but the user can check the check box to prevent it
							from being shown. The dialog briefly explains how to use the
							platform, the intended audience and what it provides.</li>
						<li><span class="item_bold">Interactive Diagram:</span>The
							interactive diagram contains four items and each associated with
							a paticular module in the platform. When clicked it will
							automatically open up the link. 1 (What is TPE?), 2 (Methodology
							and Case studies), 3 (Visualization) and 4 (How you can Get
							Involved). Clicking on each item shall open up a new page for
							that specific module.</li>
					</ul>
					<h4>How to Get Involved?:</h4>
					<p>
						From the home screen diagram click on 'Get Involved' or click on
						this link <a href="${ctx}/contribute.jspx">Get Involved</a> This
						section explains how the user could get involved in the TPE
						Methodology.
					</p>
					<h5>Using to Get Involved:</h5>
					<p>On the Home screen, click on the Get Involved section to go
						to the Get Involved page (figure 4). This will display the Get
						Involved page (figure x), which clearly explains how you could get
						involved in the TPE platform. On the Get Invloved page, you have
						options of...</p>
					<div class="guide-big">
						<img alt="tpe-tool" src="${ctx}/resources/user-getinvolved.png">
						<div class="caption">Figure 4: How to get Involved?</div>
					</div>
					<h4>Workshops Section</h4>
					<p>This section provides the TPE recent workshops. To view the
						recent TPE Workshops: On the Home screen, click on the Workshops
						section to go to the TPE Workshops page (figure x). This will
						display the most recent TPE workshops.</p>
					<!-- Viewing TPE Results -->
					<h1>Visualization of TPE Results</h1>
					<p>
						On the navigation bar (main menu), click Visualization. The
						visualization page (figure 5) will be displayed. Alternatively,
						from the home screen diagram click on the Visualization option,
						then this will display the visualization page. The Visualization
						page contains four sections (see figure 5), the <span
							class="item_bold">Parameter Selection Pane</span>, <span
							class="item_bold">Google Map result</span>, <span
							class="item_bold">Analytics (Graphics) Pane.</span>, and the <span
							class="item_bold"> Info Window.</span> The following steps are
						considered when visualizing the results:
					</p>
					<ul class="guide_list">
						<li>On the Map Option Pane, select the output map. There are
							three output options (<span class="item_bold">Climate</span>, <span
							class="item_bold">Soil</span>, <span class="item_bold">Stability</span>
							and <span class="item_bold">TPE</span>).
						</li>
						<li>Select Crop (Initially Rice and Beans)</li>
						<li>Select Crop Cultivar (variety)</li>
						<li>Select country (region)</li>
						<li>Then the TPE results will be rendered dynamically on the
							Google Map.</li>
						<li>Hover or click on the results features (environments) on
							the map, to view the corresponding graphics dynamically on the
							right pane and the info window. The info window shows the details
							about the currently hovered or clicked region.</li>
						<li>Please note when Climate or Soil are selected as output
							map, then crop and cultivar variables will not visible.</li>
					</ul>
					<div class="guide-big">
						<img alt="tpe-tool" src="${ctx}/resources/user-stab.png">
						<div class="caption">Figure 5: Visualization of Environment
							Stability</div>
					</div>
					<!-- Viewing TPE Graphics -->
					<h1>View and Download Graphics</h1>
					<p>You can view the graphics as follows:</p>
					<ul class="guide_list">
						<li>Hover or click on the results features on the map
							(environments) to view the corresponding graphics dynamically on
							the right pane and the info window as well. The info window shows
							the details about the currently hovered or clicked feature.</li>
						<li>Click on the search icon to view a detailed graphic. <img
							alt="tpe-tool" src="${ctx}/img/zoom-in.png"></li>
						<li>Click on the download icon to download or export the
							graphic in different formats (PNG, JPG, PDF and SVG).<img
							alt="tpe-tool" src="${ctx}/img/download_32.png">
						</li>
					</ul>

					<div class="guide-big">
						<img alt="tpe-tool" src="${ctx}/resources/user-graphic.png">
						<div class="caption">Figure 6: View and export graphics</div>
					</div>
					<h1>Query and download data</h1>
					<p>The user is able to query and download soil and climate data
						from the system.</p>
					<p>
						On the navigation bar (main menu), click resources then downloads.
						The query page (figure 6) will be displayed. The query page
						contains two sections, the <span class="item_bold">Parameter
							Selection Pane</span> and <span class="item_bold"> grid result.</span>
						The following steps are considered when querying and downloading
						the available data:
					</p>
					<ul class="guide_list">
						<li>On the Query Option Pane, select the category. There are
							two categories (<span class="item_bold">Climate</span> and <span
							class="item_bold">Soil</span>).
						</li>
						<li>Select country (region)</li>
						<li>If climate was selected in step 1 above, then select
							climate indicator (Min or max temperature, precipitation or
							radiation)</li>
						<li>Then the results will be rendered dynamically on the grid
							table.</li>
						<li>Click on the export button below the grid results to
							export the result.</li>
					</ul>
					<div class="guide-big">
						<img alt="tpe-tool" src="${ctx}/resources/user-query.png">
						<div class="caption">Figure 6: Querying of data</div>
					</div>
				</div>

				<div class="guide">
					<h1>Video Tutorials</h1>
					<p>Embed the Youtube TPE Video tutorial links</p>
				</div>

				<div class="guide">
					<h1>Using TPE Scripts</h1>
					<p>Provides a list of TPE Scripts</p>
				</div>
			</div>
			<div class="pane_right">
				<jsp:include page="right_pane.jsp" />
			</div>
		</div>
	</div>
</body>
</html>