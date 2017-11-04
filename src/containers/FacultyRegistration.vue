<template>
	<div class="registration">
		<div class="main-agileits">
			<h1>Faculty Registration</h1>
			<div class="mainw3-agileinfo form">
				<div class="field is-horizontal">
					<div class="field-body">
						<div class="field">
							<div class="field-wrap">
								<input type="text" name="text" placeholder="First Name">
							</div>
						</div>
						<div class="field">
							<div class="field-wrap">
								<input type="text" name="text" placeholder="Last Name">
							</div>
						</div>
					</div>
				</div>
				<div class="field is-horizontal">
					<div class="field-body">
						<div class="field">
							<div class="field-wrap">
								<input type="email" name="email" placeholder="Email Address">
							</div>
						</div>
						<div class="field">
							<div class="field-wrap">
								<input type="number" name="number" placeholder="Contact Number">
							</div>
						</div>
					</div>
				</div>
				<div class="field is-horizontal">
					<div class="field-body">
						<div class="field">
							<div class="field-wrap">
								<input type="password" name="password" placeholder="Password">
							</div>
						</div>
						<div class="field">
							<div class="field-wrap">
								<input type="password" name="confirmPassword" placeholder="Confirm Password">
							</div>
						</div>
					</div>
				</div>
				<button class="button button-block" @click="register">SignUp</button>  
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: 'HelloWorld',
	data () {
		return {

		}
	},

	methods: {
		register() {
			alert('Functionality yet to be added!');
		}
	},

	created() {
		console.log('component loaded!');
	}
}
</script>

<style lang="scss">
.registration {
	background-image: url('../assets/game.jpg');
	background-attachment: fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover; 
	background-size: cover;
	height: -webkit-fill-available;


	h1 {
		font-size: 3em;
		text-align: center;
		color: #ddd;
		font-weight: 100;
	}

	.main-agileits {
		padding: 1em 0 0;
	}
	.mainw3-agileinfo {
		width: 50%;
		margin: 2em auto;
		padding: 3em;
	}
	.form a {
		text-decoration: none;
		color: #fff;
		-webkit-transition: .5s ease;
		-moz-transition: .5s ease;
		-o-transition: .5s ease;
		-ms-transition: .5s ease;
		transition: .5s ease;
	}
	.form a:hover {
		color: #05e0e2;
	} 
	.form {
		background: rgba(0, 0, 0, 0.36);
		-webkit-border-radius: 20px;
		-moz-border-radius: 20px; 
		border-radius: 20px;
	}
	label {
		position: absolute;
		-webkit-transform: translateY(6px);
		-moz-transform: translateY(14px);
		-o-transform: translateY(14px);
		-ms-transform: translateY(14px);
		transform: translateY(14px);
		left: 2px;
		color: rgba(255, 255, 255, 0.45);
		-webkit-transition: all 0.25s;
		-moz-transition: all 0.25s; 
		transition: all 0.25s;
		-webkit-backface-visibility: hidden;
		pointer-events: none;
		font-size: 1em;
	}
	label .req {
		margin: 2px;
		color: #FFC107;
	}
	label.active {
		-webkit-transform: translateY(50px);
		-moz-transform: translateY(58px); 
		-o-transform: translateY(58px); 
		-ms-transform: translateY(58px); 
		transform: translateY(58px); 
		font-size: .75em;
	}
	label.active .req {
		opacity: 0;
	} 
	label.highlight {
		color: #fff;
	} 
	input, textarea {
		font-size: 1em;
		display: block;
		width: 93%;
		padding:1em 1em 1em 0;
		background: none;
		background-image: none;
		border: none;
		border-bottom: 1px solid #8e8e8e;
		color: #fff;
		border-radius: 0;
		-webkit-transition: border-color .25s ease, box-shadow .25s ease;
		-moz-transition: border-color .25s ease, box-shadow .25s ease; 
		transition: border-color .25s ease, box-shadow .25s ease;
	}
	input:focus, textarea:focus {
		outline: 0;
		border-color: #FFC107;
	} 
	textarea {
		border: 2px solid #a0b3b0;
		resize: vertical;
	} 
	.field-wrap {
		position: relative;
		margin-bottom: 40px;
	} 
	.button {
		border: 0;
		outline: none;
		font-size: 1em;
		font-weight: 600;
		text-transform: uppercase;
		background:#05e0e2;
		color: #fff;
		cursor: pointer;
		-webkit-transition: all 0.5s ease;
		-moz-transition: all 0.5s ease;
		-o-transition: all 0.5s ease;
		-ms-transition: all 0.5s ease;
		transition: all 0.5s ease;
		-webkit-appearance: none;
		margin-top:2.2em;
		-webkit-border-radius: 5px;
		-moz-border-radius: 5px; 
		border-radius: 5px;
	}
	.button:hover, .button:focus {
		background: #00cacc;
		letter-spacing: 7px;
	}
	.button-block {
		display: block;
		width: 100%;
	} 
	.forgot {
		text-align: center;
		font-size: 1em;
		font-weight: 300;
		letter-spacing: 1px;
	}

	.styled-select.slate {
		color: rgba(255, 255, 255, 0.45);
		height: 34px;
		width: 2rem;
	}

	.styled-select.slate select {
		border: 1px solid #ccc;
		font-size: 16px;
		height: 34px;
		width: 24.3rem;
	}
}
</style>
