# 🚀 Vibedent 学生管理系统 - 快速启动指南

## ✅ 项目完善概览

这个项目已经包含了一个完整的前后端分离的学生管理系统：

### 📊 前端功能（Vue 3 + Element Plus）
- ✅ **仪表盘** - 实时数据统计和可视化图表
- ✅ **学生管理** - 完整的增删改查、分页、搜索功能
- ✅ **班级管理** - 班级信息管理、关联年级
- ✅ **年级管理** - 年级信息管理
- ✅ **响应式布局** - 适配各种屏幕大小
- ✅ **数据可视化** - ECharts 集成，展示数据图表

### 🔧 后端功能（Spring Boot + MyBatis-Plus）
- ✅ **RESTful API** - 标准的 REST 接口设计
- ✅ **分页查询** - MyBatis-Plus 分页支持
- ✅ **条件搜索** - 支持多条件筛选
- ✅ **CRUD 操作** - 完整的增删改查
- ✅ **CORS 跨域** - 配置完成，前后端可分离
- ✅ **全局异常处理** - 统一的异常处理机制
- ✅ **数据库脚本** - 自动化初始化脚本

---

## 📋 环境要求

| 工具 | 版本 | 备注 |
|------|------|------|
| JDK | 17+ | 后端运行环境 |
| Maven | 3.8+ | Java 依赖管理 |
| MySQL | 8.0+ | 数据库 |
| Node.js | 16+ | 前端运行环境 |
| npm | 8+ | 前端依赖管理 |

---

## 🎯 5分钟快速开始

### Step 1️⃣: 初始化数据库

```bash
# 进入项目根目录
cd D:\MyPrograms\JavaProject\Vibedent

# 连接MySQL并执行初始化脚本
mysql -u root -p < src/main/resources/schema.sql
```

**或者手动执行SQL语句**（可视化工具中复制 `src/main/resources/schema.sql` 的内容执行）

### Step 2️⃣: 配置数据库连接

编辑 `src/main/resources/application.yaml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/xsglxt?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root                    # 修改为你的MySQL用户名
    password: 112358                  # 修改为你的MySQL密码
```

### Step 3️⃣: 启动后端服务

```bash
# 在项目根目录执行
mvn spring-boot:run
```

✅ 后端启动成功后，终端会显示：
```
Started XsglxtApplication in X.XXX seconds
```

📌 **后端服务地址**: `http://localhost:8081`

### Step 4️⃣: 启动前端服务

新开一个终端窗口：

```bash
# 进入前端目录
cd frontend

# 启动前端开发服务器
npm run dev
```

✅ 前端启动成功后会显示：
```
VITE v8.0.12 ready in XXX ms

➜  Local:   http://localhost:5173/
```

### Step 5️⃣: 打开浏览器

访问 `http://localhost:5173`

🎉 **完成！** 你现在可以开始使用学生管理系统了

---

## 📖 使用说明

### 🏠 仪表盘页面

访问主页后首先看到仪表盘，显示：
- 学生总数、班级总数、年级总数统计卡片
- 各年级学生分布柱状图
- 班级学生数饼图
- 最近添加的学生列表

### 👥 学生管理页面

**功能：**
1. **搜索** - 按学生姓名和班级筛选
2. **分页** - 支持自定义每页数量 (5/10/20/50条)
3. **新增** - 点击"新增学生"按钮填写表单
4. **编辑** - 点击表格中的"编辑"按钮修改
5. **删除** - 点击"删除"按钮，确认后删除

**表单字段：**
- 学号 (必填)
- 姓名 (必填)
- 性别 (必填)：男/女
- 年龄 (必填)
- 班级 (必填)：下拉选择

### 📚 班级管理页面

**类似学生管理，额外功能：**
- 按年级筛选班级

**表单字段：**
- 班级编码 (必填)
- 班级名称 (必填)
- 年级 (必填)：下拉选择
- 班主任 (必填)

### 📖 年级管理页面

**功能：**
- 搜索、分页、新增、编辑、删除

**表单字段：**
- 年级编码 (必填)：如 G001
- 年级名称 (必填)：如 高一年级

---

## 🔗 API 接口说明

所有 API 返回统一格式：

```json
{
    "data": {...},
    "msg": "success"
}
```

### 示例：获取学生列表

```bash
curl "http://localhost:8081/api/students?pageNum=1&pageSize=10&studentName=张三&classId=1"
```

**参数说明：**
- `pageNum`: 页码（默认1）
- `pageSize`: 每页条数（默认10）
- `studentName`: 学生姓名（可选）
- `classId`: 班级ID（可选）

**响应示例：**
```json
{
    "data": {
        "records": [
            {
                "id": 1,
                "studentNo": "S001",
                "studentName": "张三",
                "gender": 1,
                "age": 16,
                "classId": 1,
                "createTime": "2026-06-04T10:00:00",
                "updateTime": "2026-06-04T10:00:00"
            }
        ],
        "total": 10,
        "size": 10,
        "current": 1
    },
    "msg": "success"
}
```

---

## 📁 项目结构

```
Vibedent/
├── frontend/                       # Vue 3 前端项目
│   ├── src/
│   │   ├── api/                   # API 请求层
│   │   ├── stores/                # Pinia 状态管理
│   │   ├── router/                # 路由配置
│   │   ├── views/                 # 页面组件
│   │   │   ├── Layout.vue         # 主布局（菜单+侧边栏）
│   │   │   ├── Dashboard.vue      # 仪表盘
│   │   │   ├── student/           # 学生管理
│   │   │   ├── class/             # 班级管理
│   │   │   └── grade/             # 年级管理
│   │   ├── App.vue                # 根组件
│   │   └── main.js                # 入口文件
│   ├── package.json               # 依赖配置
│   └── vite.config.js             # Vite 配置
│
├── src/main/java/                 # Spring Boot 后端代码
│   └── com/akira/xsglxt/
│       ├── config/                # 配置类
│       │   ├── CorsConfig.java    # CORS 跨域配置
│       │   └── MybatisPlusConfig.java
│       ├── controller/            # 控制层
│       │   ├── StudentController
│       │   ├── ClassInfoController
│       │   └── GradeController
│       ├── service/               # 业务层
│       ├── mapper/                # 持久层
│       ├── entity/                # 实体类
│       ├── common/                # 通用类
│       │   └── Result.java        # 统一响应格式
│       └── exception/             # 异常处理
│
├── src/main/resources/
│   ├── application.yaml           # 应用配置
│   └── schema.sql                 # 数据库初始化脚本
│
└── pom.xml                        # Maven 配置
```

---

## 🛠️ 常见问题

### Q1: 如何修改数据库连接？

**A:** 编辑 `src/main/resources/application.yaml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://your-host:3306/xsglxt
    username: your_username
    password: your_password
```

### Q2: 前端访问 API 出现 CORS 错误？

**A:** 确保后端已启动，且 CORS 配置正确。后端在启动时会自动加载 `CorsConfig.java` 配置。

### Q3: 前端依赖安装失败？

**A:** 
```bash
cd frontend
rm -rf node_modules package-lock.json
npm install
```

### Q4: 如何修改前端访问的后端地址？

**A:** 编辑 `frontend/src/api/request.js`：

```javascript
const request = axios.create({
  baseURL: 'http://your-backend-url:8081',  // 修改这里
  timeout: 10000,
})
```

### Q5: 如何生产打包？

**后端打包：**
```bash
mvn clean package -DskipTests
# 生成 target/xsglxt-0.0.1-SNAPSHOT.jar
```

**前端打包：**
```bash
cd frontend
npm run build
# 生成 dist/ 目录，可部署到 Nginx
```

---

## 💡 下一步扩展

### 可以继续添加的功能：
- [ ] 用户登录认证（JWT）
- [ ] 权限管理系统（RBAC）
- [ ] 学生成绩管理
- [ ] 考勤管理
- [ ] 课程安排
- [ ] 通知公告系统
- [ ] 文件上传下载
- [ ] 数据导入导出

### 推荐使用的工具：
- **MockAPI** - 前端开发调试
- **Postman** - API 测试
- **MySQL Workbench** - 数据库管理
- **VS Code / WebStorm** - 前端开发
- **IDEA** - Java 后端开发

---

## 📚 技术文档链接

- [Vue 3 官方文档](https://vuejs.org/)
- [Element Plus 组件库](https://element-plus.org/)
- [Spring Boot 官方文档](https://spring.io/projects/spring-boot)
- [MyBatis-Plus 文档](https://baomidou.com/)
- [Vite 官方文档](https://vitejs.dev/)

---

## 📞 需要帮助？

遇到问题可以：
1. 查看项目中的注释说明
2. 检查控制台的错误信息
3. 查看 `README_NEW.md` 完整文档

---

**祝你使用愉快！🎉**

_最后更新：2026-06-04_
