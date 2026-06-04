import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/views/Layout.vue'
import Dashboard from '@/views/Dashboard.vue'
import StudentManage from '@/views/student/StudentManage.vue'
import ClassManage from '@/views/class/ClassManage.vue'
import GradeManage from '@/views/grade/GradeManage.vue'

const routes = [
  {
    path: '/',
    component: Layout,
    children: [
      {
        path: '',
        name: 'Dashboard',
        component: Dashboard
      },
      {
        path: 'student',
        name: 'StudentManage',
        component: StudentManage
      },
      {
        path: 'class',
        name: 'ClassManage',
        component: ClassManage
      },
      {
        path: 'grade',
        name: 'GradeManage',
        component: GradeManage
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
