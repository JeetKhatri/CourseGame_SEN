<template>
	<div class="ta">
		<div>
			<div class="column" id="button">
				<button class="button is-info" @click="addTa=true">Add New</button>
			</div>
			<div class="column" id="quiz">
				<h2>Teaching Assistants</h2>
			</div>
		</div>
		<div class="columns" id="lists" v-for="ta in data" >
			<div class="column">
				<h4>{{ta.emailId}}</h4>
			</div>
			<div class="column">
				<h4>{{ta.userName}}</h4>
			</div>
			<div class="column">
				<span class="tag is-info" @click="removeTa()">Remove</span>
			</div>
		</div>	
		<addTa v-if="addTa" @closeAddTa="close"></addTa>
	</div>
</template>

<script type="text/javascript">
import addTa from '@/components/AddNewTaModal';
import HTTP from '@/packages/HTTP';
export default {
	name: 'ta',
	components: {
		addTa
	},
	
	data() {
		return{
			batchid: '',
			data:[],
			addTa: false
		}
	},
	created(){
		this.fetchTa()
		this.getId()
	},
	
	methods: {
		close() {
			this.addTa = false
			this.fetchTa()
		},
		fetchTa(){
			this.batchid =this.$route.params.batchid,
			HTTP.get(`rest/ta/ta-list?batchid=`+this.batchid)
			.then(response => {
				this.data = response.data.TABeans;
				console.log(this.data)
			})
			.catch(e=>{
				console.log(e);
			})
		},
		getId () {
			var id = window.localStorage.getItem('faculty_id')
			if (id != null) {
				this.authToken = id;
				return true
			} else {
				this.$router.push('/')
			}
		}
	}

}
</script>

<style lang="scss">
.ta {
	.columns {
		margin: 0px;
	}
	#button {
		float: right;
	}
	#lists {
		padding: 0.1rem;
		padding-left: 5rem;
	}

	span:hover {
		cursor: pointer;
	}

	h2 {
		font-size: 24px;
		text-decoration: underline;
		display: flex;
		justify-content: center;
		padding-bottom: 0.5rem;
	}
}
</style>