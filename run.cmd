@echo off

REM Java dasturini build qilish
call maven clean install -DskipTests

REM Docker login
REM docker login -u username -p password

REM Docker image'ni yaratish
docker build -t bobur7761/reactive_programming .

REM Docker image'ni Docker Hub'ga push qilish
docker push bobur7761/reactive_programming:latest

REM Docker Compose servisini to'xtatish
docker-compose down

REM Docker image'larni yangilash
docker-compose pull

REM Docker Compose servisini ishga tushirish
docker-compose up