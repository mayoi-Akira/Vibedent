import axios from 'axios'

const request = axios.create({
  baseURL: 'http://localhost:8081',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
})

// 响应拦截器
request.interceptors.response.use(
  response => {
    const { data } = response
    return data
  },
  error => {
    console.error('请求失败:', error)
    return Promise.reject(error)
  }
)

export default request
