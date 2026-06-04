<template>
  <div class="student-manage">
    <el-card class="box-card">
      <template #header>
        <div style="display: flex; justify-content: space-between; align-items: center">
          <span style="font-size: 16px; font-weight: bold">学生管理</span>
          <el-button type="primary" @click="openDialog('add')">
            <el-icon style="margin-right: 5px"><Plus /></el-icon>
            新增学生
          </el-button>
        </div>
      </template>

      <!-- 搜索栏 -->
      <div class="search-bar" style="margin-bottom: 20px">
        <el-input
          v-model="searchForm.studentName"
          placeholder="搜索学生姓名..."
          style="width: 200px; margin-right: 10px"
        />
        <el-select
          v-model="searchForm.classId"
          placeholder="选择班级"
          clearable
          style="width: 200px; margin-right: 10px"
        >
          <el-option
            v-for="item in classList"
            :key="item.id"
            :label="item.className"
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
        <el-table-column prop="studentNo" label="学号" width="120" />
        <el-table-column prop="studentName" label="姓名" width="100" />
        <el-table-column prop="gender" label="性别" width="80">
          <template #default="{ row }">
            {{ row.gender === 1 ? '男' : row.gender === 2 ? '女' : '未知' }}
          </template>
        </el-table-column>
        <el-table-column prop="age" label="年龄" width="80" />
        <el-table-column prop="className" label="班级" width="120" />
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
        <el-form-item label="学号" prop="studentNo">
          <el-input v-model="form.studentNo" placeholder="输入学号" />
        </el-form-item>
        <el-form-item label="姓名" prop="studentName">
          <el-input v-model="form.studentName" placeholder="输入学生姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="form.gender" placeholder="选择性别">
            <el-option label="男" :value="1" />
            <el-option label="女" :value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model.number="form.age" type="number" placeholder="输入年龄" />
        </el-form-item>
        <el-form-item label="班级" prop="classId">
          <el-select v-model="form.classId" placeholder="选择班级">
            <el-option
              v-for="item in classList"
              :key="item.id"
              :label="item.className"
              :value="item.id"
            />
          </el-select>
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
import { getStudentList, addStudent, updateStudent, deleteStudent } from '@/api/student'
import { getClassList } from '@/api/class'

const tableData = ref([])
const total = ref(0)
const pageNum = ref(1)
const pageSize = ref(10)
const loading = ref(false)
const dialogVisible = ref(false)
const dialogTitle = ref('新增学生')
const formRef = ref(null)
const classList = ref([])
const operationType = ref('add') // 'add' 或 'edit'

const searchForm = reactive({
  studentName: '',
  classId: ''
})

const form = reactive({
  id: '',
  studentNo: '',
  studentName: '',
  gender: '',
  age: '',
  classId: ''
})

const rules = {
  studentNo: [{ required: true, message: '学号不能为空', trigger: 'blur' }],
  studentName: [{ required: true, message: '姓名不能为空', trigger: 'blur' }],
  gender: [{ required: true, message: '性别不能为空', trigger: 'change' }],
  age: [{ required: true, message: '年龄不能为空', trigger: 'blur' }],
  classId: [{ required: true, message: '班级不能为空', trigger: 'change' }]
}

const loadData = async () => {
  loading.value = true
  try {
    const response = await getStudentList(pageNum.value, pageSize.value, searchForm.studentName, searchForm.classId)
    tableData.value = response.data?.records || response.data || []
    total.value = response.data?.total || tableData.value.length
  } catch (error) {
    ElMessage.error('加载数据失败')
    console.error(error)
  } finally {
    loading.value = false
  }
}

const loadClassList = async () => {
  try {
    const response = await getClassList(1, 1000)
    classList.value = response.data?.records || response.data || []
  } catch (error) {
    console.error('加载班级列表失败:', error)
  }
}

const openDialog = (type, row) => {
  operationType.value = type
  if (type === 'add') {
    dialogTitle.value = '新增学生'
    Object.assign(form, {
      id: '',
      studentNo: '',
      studentName: '',
      gender: '',
      age: '',
      classId: ''
    })
  } else {
    dialogTitle.value = '编辑学生'
    Object.assign(form, row)
  }
  dialogVisible.value = true
}

const handleSubmit = async () => {
  await formRef.value.validate()
  try {
    if (operationType.value === 'add') {
      await addStudent(form)
      ElMessage.success('添加成功')
    } else {
      await updateStudent(form)
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
  ElMessageBox.confirm('确定删除该学生吗？', '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      await deleteStudent(row.id)
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
  searchForm.studentName = ''
  searchForm.classId = ''
  pageNum.value = 1
  loadData()
}

onMounted(() => {
  loadClassList()
  loadData()
})
</script>

<style scoped>
.student-manage {
  padding: 20px;
}

.search-bar {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
}
</style>
