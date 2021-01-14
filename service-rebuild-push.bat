set /p IMAGE_NAME=<service-image-name.txt
set /p SERVICE_NAME=<service-name.txt

call mvn clean install -DskipTests
if errorlevel 1 goto error

rem --tag , -t		Name and optionally a tag in the ‘name:tag’ format
docker build -t %IMAGE_NAME% .
if errorlevel 1 goto error
docker push %IMAGE_NAME%
if errorlevel 1 goto error

goto exit

:error
echo error
echo  
pause

:exit
echo ok
echo  

