import axios from 'axios'

const GITHUB_TOKEN = process.env.REACT_APP_GITHUB_TOKEN
console.log(GITHUB_TOKEN);
const api = axios.create({

    baseURL : 'https://api.github.com',
    headers: {
        Authorization: `Bearer ${GITHUB_TOKEN}`,
        Accept: 'appication/vnd.github+json'
    }
});

export default api;