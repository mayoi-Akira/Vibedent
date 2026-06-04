<template>
  <div class="class-manage">
    <el-card class="box-card">
      <template #header>
        <div style="display: flex; justify-content: space-between; align-items: center">
          <span style="font-size: 16px; font-weight: bold">班级管理</span>
          <el-button type="primary" @click="openDialog('add')">
            <el-icon style="margin-right: 5px"><Plus /></el-icon>
            新增班级
          </el-button>
        </div>
      </template>

      <!-- 搜索栏 -->
      <div class="search-bar" style="margin-bottom: 20px">
        <el-input
          v-model="searchForm.className"
          placeholder="搜索班级名..."
          style="width: 200px; margin-right: 10px"
        />
        <el-select
          v-model="searchForm.gradeId"
          placeholder="选择年级"
          clearable
          style="width: 200px; margin-right: 10px"
        >
          <el-option
            v-for="item in gradeList"
            :key="item.id"
            :label="item.gradeName"
            :value="item.id"
          />
        </el-select>
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
        <el-table-column prop="classCode" label="班级编码" width="120" />
        <el-table-column prop="className" label="班级名称" width="150" />
        <el-table-column prop="gradeName" label="年级" width="100" />
        <el-table-column prop="teacher" label="班主任" width="120" />
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
        <el-form-item label="班级编码" prop="classCode">
          <el-input v-model="form.classCode" placeholder="输入班级编码" />
        </el-form-item>
        <el-form-item label="班级名称" prop="className">
          <el-input v-model="form.className" placeholder="输入班级名称" />
        </el-form-item>
        <el-form-item label="年级" prop="gradeId">
          <el-select v-model="form.gradeId" placeholder="选择年级">
            <el-option
              v-for="item in gradeList"
              :key="item.id"
              :label="item.gradeName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="班主任" prop="teacher">
          <el-input v-model="form.teacher" placeholder="输入班主任名字" />
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
import { getClassList, addClass, updateClass, deleteClass } from '@/api/class'
import { getGradeList } from '@/api/grade'

const tableData = ref([])
const total = ref(0)
const pageNum = ref(1)
const pageSize = ref(10)
const loading = ref(false)
const dialogVisible = ref(false)
const dialogTitle = ref('新增班级')
const formRef = ref(null)
const gradeList = ref([])
const operationType = ref('add')

const searchForm = reactive({
  className: '',
  gradeId: ''
})

const form = reactive({
  id: '',
  classCode: '',
  className: '',
  gradeId: '',
  teacher: ''
})

const rules = {
  classCode: [{ required: true, message: '班级编码不能为空', trigger: 'blur' }],
  className: [{ required: true, message: '班级名称不能为空', trigger: 'blur' }],
  gradeId: [{ required: true, message: '年级不能为空', trigger: 'change' }],
  teacher: [{ required: true, message: '班主任不能为空', trigger: 'blur' }]
}

const loadData = async () => {
  loading.value = true
  try {
    const response = await getClassList(pageNum.value, pageSize.value, searchForm.className, searchForm.gradeId)
    tableData.value = response.data?.records || response.data || []
    total.value = response.data?.total || tableData.value.length
  } catch (error) {
    ElMessage.error('加载数据失败')
    console.error(error)
  } finally {
    loading.value = false
  }
}

const loadGradeList = async () => {
  try {
    const response = await getGradeList(1, 1000)
    gradeList.value = response.data?.records || response.data || []
  } catch (error) {
    console.error('加载年级列表失败:', error)
  }
}

const openDialog = (type, row) => {
  operationType.value = type
  if (type === 'add') {
    dialogTitle.value = '新增班级'
    Object.assign(form, {
      id: '',
      classCode: '',
      className: '',
      gradeId: '',
      teacher: ''
    })
  } else {
    dialogTitle.value = '编辑班级'
    Object.assign(form, row)
  }
  dialogVisible.value = true
}

const handleSubmit = async () => {
  await formRef.value.validate()
  try {
    if (operationType.value === 'add') {
      await addClass(form)
      ElMessage.success('添加成功')
    } else {
      await updateClass(form)
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
  ElMessageBox.confirm('确定删除该班级吗？', '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      await deleteClass(row.id)
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
  searchForm.className = ''
  searchForm.gradeId = ''
  pageNum.value = 1
  loadData()
}

onMounted(() => {
  loadGradeList()
  loadData()
})
</script>

<style scoped>
.class-manage {
  padding: 20px;
}

.search-bar {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
}
</style>
