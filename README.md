# 学生管理系统

基于 [若依 (RuoYi)](http://ruoyi.vip) 框架二次开发的学生信息管理系统，实现年级、班级、学生的完整 CRUD 管理。

> ⚠️ **AI 驱动开发声明**  
> 本项目**所有代码和文档**均由 AI 生成，无任何人工手写代码。
>
> - **AI 模型**：Claude Opus 4.8（Anthropic）+ DeepSeek-V4-Pro
> - **开发工具**：[Claude Code](https://claude.com/claude-code)（Anthropic 官方 CLI）+ VS Code 插件
> - **开发方式**：自然语言描述需求 → AI 生成代码 → 人工审核验收
> - **涵盖范围**：后端实体/Mapper/Service/Controller、前端 Vue 页面/API 层、数据库菜单配置、项目文档（包括本 README）
>
> 这是一次完全的 **Vibe Coding** 实践——开发者只需描述"想要什么"，无需手写一行代码。

---

## 系统功能

### 核心业务模块

| 模块 | 功能说明 |
|---|---|
| 📊 首页仪表盘 | 学生/班级/年级统计概览 + 快捷入口 |
| 👨‍🎓 学生管理 | 学生信息 CRUD，支持按学号、姓名、性别、班级筛选 |
| 🏫 班级管理 | 班级信息 CRUD，关联年级下拉选择 |
| 🎓 年级管理 | 年级信息 CRUD |

### 系统管理（保留自若依框架）

| 模块 | 说明 |
|---|---|
| 用户管理 | 系统用户 CRUD + 角色分配 |
| 角色管理 | 角色权限配置 + 用户分配 |
| 菜单管理 | 动态菜单/路由配置 |
| 部门管理 | 组织架构树 |
| 岗位管理 | 岗位字典维护 |
| 字典管理 | 系统字典类型与数据 |
| 参数设置 | 系统参数配置 |
| 通知公告 | 系统通知发布 |
| 日志管理 | 操作日志 + 登录日志 |

### 系统监控（保留自若依框架）

在线用户、定时任务、数据监控（Druid）、服务监控、缓存监控（Redis）

### 系统工具（保留自若依框架）

表单构建、代码生成、系统接口（Swagger）

---

## 技术栈

| 层级 | 技术 | 版本 |
|---|---|---|
| 框架 | Spring Boot | 4.0.3 |
| 语言 | Java | 17 |
| ORM | MyBatis + MyBatis Plus | 3.x |
| 数据库 | MySQL | 8.x |
| 连接池 | Druid | 1.2.28 |
| 缓存 | Redis | — |
| 安全 | Spring Security + JWT | — |
| 前端 | Vue 2 + Element UI | 2.x |
| 构建 | Maven + Vue CLI | — |

---

## 快速开始

### 环境要求

- JDK 17+
- MySQL 8.0+
- Redis（可选，缓存用）
- Node.js 16+（前端开发）
- Maven 3.6+

### 1. 数据库初始化

```bash
# 创建数据库
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS \`ry-vue\` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;"

# 导入初始化脚本
mysql -u root -p ry-vue < sql/ry_20260417.sql
```

### 2. 后端启动

```bash
# 编译打包
mvn clean package -DskipTests

# 启动
java -jar ruoyi-admin/target/ruoyi-admin.jar

# 或 IDE 中直接运行 RuoYiApplication.java
```

默认端口：`8080`  
默认账号：`admin` / `admin123`

### 3. 前端启动

```bash
cd ruoyi-ui

# 安装依赖
npm install

# 启动开发服务器
npm run dev
```

前端开发服务器默认运行在 `http://localhost:80`

---

## 数据库表结构

```
grade (年级表)
  ├── id           BIGINT PK
  ├── grade_name   VARCHAR(50)  年级名称
  ├── grade_code   VARCHAR(20)  年级编码 (UNIQUE)
  ├── create_time  DATETIME
  ├── update_time  DATETIME
  └── deleted      TINYINT      逻辑删除

class (班级表)
  ├── id           BIGINT PK
  ├── grade_id     BIGINT FK → grade.id
  ├── class_name   VARCHAR(50)  班级名称
  ├── class_code   VARCHAR(20)  班级编码 (UNIQUE)
  ├── teacher      VARCHAR(50)  班主任
  ├── create_time  DATETIME
  ├── update_time  DATETIME
  └── deleted      TINYINT      逻辑删除

student (学生表)
  ├── id           BIGINT PK
  ├── student_no   VARCHAR(30)  学号 (UNIQUE)
  ├── student_name VARCHAR(50)  学生姓名
  ├── gender       TINYINT      性别 (1男 2女)
  ├── age          INT          年龄
  ├── class_id     BIGINT FK → class.id
  ├── create_time  DATETIME
  ├── update_time  DATETIME
  └── deleted      TINYINT      逻辑删除
```

---

## 项目结构

```
xsglxt/
├── ruoyi-admin/       # 控制层 (Controller)
│   └── src/main/java/com/ruoyi/web/controller/
│       ├── business/  # 🆕 业务控制器 (Grade/ClassInfo/Student/Dashboard)
│       ├── system/    # 系统管理控制器
│       ├── monitor/   # 监控控制器
│       └── common/    # 公共控制器
├── ruoyi-common/      # 公共模块 (Entity, Utils, Annotation)
│   └── src/main/java/com/ruoyi/common/core/domain/entity/
│       ├── Grade.java       # 🆕 年级实体
│       ├── ClassInfo.java   # 🆕 班级实体
│       └── Student.java     # 🆕 学生实体
├── ruoyi-framework/   # 框架配置 (Security, MyBatis Plus)
├── ruoyi-system/      # 业务层 (Service + Mapper)
│   └── src/main/java/com/ruoyi/system/
│       ├── mapper/    # 🆕 GradeMapper / ClassInfoMapper / StudentMapper
│       └── service/   # 🆕 ISysXxxService + impl
├── ruoyi-ui/          # 前端 Vue 项目
│   └── src/
│       ├── views/
│       │   ├── business/   # 🆕 业务页面 (grade / class / student)
│       │   ├── system/     # 系统管理页面
│       │   ├── monitor/    # 监控页面
│       │   └── index.vue   # ✏️ 首页（统计概览）
│       └── api/
│           └── business/   # 🆕 业务 API (grade / classInfo / student)
├── sql/               # 数据库脚本
└── pom.xml
```

> 🆕 = AI 新增 &nbsp;|&nbsp; ✏️ = AI 修改 &nbsp;|&nbsp; 其余 = 保留自若依框架

---

## 开发说明

### AI 驱动开发流程

```
1. 开发者用自然语言描述需求
   ↓
2. Claude Code 读取项目代码理解结构
   ↓
3. AI 生成后端 Entity → Mapper → Service → Controller
   ↓
4. AI 生成前端 API 层 → Vue 组件页面
   ↓
5. AI 直接操作数据库更新菜单配置
   ↓
6. 人工审核验收 → git commit
```

### 本次改动统计

| 类型 | 数量 |
|---|---|
| 新增后端 Java 文件 | 13 个 |
| 新增前端 JS/Vue 文件 | 6 个 |
| 修改文件 | 1 个（首页） |
| 数据库菜单新增 | 16 条 |
| 总代码量 | 约 1200 行 |

全部由 AI 生成，开发者零手写。

---

## 许可证

基于 [若依 (RuoYi)](https://gitee.com/y_project/RuoYi) 框架，本项目代码采用 MIT License 开源。

---

<div align="center">

**🤖 本项目 100% 由 AI 写就，包括你正在读的这句话。**

*Powered by Claude Opus 4.8 + DeepSeek-V4-Pro · Built with Claude Code*

</div>
