<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>TPE Platform</title>

<script type="text/javascript"
	src="${ctx}/script/home-overlay-dialog.js"></script>

<script type="text/javascript">
	(function(setting) {
		var cook = {
			set : function(n, v, d) { // cook.set takes (name, value, optional_persist_days) - defaults to session if no days specified
				if (d) {
					var dt = new Date();
					dt.setDate(dt.getDate() + d);
					d = '; expires=' + dt.toGMTString();
				}
				document.cookie = n + '=' + escape(v) + (d || '') + '; path=/';
			},
			get : function(n) { // cook.get takes (name)
				var c = document.cookie.match('(^|;)\x20*' + n + '=([^;]*)');
				return c ? unescape(c[2]) : null;
			}
		};
		if (cook.get('skipthispage')) {
			location.replace(setting.page);
		}
		if (!document.cookie) {
			cook.set('temp', 1);
		}
		if (document.cookie) {
			jQuery(function($) {
				$('#optout').css({
					display : ''
				}).append(setting.optoutHTML).find('input').click(
						function() {
							this.checked ? cook.set('skipthispage', '1',
									setting.days) : cook.set('skipthispage',
									'', -1);
							this.checked && setting.gowhenchecked
									&& location.replace(setting.page);
						});
			});
		}
	})
			({
				days : 1, // days cookie will persist//days : 365, 
				page : '/', // page to goto if cookie is set
				gowhenchecked : true, // true/false - should page switch when the box is checked?
				optoutHTML : '<label for="optoutcheckbox">Don\'t show this dialog again: <input type="checkbox" id="optoutcheckbox" value=""></label>'
			});
</script>
</head>

<body>
	<div id="home_container">
		<div id="home_panel">
			<div id="panel-left" class="panel-box-small">
				<img class="middle-img" src="${ctx}/img/left-home.png" height="462"
					width="294" />
			</div>
			<div id="panel-top" class="panel-box-big">
				<img class="middle-img" src="${ctx}/img/top-home.png" height="294"
					width="462" />
			</div>
			<div id="panel-bottom" class="panel-box-big">
				<img class="middle-img" src="${ctx}/img/bottom-home.png"
					height="294" width="462" />
			</div>
			<div id="panel-right" class="panel-box-small">
				<img class="middle-img" src="${ctx}/img/right-home.png" height="462"
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
				Welcome to <a href="#">CCAFS-TPE Platform</a>
			</h3>
		</header>
		<div class="modal-body">
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
			<div id="optout" style="display: none;"></div>
		</div>
		<footer>
			<a href="#" class="js-modal-close">Return to website</a>
		</footer>
	</div>
	<a class="js-open-modal" href="#" data-modal-id="popup"> Click me </a>
</body>
</html>