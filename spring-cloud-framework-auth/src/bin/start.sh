#!/bin/bash
cd `dirname $0`
source ./env.sh
cd ..
APP_DIR=`pwd`
APP_JAR_NAME=${APP_NAME}-${APP_VERSION}.jar

JAVA_OPT="-server -Xms256m -Xmx256m"
JAVA_OPT="${JAVA_OPT} -XX:-OmitStackTraceInFastThrow"

echo "######################### APPLICATION STARTING #########################"
nohup java -server ${JAVA_OPT} -jar ${APP_DIR}/boot/${APP_JAR_NAME} --spring.config.location=${APP_DIR}/conf/ > /dev/null 2>&1 &
echo "######################### APPLICATION RUNNING #########################"