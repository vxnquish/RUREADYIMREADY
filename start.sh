#!/bin/bash

# 백엔드 실행
cd backend
mvn spring-boot:run &
BACK_PID=$!

# 프론트엔드 실행
cd ../frontend
npm run dev

# 프론트 종료 시 백엔드도 종료
kill $BACK_PID

# 실행 방법
# New Terminal -> ./chmod +x start.sh (한 번만 하면 됨 다음부턴 그냥 바로)
# ./start.sh