#!/bin/bash

# 백엔드 실행
cd backend
./gradlew bootRun &
BACK_PID=$!

# 종료 시 백엔드도 같이 죽이기 위한 trap
trap "echo '종료 중...'; kill $BACK_PID" EXIT

# 프론트 실행
cd ../frontend
npm run dev
