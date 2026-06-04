<template>
  <div class="dashboard">
    <el-row :gutter="20" class="stats-row">
      <el-col :xs="24" :sm="12" :md="6">
        <div class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%)">
            <el-icon><User /></el-icon>
          </div>
          <div class="stat-content">
            <div class="stat-value">{{ studentCount }}</div>
            <div class="stat-label">学生总数</div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6">
        <div class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%)">
            <el-icon><Management /></el-icon>
          </div>
          <div class="stat-content">
            <div class="stat-value">{{ classCount }}</div>
            <div class="stat-label">班级总数</div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6">
        <div class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%)">
            <el-icon><Document /></el-icon>
          </div>
          <div class="stat-content">
            <div class="stat-value">{{ gradeCount }}</div>
            <div class="stat-label">年级总数</div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6">
        <div class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #fa709a 0%, #fee140 100%)">
            <el-icon><BarChart /></el-icon>
          </div>
          <div class="stat-content">
            <div class="stat-value">{{ avgStudentsPerClass }}</div>
            <div class="stat-label">平均班级人数</div>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :xs="24" :md="12">
        <el-card class="box-card">
          <template #header>
            <div style="display: flex; justify-content: space-between; align-items: center">
              <span>各年级学生分布</span>
            </div>
          </template>
          <div id="gradeChart" style="width: 100%; height: 300px"></div>
        </el-card>
      </el-col>
      <el-col :xs="24" :md="12">
        <el-card class="box-card">
          <template #header>
            <div style="display: flex; justify-content: space-between; align-items: center">
              <span>班级学生数统计</span>
            </div>
          </template>
          <div id="classChart" style="width: 100%; height: 300px"></div>
        </el-card>
      </el-col>
    </el-row>

    <el-row style="margin-top: 20px">
      <el-col :xs="24">
        <el-card class="box-card">
          <template #header>
            <div style="display: flex; justify-content: space-between; align-items: center">
              <span>最近添加的学生</span>
            </div>
          </template>
          <el-table :data="recentStudents" stripe>
            <el-table-column prop="studentName" label="姓名" width="120" />
            <el-table-column prop="studentNo" label="学号" width="120" />
            <el-table-column prop="className" label="班级" />
            <el-table-column prop="age" label="年龄" width="80" />
            <el-table-column prop="gender" label="性别" width="80">
              <template #default="{ row }">
                {{ row.gender === 1 ? '男' : '女' }}
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { User, Management, Document, BarChart } from '@element-plus/icons-vue'
import * as echarts from 'echarts'
import { getStudentList } from '@/api/student'
import { getClassList } from '@/api/class'
import { getGradeList } from '@/api/grade'

const studentCount = ref(0)
const classCount = ref(0)
const gradeCount = ref(0)
const recentStudents = ref([])

const avgStudentsPerClass = computed(() => {
  if (classCount.value === 0) return 0
  return Math.round(studentCount.value / classCount.value)
})

const loadStats = async () => {
  try {
    // 获取学生总数
    const studentRes = await getStudentList(1, 1000)
    const students = studentRes.data?.records || studentRes.data || []
    studentCount.value = Array.isArray(students) ? students.length : 0
    recentStudents.value = students.slice(0, 5)

    // 获取班级总数
    const classRes = await getClassList(1, 1000)
    const classes = classRes.data?.records || classRes.data || []
    classCount.value = Array.isArray(classes) ? classes.length : 0

    // 获取年级总数
    const gradeRes = await getGradeList(1, 1000)
    const grades = gradeRes.data?.records || gradeRes.data || []
    gradeCount.value = Array.isArray(grades) ? grades.length : 0

    // 初始化图表
    initCharts(students, grades)
  } catch (error) {
    console.error('加载统计数据失败:', error)
  }
}

const initCharts = (students, grades) => {
  // 年级学生分布图
  const gradeChart = echarts.init(document.getElementById('gradeChart'))
  const gradeNames = grades.map(g => g.gradeName)
  const gradeStudentCounts = grades.map(grade => {
    return students.filter(s => s.gradeId === grade.id).length
  })

  gradeChart.setOption({
    tooltip: { trigger: 'axis' },
    xAxis: { type: 'category', data: gradeNames },
    yAxis: { type: 'value' },
    series: [{
      data: gradeStudentCounts,
      type: 'bar',
      itemStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          { offset: 0, color: '#83bff6' },
          { offset: 0.5, color: '#188df0' },
          { offset: 1, color: '#188df0' }
        ])
      }
    }]
  })

  // 班级学生数统计
  const classChart = echarts.init(document.getElementById('classChart'))
  classChart.setOption({
    tooltip: { trigger: 'item' },
    series: [{
      name: '学生数',
      type: 'pie',
      radius: '50%',
      data: grades.map((grade, idx) => ({
        value: gradeStudentCounts[idx],
        name: grade.gradeName
      })),
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }]
  })

  window.addEventListener('resize', () => {
    gradeChart.resize()
    classChart.resize()
  })
}

onMounted(() => {
  loadStats()
})
</script>

<style scoped>
.dashboard {
  padding: 20px;
}

.stats-row {
  width: 100%;
}

.stat-card {
  background: white;
  border-radius: 4px;
  padding: 20px;
  display: flex;
  align-items: center;
  gap: 15px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
}

.stat-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
}

.stat-icon {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 30px;
}

.stat-content {
  flex: 1;
}

.stat-value {
  font-size: 28px;
  font-weight: bold;
  color: #333;
  margin-bottom: 5px;
}

.stat-label {
  font-size: 14px;
  color: #909399;
}

.box-card {
  margin-bottom: 20px;
}

:deep(.el-card__header) {
  background-color: #fafafa;
  border-bottom: 1px solid #ebeef5;
  padding: 18px 20px;
}
</style>
