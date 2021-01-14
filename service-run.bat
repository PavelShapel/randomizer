set /p IMAGE_NAME=<service-image-name.txt
set /p SERVICE_NAME=<service-name.txt
set /p SERVICE_PORT=<service-port.txt

rem --detach ,  -d		Run container in background and print container ID
rem --publish , -p		Publish a container’s port(s) to the host

docker run -d -p %SERVICE_PORT%:%SERVICE_PORT% --name %SERVICE_NAME% %IMAGE_NAME%