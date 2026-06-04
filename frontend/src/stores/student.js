import { defineStore } from 'pinia'
import { reactive } from 'vue'
import { getStudentList } from '@/api/student'

export const useStudentStore = defineStore('student', () => {
  const state = reactive({
    list: [],
    total: 0,
    pageNum: 1,
    pageSize: 10,
    loading: false
  })

  const fetchStudents = async (studentName = '', classId = '') => {
    state.loading = true
    try {
      const response = await getStudentList(state.pageNum, state.pageSize, studentName, classId)
      if (response.data) {
        state.list = response.data.records || response.data
        state.total = response.data.total || response.data.length
      }
    } catch (error) {
      console.error('获取学生列表失败:', error)
    } finally {
      state.loading = false
    }
  }

  return {
    state,
    fetchStudents
  }
})
