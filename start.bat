@echo off
REM Vibedent 学生管理系统 - Windows 启动脚本

echo ========================================
echo Vibedent 学生管理系统 - 启动脚本
echo ========================================
echo.

REM 启动后端
echo [1/2] 启动后端服务 (http://localhost:8081)...
start cmd /k "mvn spring-boot:run"

timeout /t 5 /nobreak

REM 启动前端
echo [2/2] 启动前端服务 (http://localhost:5173)...
start cmd /k "cd frontend && npm run dev"

echo.
echo ========================================
echo 启动完成！请在浏览器打开：
echo http://localhost:5173
echo ========================================
