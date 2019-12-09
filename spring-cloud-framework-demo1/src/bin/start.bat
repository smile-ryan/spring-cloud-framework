@echo off
cd %~dp0
call env.bat
set DEPLOY_DIR=%~dp0%..
set CLASSPATH=%DEPLOY_DIR%
set CLASSPATH=%~dp0..\lib\*;%CLASSPATH%

echo ::::::::::::::::::::::::: APPLICATION START :::::::::::::::::::::::::

echo on
java -cp "%CLASSPATH%" %CONSOLE_MAIN%
:end
