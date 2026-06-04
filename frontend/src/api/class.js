import request from './request'

export const getClassList = (pageNum, pageSize, className, gradeId) => {
  return request.get('/api/classes', {
    params: {
      pageNum,
      pageSize,
      className,
      gradeId
    }
  })
}

export const getClassById = (id) => {
  return request.get(`/api/classes/${id}`)
}

export const addClass = (data) => {
  return request.post('/api/classes', data)
}

export const updateClass = (data) => {
  return request.put('/api/classes', data)
}

export const deleteClass = (id) => {
  return request.delete(`/api/classes/${id}`)
}
