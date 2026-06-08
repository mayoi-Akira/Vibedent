<template>
  <div class="app-container home">
    <!-- 统计卡片 -->
    <el-row :gutter="20" class="stats-row">
      <el-col :span="8">
        <div class="stat-card student-card" @click="$router.push('/business/student')">
          <div class="stat-icon"><i class="el-icon-user-solid"></i></div>
          <div class="stat-info">
            <div class="stat-value">{{ stats.studentCount }}</div>
            <div class="stat-label">学生总数</div>
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="stat-card class-card" @click="$router.push('/business/class')">
          <div class="stat-icon"><i class="el-icon-office-building"></i></div>
          <div class="stat-info">
            <div class="stat-value">{{ stats.classCount }}</div>
            <div class="stat-label">班级总数</div>
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="stat-card grade-card" @click="$router.push('/business/grade')">
          <div class="stat-icon"><i class="el-icon-s-data"></i></div>
          <div class="stat-info">
            <div class="stat-value">{{ stats.gradeCount }}</div>
            <div class="stat-label">年级总数</div>
          </div>
        </div>
      </el-col>
    </el-row>

    <!-- 快捷入口 -->
    <el-row :gutter="20" class="quick-links">
      <el-col :span="24">
        <el-card shadow="never" class="welcome-card">
          <div class="welcome-title">欢迎使用学生管理系统</div>
          <div class="welcome-desc">您可以通过以下快捷入口快速进入各管理模块</div>
          <div class="link-buttons">
            <el-button type="primary" icon="el-icon-user" @click="$router.push('/business/student')">学生管理</el-button>
            <el-button type="success" icon="el-icon-office-building" @click="$router.push('/business/class')">班级管理</el-button>
            <el-button type="warning" icon="el-icon-s-data" @click="$router.push('/business/grade')">年级管理</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from '@/utils/request'

export default {
  name: "Index",
  data() {
    return {
      stats: {
        studentCount: 0,
        classCount: 0,
        gradeCount: 0
      }
    }
  },
  created() {
    this.loadStats()
  },
  methods: {
    loadStats() {
      request({
        url: '/business/dashboard/stats',
        method: 'get'
      }).then(response => {
        this.stats = response.data
      })
    }
  }
}
</script>

<style scoped lang="scss">
.home {
  .stats-row {
    margin-bottom: 20px;
  }

  .stat-card {
    display: flex;
    align-items: center;
    padding: 25px 20px;
    border-radius: 6px;
    cursor: pointer;
    color: #fff;
    transition: all 0.3s ease;

    &:hover {
      transform: translateY(-3px);
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
    }

    .stat-icon {
      font-size: 48px;
      margin-right: 20px;
      opacity: 0.8;
    }

    .stat-value {
      font-size: 32px;
      font-weight: bold;
    }

    .stat-label {
      font-size: 15px;
      opacity: 0.9;
    }
  }

  .student-card {
    background: linear-gradient(135deg, #1890ff, #36cfc9);
  }

  .class-card {
    background: linear-gradient(135deg, #52c41a, #73d13d);
  }

  .grade-card {
    background: linear-gradient(135deg, #faad14, #ffc53d);
  }

  .quick-links {
    .welcome-card {
      text-align: center;
      padding: 20px;

      .welcome-title {
        font-size: 22px;
        font-weight: bold;
        color: #303133;
        margin-bottom: 10px;
      }

      .welcome-desc {
        color: #909399;
        margin-bottom: 20px;
      }

      .link-buttons .el-button {
        margin: 0 10px;
        padding: 12px 28px;
        font-size: 15px;
      }
    }
  }
}
</style>

