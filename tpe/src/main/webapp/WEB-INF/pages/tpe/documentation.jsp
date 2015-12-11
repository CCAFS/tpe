<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>Documentation</title>
<script type="text/javascript">
	$(document).ready(function() {

		$(".more").on('click', function() {
			if ($(this).siblings(".complete").css('display') == 'none') {
				$(this).text("view less...").siblings(".complete").show();
			} else {
				$(this).text("view more...").siblings(".complete").hide();
			}
		});
	});
</script>
</head>
<body>
	<div id="documentation">
		<div>
			<div class="pane_left">
				<!--Documentation Area-->
				<div class="sec_details">
					<h4>Methodology</h4>
					<p>We developed and applied a methodology to identify
						environmental groups (EGs) and stress profiles so as to improve
						priority setting in crop improvement programs (also see our
						Working Paper here [hyperlink] to WP). Fig. 1 presents an overview
						of the process.</p>

					<img src="${ctx}/resources/methodology.png" alt="image" /> 
					<div class="caption">Figure
					1 Overview of the environmental characterization methodology.
					Circled numbers indicate steps, grey boxes indicate inputs to the
					method, and light orange boxes indicate outputs.</div>

					<p>The methodology consists of seven steps, as follows:</p>
					<h4>Step 1:</h4>
					<p>
						The first step is to define the study region and crop under study,
						the crop model to be used, and collate experimental data for model
						calibration and evaluation.<span class="complete"> Identify
							whether a well-established model has already been calibrated and
							evaluated for the study region, or whether the model you commonly
							use is suitable for the conditions in the study region. The
							selected crop model needs to be able to simulate processes that
							are relevant to the region. Experimental data should include
							relevant varieties in the region, should ideally be
							multi-location and/or multi-year, and should include both
							potential yield and stress-induced yield trials. Field data
							should include measurements of multiple plant attributes (e.g.
							leaf area index, organ-specific biomass), as well as weather,
							soils and management inputs.</span><span class="more"> View more...</span>
					</p>

					<h4>Step 2:</h4>
					<p>
						The next step is to parameterize a process-based crop model using
						a set of field experiments.<span class="complete"> First,
							thoroughly check the experimental data for possible errors, and
							then the data split into a `calibration` set and an `evaluation`
							set. Next, per crop variety, calibrate the crop model. Multiple
							methods exist to derive model parameter values for crop models,
							and some of these are already built-in and well-tested for
							certain crop models. Finally, ensure that the parameter values
							fall within plausible ranges and represent well the
							morpho-physiological attributes of the varieties being
							calibrated.</span><span class="more"> View more...</span>
					</p>

					<h4>Step 3:</h4>
					<p>
						Simulations are then run per crop variety and assessed against the
						evaluation data using metrics such as the Root Mean Square Error
						(RMSE), the mean bias (difference between means of observations
						and simulations), the correlation coefficient, and the Wilmott
						d-statistic.<span class="complete"> The primary aim of this
							step is to ensure that the model is capable of reproducing an
							independent set of observations, and the result should be a
							parameter set (per variety) that can later be used to run
							spatially-explicit and time-varying simulations for the study
							region.</span><span class="more"> View more...</span>
					</p>

					<h4>Step 4:</h4>
					<p>
						Having calibrated and evaluated the crop model, the most
						interesting part of the approach begins. You will need to run
						spatially explicit crop model simulations for the study area
						(across k sites), for a representative period of n years and for a
						number of management scenarios (m).<span class="complete">
							For this, either high-resolution gridded daily weather or daily
							weather station records of representative number of weather
							stations are needed. Soil profile data for all locations where
							the crop model is to be run are also needed. Management scenarios
							are constructed as a combination of planting dates, planting
							densities, and/or fertiliser application regimes.</span><span
							class="more"> View more...</span>
					</p>

					<h4>Step 5:</h4>
					<p>
						Now, environmental groups (EGs) need to be determined. To this
						aim, statistical clustering is performed on the simulated crop
						yields of all the site*year*management scenarios.<span
							class="complete"> Clustering by yield helps separating
							situations of low and high yields, without necessarily assessing
							their causes –which will be assessed in step 6. Clustering
							efficiency and stability indicators are then used to determine
							the optimal number of EGs for the study region. Using the results
							of this first clustering, EG maps can be produced.</span><span
							class="more"> View more...</span>
					</p>

					<h4>Step 6:</h4>
					<p>
						With an understanding of which site*year*management combinations
						belong to the different EGs, the next step is to determine the
						main stresses for each group, i.e. stress profiles.<span
							class="complete"> Because this step requires statistical
							clustering of stress-related modelled variables (i.e. stress
							index), an a-priori idea of stresses in the region should inform
							this step. For example, the ratio of actual to potential
							evapotranspiration is usually a good indicator of water stress.
							Clustering is performed individually per EG using the seasonal
							variation of the relevant modelled stress index.</span><span
							class="more"> View more...</span>
					</p>

					<h4>Step 7:</h4>
					<p>
						The final step is to use the environmental groupings and the
						stress profiles to calculate how much additional area in the study
						region can be covered if the breeding strategy extended to include
						stresses that are not currently considered.<span class="complete">
							This step is expected to make a clear case for, for example,
							inclusion of additional sites for germplasm selection under
							specific conditions.</span><span class="more"> View more...</span>
					</p>

					<h4>Further reading:</h4>
					<ul>
						<li>Ramirez-Villegas J and Heinemann AB (2015) Environmental
							characterisation to guide breeding decisions in a changing
							climate. CCAFS Working Paper No. XX. (read) [hyperlink to WP].</li>
						<li>Heinemann AB, Barrios-Perez C, Ramirez-Villegas J, et al.
							(2015a) Variation and impact of drought-stress patterns across
							upland rice target population of environments in Brazil. Journal
							of Experimental Botany 66:3625–3638. doi: 10.1093/jxb/erv126.
							read: <a href="http://jxb.oxfordjournals.org/content/66/12/3625">rice
								paper</a>
						</li>
						<li>Heinemann AB, Ramirez-Villegas J, Souza T, et al. (2015b)
							Drought impact on rainfed common bean production areas in Brazil.
							Agricultural and Forest Meteorology. under review.</li>

					</ul>
				</div>


			</div>
			<div class="pane_right">
				<jsp:include page="right_pane.jsp" />
			</div>
		</div>
	</div>
</body>
</html>