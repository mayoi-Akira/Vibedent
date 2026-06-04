# Vibedent - 学生管理系统 🎓

> ⚡ 前后端完整分离项目：Vue 3 + Element Plus 前端 + Spring Boot 后端

## 🚀 项目结构

```
Vibedent/
├── frontend/                 # Vue 3 + Element Plus 前端项目
│   ├── src/
│   │   ├── api/             # API 请求封装
│   │   ├── stores/          # Pinia 状态管理
│   │   ├── router/          # 路由配置
│   │   ├── views/           # 页面组件
│   │   │   ├── student/     # 学生管理
│   │   │   ├── class/       # 班级管理
│   │   │   ├── grade/       # 年级管理
│   │   │   └── Dashboard.vue # 仪表盘
│   │   └── main.js
│   └── vite.config.js
└── src/main/java/           # Spring Boot 后端代码
    └── com/akira/xsglxt/
        ├── config/          # 配置类
        ├── controller/      # 控制器
        ├── entity/          # 实体类
        ├── mapper/          # Mapper 层
        └── service/         # 业务逻辑层
```

## 📦 技术栈

### 前端
- Vue 3 (3.5.34+)
- Element Plus (2.14.1+)
- Vue Router (5.1.0+)
- Pinia (3.0.4+)
- Axios (1.17.0+)
- ECharts (6.1.0+)
- Vite (8.0.12+)

### 后端
- Java 17+
- Spring Boot 4.0.6
- MyBatis-Plus 3.5.16
- MySQL 8.0+
- Maven 3.8+

## 🛠️ 快速开始

### 1️⃣ 准备工作

确保已安装：
- JDK 17+
- Maven 3.8+
- MySQL 8.0+
- Node.js 16+ (用于前端)

### 2️⃣ 配置数据库

#### 方式一：使用 SQL 脚本
```bash
mysql -u root -p < src/main/resources/schema.sql
```

#### 方式二：手动配置
修改 `src/main/resources/application.yaml`：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/xsglxt
    username: root
    password: your_password
```

### 3️⃣ 启动后端

```bash
# 在项目根目录
mvn spring-boot:run
```

后端服务将在 `http://localhost:8081` 启动

### 4️⃣ 启动前端

```bash
# 进入前端目录
cd frontend

# 安装依赖（如果还未安装）
npm install

# 启动开发服务器
npm run dev
```

前端应用将在 `http://localhost:5173` 启动

### 5️⃣ 访问应用

打开浏览器访问 `http://localhost:5173`

## 📱 功能说明

### 🏠 仪表盘
- 显示学生、班级、年级的统计数据
- 展示各年级学生分布柱状图
- 展示班级学生数饼图
- 最近添加的学生列表

### 👥 学生管理
- 分页查询学生列表
- 按学生姓名、班级搜索
- 新增学生
- 编辑学生信息
- 删除学生

### 📚 班级管理
- 分页查询班级列表
- 按班级名称、年级搜索
- 新增班级
- 编辑班级信息
- 删除班级

### 📖 年级管理
- 分页查询年级列表
- 按年级名称搜索
- 新增年级
- 编辑年级信息
- 删除年级

## 🔌 API 接口

所有接口统一返回格式：
```json
{
    "data": {},
    "msg": "success"
}
```

### 学生接口
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/students` | 分页查询学生列表 |
| GET | `/api/students/{id}` | 获取学生详情 |
| POST | `/api/students` | 新增学生 |
| PUT | `/api/students` | 修改学生 |
| DELETE | `/api/students/{id}` | 删除学生 |

### 班级接口
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/classes` | 分页查询班级列表 |
| GET | `/api/classes/{id}` | 获取班级详情 |
| POST | `/api/classes` | 新增班级 |
| PUT | `/api/classes` | 修改班级 |
| DELETE | `/api/classes/{id}` | 删除班级 |

### 年级接口
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/grades` | 分页查询年级列表 |
| GET | `/api/grades/{id}` | 获取年级详情 |
| POST | `/api/grades` | 新增年级 |
| PUT | `/api/grades` | 修改年级 |
| DELETE | `/api/grades/{id}` | 删除年级 |

## 📝 前端开发说明

### 目录结构说明

```
frontend/src/
├── api/                  # API 请求模块
│   ├── request.js       # Axios 实例配置
│   ├── student.js       # 学生 API
│   ├── class.js         # 班级 API
│   └── grade.js         # 年级 API
├── stores/              # Pinia 状态管理
│   ├── student.js
│   ├── class.js
│   └── grade.js
├── router/
│   └── index.js        # 路由配置
├── views/
│   ├── Layout.vue      # 主布局
│   ├── Dashboard.vue   # 仪表盘
│   ├── student/
│   │   └── StudentManage.vue
│   ├── class/
│   │   └── ClassManage.vue
│   └── grade/
│       └── GradeManage.vue
└── App.vue
```

### 添加新的管理模块

以添加课程管理为例：

1. **创建 API**：`src/api/course.js`
2. **创建 Store**：`src/stores/course.js`
3. **创建页面**：`src/views/course/CourseManage.vue`
4. **更新路由**：在 `src/router/index.js` 中添加路由
5. **更新菜单**：在 `src/views/Layout.vue` 中添加菜单项

## 🚀 构建生产版本

### 后端构建
```bash
mvn clean package -DskipTests
```

### 前端构建
```bash
cd frontend
npm run build
```

生成的 `dist` 目录可部署到 Web 服务器

## 🔐 注意事项

1. **跨域配置**：后端已配置 CORS，允许来自 `http://localhost:5173` 的请求
2. **代理配置**：前端 Vite 配置了代理，所有 `/api` 请求转发到 `http://localhost:8081`
3. **逻辑删除**：所有表都启用了逻辑删除，使用 `deleted` 字段标记
4. **时间戳**：所有表都有 `create_time` 和 `update_time` 字段，自动管理

## 📚 参考资源

- [Vue 3 文档](https://vuejs.org/)
- [Element Plus 文档](https://element-plus.org/)
- [Spring Boot 文档](https://spring.io/projects/spring-boot)
- [MyBatis-Plus 文档](https://baomidou.com/)

## 📄 许可证

MIT

---

_项目完成于 2026-06-04_
