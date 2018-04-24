<!-- Page Loader -->
<div class="page-loader-wrapper">
	<div class="loader">
		<div class="md-preloader pl-size-md">
			<svg viewbox="0 0 75 75">
				<circle cx="37.5" cy="37.5" r="33.5" class="pl-red" stroke-width="4" />
			</svg>
		</div>
		<p>Please wait...</p>
	</div>
</div>
<!-- #END# Page Loader -->
<!-- Overlay For Sidebars -->
<div class="overlay"></div>
<!-- #END# Overlay For Sidebars -->
<!-- Search Bar -->
<div class="search-bar">
	<div class="search-icon">
		<i class="material-icons">search</i>
	</div>
	<input type="text" placeholder="START TYPING...">
	<div class="close-search">
		<i class="material-icons">close</i>
	</div>
</div>
<!-- #END# Search Bar -->
<!-- Top Bar -->

<!-- #Top Bar -->
<section>

	<div class="row clearfix">
		<div class="col-sm-6">
			<div class="form-group">
				<div id="left_login" class="form-line">
					<img src="/include/images/login%20page%20pic.png" width="100%" height="100%">
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<div class="form-line">
					<h1 style=" color: darkgrey;text-align: center">OSO <?php echo $title ?></h1>
					<p>Welcome back! Please Login to your account.</p>
					<?php if (isset($message)) {
						echo "<p>$message</p>";
					} ?>
					<form>
						<input type="text" class="form-control" name="Username" placeholder="Username">
						<input type="text" class="form-control" name="password" placeholder="Password">
					</form>
					<button type="button" class="btn bg-pink btn-block btn-lg waves-effect">LARGE</button>
				</div>
			</div>
		</div>
	</div>

</section>
