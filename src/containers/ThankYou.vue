<template>
	<div class="thank-you">
		<div class="greeting">
			<p>Thank You</p>
			<small>Your registration was successfull. We will review your request and get back to you soon.</small>
			<br>
			<router-link to="/">Home</router-link>
		</div>
	</div>	
</template>

<script type="text/javascript">
export default {
	name: 'thank-you',

}
</script>

<style lang="scss">
.thank-you {
	font-family: 'Quicksand', sans-serif;
	
	.greeting {
		padding-top: 16rem;
		text-align: center;
	}
	p {
		font-size: 44px;
	}
	small {
		font-size: 24px;
	}
}
</style>