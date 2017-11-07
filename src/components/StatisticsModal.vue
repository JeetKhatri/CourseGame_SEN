<template>
	<div class="statistics-modal">
		<div class="modal is-active">
			<div class="modal-background"></div>
			<div class="modal-card">
				<header class="modal-card-head">
					<p class="modal-card-title">Student Statistics</p>
					<button class="delete" @click="close"></button>
				</header>
				<section class="modal-card-body">
					<div class="details">
						<div class="field">
							<label class="label">CPI</label>
							<p class="control">
								<input class="input" type="number" name="cpi" placeholder="CPI">
							</p>
						</div>
					</div>
				</section>
				<footer class="modal-card-foot">
					<a class="button is-success">Save</a>
					<a class="button close-btn" @click="close">Close</a>
				</footer>
			</div>
		</div>
	</div>
</template>

<script type="text/javascript">
export default {
	name: 'statistics-modal',

	methods: {
		close() {
			this.$emit('closeStatistics');
		}
	}

}
</script>

<style lang="scss"></style>