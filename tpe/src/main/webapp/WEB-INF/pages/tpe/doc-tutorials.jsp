<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<title>TPE Documentation</title>
</head>
<body>
	<div id="documentation">
		<h4 class="navigation">
			<a href="documentation.jspx">Documentation</a> > <a href="#">Tutorials</a>
		</h4>
		<div>
			<div class="pane_left">
				<!--TPE Project Tutorials Area-->
				<div class="sec_details">
					<h4>How to use TPE tool?</h4>
					<h1>Introduction</h1>
					<p>Target Population of Environments Platform is an online tool
						for the identification of target population environments (TPEs).
						This tutorial provides an overview of how TPE platform works and
						will teach you how to use the platform. The figure below shows how
						TPE platform's most important components. The rest of this chapter
						describes the tool and sections in this figure.</p>
					<h1>Audience</h1>
					<p>This tutorial has been prepared for the breeders,
						scientists, students and other users to help them understand basic
						functionality of TPE tool.</p>

					<h1>TPE Navigation Menu</h1>
					<p>The navigation menu defines how visitors move from one part
						of TPE platform to another. The TPE menu contains five items:</p>
					<ul class="guide_list">
						<li><span class="item_bold">Home:</span> This contains the
							TPE overview and introduction</li>
						<li><span class="item_bold">TPE Tool:</span> This provides
							the TPE Tool and all the controls used.</li>
						<li><span class="item_bold">Documentation:</span> This
							provides all the TPE documentations (Project, Scientific and
							Tutorials)</li>
						<li><span class="item_bold">Contact Us:</span> This gives the
							TPE contact information.</li>
						<li><span class="item_bold">CCAFS-Sites:</span> This provides
							other CCAFS sites</li>
					</ul>
					<h1>The Home Screen</h1>
					<h4>Using the Home Screen</h4>
					<p>Click Home on the main menu bar. The home page(figure 1)
						will be displayed. The home page has a number sections:</p>
					<ul class="guide_list">
						<li><span class="item_bold">Slide Show Section:</span>The
							slide show (figure x) shows or displays the recent and exciting
							new TPE activities and events. This includes workshops,
							presentations, fieldwork, experiments, upcoming events and
							seminars. Each slide has detailed information attached and
							clicking on read-more link will automatically display the
							corresponding slide information or details on a separate page.</li>
						<li><span class="item_bold">TPE Tool:</span> This section
							contains the TPE Tool and clicking on this section will
							automatically display the TPE Tool's page(figure x). The same
							page is also accessible by clicking on TPE Tool menu located on
							the main menu bar (figure xx).</li>
						<li><span class="item_bold">Get Involved Section:</span> This
							section explains how the user could get involved in the TPE
							platform development or how to could contribute.</li>
						<li><span class="item_bold">Workshops Section:</span> This
							section provides the TPE recent workshops.</li>
					</ul>
					<h4>Get Involved Section:</h4>
					<p>This section explains how the user could get involved in the
						TPE platform development or how you could contribute.</p>
					<h5>Using to Get Involved:</h5>
					<p>On the Home screen, click on the Get Involved section to go
						to the Get Involved page (figure 11). This will display the Get
						Involved page (figure 12), which clearly explains how you could
						get involved in the TPE platform. On the Get Invloved page, you
						have options of...</p>
					<h4>Workshops Section</h4>
					<p>This section provides the TPE recent workshops. To view the
						recent TPE Workshops: On the Home screen, click on the Workshops
						section to go to the TPE Workshops page (figure 11). 2. This will
						display the most recent TPE workshops.</p>
					<!-- Viewing TPE Results -->
					<h1>Viewing TPE Results</h1>
					<p>
						On the navigation bar (main menu), click TPE Tool. The tool's page
						(figure x) will be displayed. Alternatively, clicking on the TPE
						Tool section on the home page will display the tool's page. The
						tool page contains four sections (see figure x), the <span
							class="item_bold">variable Selection Pane</span>, <span
							class="item_bold">Google Map section</span>, and the <span
							class="item_bold">Analytics Pane.</span> The following steps are
						considered when using the TPE tool:
					</p>
					<ul class="guide_list">
						<li>On the Select Variable Pane, select your desired output
							variable. There are three output options (<span class="item_bold">TPE</span>,
							<span class="item_bold">Climate</span> and <span
							class="item_bold">Soil</span>).
						</li>
						<li>Select Crop variable (Initially Rice and Beans)</li>
						<li>Select Crop Cultivar variable</li>
						<li>Select country (initially Brazil and Colombia)</li>
						<li>Then the TPE results will be rendered dynamically on the
							Google Map.</li>
						<li>Hover or click on the TPE regions (environments), to view
							the corresponding graphics dynamically on the right pane and the
							info window. The info window shows the details about the
							currently hovered or clicked region.</li>
					</ul>
					<div class="guide-big">
						<img alt="tpe-tool" src="img/guide/tpe-tool.png">
					</div>
					<!-- Viewing TPE Graphics -->
					<h1>Viewing and Downloading TPE Graphics</h1>
					<p>You can only view the TPE graphics after the TPE results are
						rendered on the Google Map (see the previous step above). You can
						view the graphics as follows:</p>
					<ul class="guide_list">
						<li>Hover or click on the TPE regions (environments), to view
							the corresponding graphics dynamically on the right pane and the
							info window as well. The info window shows the details about the
							currently hovered or clicked region.</li>
						<li>Click on the search icon to view a detailed graphic. <img
							alt="tpe-tool" src="img/zoom-in.png"></li>
						<li>Click on the download icon to download or export the
							graphic in different formats (PNG, JPG, PDF and SVG vetcor
							image).<img alt="tpe-tool" src="img/download_32.png">
						</li>
						<li></li>
					</ul>

					<div class="guide-big">
						<img alt="tpe-tool" src="img/guide/export.jpg">
					</div>
				</div>
				<div class="sec_details">
					<h1>Video Tutorials</h1>
					<p>Embed the Youtube TPE Video tutorial links</p>
				</div>

				<div class="sec_details">
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