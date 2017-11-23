<template>
	<div class="change-password">
		<div class="modal is-active">
			<div class="modal-background"></div>
			<div class="modal-card">
				<header class="modal-card-head">
					<p class="modal-card-title">Add New Quiz</p>
					<button class="delete" @click="close"></button>
				</header>
				<section class="modal-card-body">
					<div class="details">
						<div class="columns">
							<div class="column">
								<div class="field">
									<div class="control">
										<input class="input" type="text" placeholder="Old Password">
									</div>
								</div>
								<div class="field">
									<div class="control">
										<input class="input" type="text" placeholder="New Password">
									</div>
								</div>
								<div class="field">
									<div class="control">
										<input class="input" type="text" placeholder="Confirm Password">
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
				<footer class="modal-card-foot">
					<a class="button is-info" @click="addQuiz">Change</a>
					<a class="button is-info" @click="close">Close</a>
				</footer>
			</div>
		</div>
	</div>	
</template>

<script type="text/javascript">
export default {
	name: "change-password",

	data() {
		return {
			oldPassword: '',
			newPassword: '',
			confirmPassword: ''
		}
	}

}
</script>

<style lang="scss">

</style>