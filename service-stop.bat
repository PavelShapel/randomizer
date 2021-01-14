set /p SERVICE_NAME=<service-name.txt

docker stop %SERVICE_NAME%
docker rm %SERVICE_NAME%