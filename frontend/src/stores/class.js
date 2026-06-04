import { defineStore } from 'pinia'
import { reactive } from 'vue'
import { getClassList } from '@/api/class'

export const useClassStore = defineStore('class', () => {
  const state = reactive({
    list: [],
    total: 0,
    pageNum: 1,
    pageSize: 10,
    loading: false
  })

  const fetchClasses = async (className = '', gradeId = '') => {
    state.loading = true
    try {
      const response = await getClassList(state.pageNum, state.pageSize, className, gradeId)
      if (response.data) {
        state.list = response.data.records || response.data
        state.total = response.data.total || response.data.length
      }
    } catch (error) {
      console.error('获取班级列表失败:', error)
    } finally {
      state.loading = false
    }
  }

  return {
    state,
    fetchClasses
  }
})
