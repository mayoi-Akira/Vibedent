# Vibedent 项目环境配置指南

## 📋 应用配置文件

文件位置：`src/main/resources/application.yaml`

```yaml
# 应用名称
spring:
  application:
    name: xsglxt

  # 数据库配置
  datasource:
    # MySQL 连接 URL
    url: jdbc:mysql://localhost:3306/xsglxt?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&createDatabaseIfNotExist=true
    
    # MySQL 用户名
    username: root
    
    # MySQL 密码
    password: 112358
    
    # MySQL 驱动
    driver-class-name: com.mysql.cj.jdbc.Driver

# 服务器端口
server:
  port: 8081

# MyBatis-Plus 配置
mybatis-plus:
  # 实体类所在的包
  type-aliases-package: com.akira.xsglxt.entity
  
  # Mapper XML 文件位置
  mapperLocations: classpath*:mapper/**/*Mapper.xml
  
  # 配置
  configuration:
    # 自动将下划线转换为驼峰命名
    map-underscore-to-camel-case: true
    
    # 打印 SQL 语句（开发时有用，生产环境建议关闭）
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

## 🔧 开发环境配置

### Windows 环境变量

```batch
# 设置 JAVA_HOME
set JAVA_HOME=C:\Program Files\Java\jdk-17

# 设置 MAVEN_HOME
set MAVEN_HOME=C:\Program Files\apache-maven-3.8.1

# 验证安装
java -version
mvn -version
node -v
npm -v
```

### Linux/Mac 环境变量

```bash
# 编辑 ~/.bashrc 或 ~/.zshrc

# Java
export JAVA_HOME=/usr/libexec/java_home -v 17
export PATH=$JAVA_HOME/bin:$PATH

# Maven
export MAVEN_HOME=/usr/local/maven
export PATH=$MAVEN_HOME/bin:$PATH

# 验证
source ~/.bashrc
java -version
mvn -version
```

## 🗄️ 数据库配置

### MySQL 连接参数详解

```
jdbc:mysql://host:port/database?params
```

**常用参数：**
- `useUnicode=true` - 使用 UTF-8 编码
- `characterEncoding=utf-8` - 字符编码为 UTF-8
- `serverTimezone=Asia/Shanghai` - 时区设置
- `useSSL=false` - 禁用 SSL（开发环境）
- `allowPublicKeyRetrieval=true` - 允许公钥检索（MySQL 8.0+）
- `createDatabaseIfNotExist=true` - 数据库不存在时自动创建

### 快速初始化数据库

```bash
# 1. 通过 MySQL 命令行
mysql -u root -p < src/main/resources/schema.sql

# 2. 通过 MySQL GUI 工具
# 在 MySQL Workbench 或 Navicat 中打开 src/main/resources/schema.sql 执行

# 3. 查看创建的表
USE xsglxt;
SHOW TABLES;
```

## 🔐 生产环境配置

### 新建文件：`src/main/resources/application-prod.yaml`

```yaml
spring:
  application:
    name: xsglxt
  
  datasource:
    # 生产数据库连接
    url: jdbc:mysql://your-prod-db-host:3306/xsglxt?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=true
    username: prod_user
    password: ${DB_PASSWORD}  # 从环境变量读取
    driver-class-name: com.mysql.cj.jdbc.Driver

server:
  port: 8081

mybatis-plus:
  type-aliases-package: com.akira.xsglxt.entity
  mapperLocations: classpath*:mapper/**/*Mapper.xml
  configuration:
    map-underscore-to-camel-case: true
    # 生产环境不要打印 SQL
    log-impl: org.apache.ibatis.logging.nologging.NoLoggingImpl
```

### 启动生产环境

```bash
# 方式 1: 使用 application-prod.yaml
java -jar xsglxt-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

# 方式 2: 通过环境变量传递密码
java -jar xsglxt-0.0.1-SNAPSHOT.jar \
  --spring.profiles.active=prod \
  --DB_PASSWORD=your_password
```

## 📝 前端环境配置

### 开发环境配置

文件位置：`frontend/.env.development` (自建)

```env
# API 基地址
VITE_API_BASE_URL=http://localhost:8081

# 应用名称
VITE_APP_NAME=Vibedent-Dev
```

### 生产环境配置

文件位置：`frontend/.env.production` (自建)

```env
# API 基地址
VITE_API_BASE_URL=https://api.yourdomain.com

# 应用名称
VITE_APP_NAME=Vibedent
```

### 在 Vue 中使用环境变量

```javascript
// frontend/src/api/request.js
const baseURL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8081'

const request = axios.create({
  baseURL: baseURL,
  timeout: 10000,
})
```

## 🔄 跨域配置说明

### 后端 CORS 配置

文件位置：`src/main/java/com/akira/xsglxt/config/CorsConfig.java`

```java
registry.addMapping("/**")
  .allowedOrigins("http://localhost:5173")  // 允许的源
  .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允许的方法
  .allowedHeaders("*")  // 允许的请求头
  .allowCredentials(true)  // 是否发送 Cookie
  .maxAge(3600);  // 预检请求缓存时间
```

### 前端代理配置

文件位置：`frontend/vite.config.js`

```javascript
server: {
  proxy: {
    '/api': {
      target: 'http://localhost:8081',  // 后端地址
      changeOrigin: true  // 修改请求源
    }
  }
}
```

## 🔒 安全建议

### 生产环境安全配置

1. **数据库密码**
   - ✅ 使用环境变量或密钥管理系统
   - ❌ 不要在代码中硬编码密码

2. **HTTPS**
   - ✅ 生产环境必须使用 HTTPS
   - ✅ 获取 SSL 证书

3. **CORS**
   - ✅ 只允许特定的域名
   - ❌ 不要允许 `*`

4. **密钥**
   - ✅ 使用强密钥
   - ✅ 定期轮换密钥

---

**需要帮助？** 查看 `QUICK_START.md` 快速启动指南。
