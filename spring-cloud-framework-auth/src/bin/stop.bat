@echo off
cd %~dp0
call env.bat
set PID_FILE=app.pid
set /p PID=<%PID_FILE%
TASKKILL /PID %PID% /F /T
del %PID_FILE%
:end
