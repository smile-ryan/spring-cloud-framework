#!/bin/bash
cd `dirname $0`
source ./env.sh
PID_FILE=/opt/data/${APP_NAME}/application.pid
PID=`cat $PID_FILE`
kill -15 $PID
