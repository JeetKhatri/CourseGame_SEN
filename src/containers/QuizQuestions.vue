<template>
	<div class="quiz-question">
		<textarea class="textarea" placeholder="Enter question for quiz"></textarea>
		<br>
		<div class="columns">
			<div class="column">
				<div class="field">
					<div class="control">
						<input class="input" type="text" placeholder="Option A">
					</div>
				</div>
			</div>
			<div class="column">
				<div class="field">
					<div class="control">
						<input class="input" type="text" placeholder="Option B">
					</div>
				</div>
			</div>
			<div class="column">
				<div class="field">
					<div class="control">
						<input class="input" type="text" placeholder="Option c">
					</div>
				</div>
			</div>
			<div class="column">
				<div class="field">
					<div class="control">
						<input class="input" type="text" placeholder="Option D">
					</div>
				</div>
			</div>
		</div>
		<div class="column" id="buttons">
			<button class="button is-success">Save</button>
			<button class="button is-success">Add New</button>
		</div>
		<div class="column" id="correctAnswer">
			<input class="input" type="text" placeholder="Correct Answer">
		</div>
	</div>
</template>

<script type="text/javascript">
export default {
	name: 'quiz-question',

}
</script>

<style lang="scss">
.quiz-question {
	#buttons {
		float: right;
	}

	#correctAnswer {
		float: left;
	}
}
</style>