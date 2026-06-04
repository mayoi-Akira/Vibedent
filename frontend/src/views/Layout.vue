<template>
  <div class="layout">
    <el-container>
      <!-- 侧边栏 -->
      <el-aside width="200px" class="sidebar">
        <div class="logo">
          <h1>📚 学生管理系统</h1>
        </div>
        <el-menu router class="menu" default-active="/student">
          <el-menu-item index="/" class="menu-item">
            <el-icon><DataAnalysis /></el-icon>
            <span>仪表盘</span>
          </el-menu-item>
          <el-menu-item index="/student" class="menu-item">
            <el-icon><User /></el-icon>
            <span>学生管理</span>
          </el-menu-item>
          <el-menu-item index="/class" class="menu-item">
            <el-icon><Management /></el-icon>
            <span>班级管理</span>
          </el-menu-item>
          <el-menu-item index="/grade" class="menu-item">
            <el-icon><Document /></el-icon>
            <span>年级管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <!-- 主内容区 -->
      <el-container>
        <el-header class="header">
          <div class="header-content">
            <span>欢迎使用学生管理系统</span>
          </div>
        </el-header>
        <el-main class="main">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { DataAnalysis, User, Management, Document } from '@element-plus/icons-vue'
</script>

<style scoped>
.layout {
  height: 100vh;
}

.el-container {
  height: 100%;
}

.sidebar {
  background-color: #245468;
  border-right: 1px solid #e4e7eb;
}

.logo {
  padding: 20px;
  text-align: center;
  border-bottom: 1px solid #e4e7eb;
}

.logo h1 {
  margin: 0;
  color: white;
  font-size: 18px;
  font-weight: bold;
}

.menu {
  border-right: none;
  background-color: #245468;
}

.menu-item {
  color: #bfcdc9 !important;
}

:deep(.el-menu-item) {
  background-color: #245468 !important;
  color: #bfcdc9 !important;
}

:deep(.el-menu-item:hover) {
  background-color: #0f3549 !important;
  color: white !important;
}

:deep(.el-menu-item.is-active) {
  background-color: #0a5a8a !important;
  color: white !important;
}

:deep(.el-menu--collapse) {
  width: 64px;
}

.header {
  background-color: #ffffff;
  border-bottom: 1px solid #e4e7eb;
  display: flex;
  align-items: center;
  padding: 0 20px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.04);
}

.header-content {
  width: 100%;
  font-size: 18px;
  font-weight: 500;
  color: #333;
}

.main {
  background-color: #f5f7fa;
  padding: 20px;
}
</style>
