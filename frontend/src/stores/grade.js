import { defineStore } from 'pinia'
import { reactive } from 'vue'
import { getGradeList } from '@/api/grade'

export const useGradeStore = defineStore('grade', () => {
  const state = reactive({
    list: [],
    total: 0,
    pageNum: 1,
    pageSize: 10,
    loading: false
  })

  const fetchGrades = async (gradeName = '') => {
    state.loading = true
    try {
      const response = await getGradeList(state.pageNum, state.pageSize, gradeName)
      if (response.data) {
        state.list = response.data.records || response.data
        state.total = response.data.total || response.data.length
      }
    } catch (error) {
      console.error('获取年级列表失败:', error)
    } finally {
      state.loading = false
    }
  }

  return {
    state,
    fetchGrades
  }
})
