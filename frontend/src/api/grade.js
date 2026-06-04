import request from './request'

export const getGradeList = (pageNum, pageSize, gradeName) => {
  return request.get('/api/grades', {
    params: {
      pageNum,
      pageSize,
      gradeName
    }
  })
}

export const getGradeById = (id) => {
  return request.get(`/api/grades/${id}`)
}

export const addGrade = (data) => {
  return request.post('/api/grades', data)
}

export const updateGrade = (data) => {
  return request.put('/api/grades', data)
}

export const deleteGrade = (id) => {
  return request.delete(`/api/grades/${id}`)
}
