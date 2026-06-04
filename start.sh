#!/bin/bash
# Vibedent 学生管理系统 - Linux/Mac 启动脚本

echo "========================================"
echo "Vibedent 学生管理系统 - 启动脚本"
echo "========================================"
echo ""

# 启动后端
echo "[1/2] 启动后端服务 (http://localhost:8081)..."
mvn spring-boot:run &
BACKEND_PID=$!

sleep 5

# 启动前端
echo "[2/2] 启动前端服务 (http://localhost:5173)..."
cd frontend
npm run dev &
FRONTEND_PID=$!

echo ""
echo "========================================"
echo "启动完成！请在浏览器打开："
echo "http://localhost:5173"
echo ""
echo "后端 PID: $BACKEND_PID"
echo "前端 PID: $FRONTEND_PID"
echo "========================================"

# 等待进程
wait
