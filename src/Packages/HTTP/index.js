import axios from 'axios';
// import auth from '@/packages/Auth/auth';


let axiosInstance = axios.create({
	// baseURL: 'https://afternoon-oasis-93157.herokuapp.com/'
	baseURL: 'https://coursegame.herokuapp.com/'
})

export default axiosInstance