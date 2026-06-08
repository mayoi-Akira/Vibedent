<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="班级名称" prop="className">
        <el-input
          v-model="queryParams.className"
          placeholder="请输入班级名称"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班级编码" prop="classCode">
        <el-input
          v-model="queryParams.classCode"
          placeholder="请输入班级编码"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属年级" prop="gradeId">
        <el-select v-model="queryParams.gradeId" placeholder="请选择年级" clearable style="width: 240px">
          <el-option
            v-for="g in gradeOptions"
            :key="g.id"
            :label="g.gradeName"
            :value="g.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['business:class:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['business:class:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['business:class:remove']">删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="classList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" width="80" />
      <el-table-column label="班级名称" align="center" prop="className" :show-overflow-tooltip="true" />
      <el-table-column label="班级编码" align="center" prop="classCode" width="160" />
      <el-table-column label="所属年级" align="center" prop="gradeId" width="120">
        <template slot-scope="scope">
          <span>{{ getGradeName(scope.row.gradeId) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="班主任" align="center" prop="teacher" width="120" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="160">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['business:class:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['business:class:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改班级对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="班级名称" prop="className">
          <el-input v-model="form.className" placeholder="请输入班级名称" />
        </el-form-item>
        <el-form-item label="班级编码" prop="classCode">
          <el-input v-model="form.classCode" placeholder="请输入班级编码" />
        </el-form-item>
        <el-form-item label="所属年级" prop="gradeId">
          <el-select v-model="form.gradeId" placeholder="请选择年级" style="width: 100%">
            <el-option
              v-for="g in gradeOptions"
              :key="g.id"
              :label="g.gradeName"
              :value="g.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="班主任" prop="teacher">
          <el-input v-model="form.teacher" placeholder="请输入班主任姓名" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listClassInfo, getClassInfo, delClassInfo, addClassInfo, updateClassInfo } from "@/api/business/classInfo"
import { allGrade } from "@/api/business/grade"

export default {
  name: "ClassInfo",
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      classList: [],
      gradeOptions: [],
      title: "",
      open: false,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        className: undefined,
        classCode: undefined,
        gradeId: undefined
      },
      form: {},
      rules: {
        className: [{ required: true, message: "班级名称不能为空", trigger: "blur" }],
        classCode: [{ required: true, message: "班级编码不能为空", trigger: "blur" }],
        gradeId: [{ required: true, message: "所属年级不能为空", trigger: "change" }]
      }
    }
  },
  created() {
    this.getList()
    this.loadGrades()
  },
  methods: {
    getList() {
      this.loading = true
      listClassInfo(this.queryParams).then(response => {
        this.classList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    loadGrades() {
      allGrade().then(response => {
        this.gradeOptions = response.data
      })
    },
    getGradeName(gradeId) {
      const g = this.gradeOptions.find(item => item.id === gradeId)
      return g ? g.gradeName : ''
    },
    cancel() {
      this.open = false
      this.reset()
    },
    reset() {
      this.form = {
        id: undefined,
        className: undefined,
        classCode: undefined,
        gradeId: undefined,
        teacher: undefined
      }
      this.resetForm("form")
    },
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加班级"
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getClassInfo(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改班级"
      })
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateClassInfo(this.form).then(() => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addClassInfo(this.form).then(() => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除班级编号为"' + ids + '"的数据项？').then(function () {
        return delClassInfo(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => { })
    }
  }
}
</script>
