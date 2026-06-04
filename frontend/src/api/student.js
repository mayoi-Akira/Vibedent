import request from './request'

export const getStudentList = (pageNum, pageSize, studentName, classId) => {
  return request.get('/api/students', {
    params: {
      pageNum,
      pageSize,
      studentName,
      classId
    }
  })
}

export const getStudentById = (id) => {
  return request.get(`/api/students/${id}`)
}

export const addStudent = (data) => {
  return request.post('/api/students', data)
}

export const updateStudent = (data) => {
  return request.put('/api/students', data)
}

export const deleteStudent = (id) => {
  return request.delete(`/api/students/${id}`)
}
