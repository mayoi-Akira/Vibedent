<template>
  <div class="grade-manage">
    <el-card class="box-card">
      <template #header>
        <div style="display: flex; justify-content: space-between; align-items: center">
          <span style="font-size: 16px; font-weight: bold">年级管理</span>
          <el-button type="primary" @click="openDialog('add')">
            <el-icon style="margin-right: 5px"><Plus /></el-icon>
            新增年级
          </el-button>
        </div>
      </template>

      <!-- 搜索栏 -->
      <div class="search-bar" style="margin-bottom: 20px">
        <el-input
          v-model="searchForm.gradeName"
          placeholder="搜索年级名..."
          style="width: 200px; margin-right: 10px"
        />
        <el-button type="primary" @click="handleSearch">
          <el-icon style="margin-right: 5px"><Search /></el-icon>
          搜索
        </el-button>
        <el-button @click="handleReset">
          <el-icon style="margin-right: 5px"><Delete /></el-icon>
          重置
        </el-button>
      </div>

      <!-- 表格 -->
      <el-table :data="tableData" stripe v-loading="loading" style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="gradeCode" label="年级编码" width="120" />
        <el-table-column prop="gradeName" label="年级名称" width="150" />
        <el-table-column prop="createTime" label="创建时间" width="180">
          <template #default="{ row }">
            {{ formatTime(row.createTime) }}
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="更新时间" width="180">
          <template #default="{ row }">
            {{ formatTime(row.updateTime) }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="{ row }">
            <el-button link type="primary" size="small" @click="openDialog('edit', row)">编辑</el-button>
            <el-button link type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div style="margin-top: 20px; text-align: right">
        <el-pagination
          v-model:current-page="pageNum"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20, 50]"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          @current-change="loadData"
          @size-change="loadData"
        />
      </div>
    </el-card>

    <!-- 新增/编辑对话框 -->
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="500px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="年级编码" prop="gradeCode">
          <el-input v-model="form.gradeCode" placeholder="输入年级编码 (如: G001)" />
        </el-form-item>
        <el-form-item label="年级名称" prop="gradeName">
          <el-input v-model="form.gradeName" placeholder="输入年级名称 (如: 高一年级)" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSubmit">提交</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Search, Delete } from '@element-plus/icons-vue'
import { getGradeList, addGrade, updateGrade, deleteGrade } from '@/api/grade'

const tableData = ref([])
const total = ref(0)
const pageNum = ref(1)
const pageSize = ref(10)
const loading = ref(false)
const dialogVisible = ref(false)
const dialogTitle = ref('新增年级')
const formRef = ref(null)
const operationType = ref('add')

const searchForm = reactive({
  gradeName: ''
})

const form = reactive({
  id: '',
  gradeCode: '',
  gradeName: ''
})

const rules = {
  gradeCode: [{ required: true, message: '年级编码不能为空', trigger: 'blur' }],
  gradeName: [{ required: true, message: '年级名称不能为空', trigger: 'blur' }]
}

const formatTime = (time) => {
  if (!time) return '-'
  const date = new Date(time)
  return date.toLocaleString('zh-CN')
}

const loadData = async () => {
  loading.value = true
  try {
    const response = await getGradeList(pageNum.value, pageSize.value, searchForm.gradeName)
    tableData.value = response.data?.records || response.data || []
    total.value = response.data?.total || tableData.value.length
  } catch (error) {
    ElMessage.error('加载数据失败')
    console.error(error)
  } finally {
    loading.value = false
  }
}

const openDialog = (type, row) => {
  operationType.value = type
  if (type === 'add') {
    dialogTitle.value = '新增年级'
    Object.assign(form, {
      id: '',
      gradeCode: '',
      gradeName: ''
    })
  } else {
    dialogTitle.value = '编辑年级'
    Object.assign(form, row)
  }
  dialogVisible.value = true
}

const handleSubmit = async () => {
  await formRef.value.validate()
  try {
    if (operationType.value === 'add') {
      await addGrade(form)
      ElMessage.success('添加成功')
    } else {
      await updateGrade(form)
      ElMessage.success('编辑成功')
    }
    dialogVisible.value = false
    loadData()
  } catch (error) {
    ElMessage.error(operationType.value === 'add' ? '添加失败' : '编辑失败')
    console.error(error)
  }
}

const handleDelete = (row) => {
  ElMessageBox.confirm('确定删除该年级吗？', '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      await deleteGrade(row.id)
      ElMessage.success('删除成功')
      loadData()
    } catch (error) {
      ElMessage.error('删除失败')
      console.error(error)
    }
  }).catch(() => {
    ElMessage.info('已取消删除')
  })
}

const handleSearch = () => {
  pageNum.value = 1
  loadData()
}

const handleReset = () => {
  searchForm.gradeName = ''
  pageNum.value = 1
  loadData()
}

onMounted(() => {
  loadData()
})
</script>

<style scoped>
.grade-manage {
  padding: 20px;
}

.search-bar {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
}
</style>
