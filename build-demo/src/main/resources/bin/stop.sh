#!/bin/bash
#变量设置,如果以下变量名不能满足需求，则自行新增一个变量名，在启动脚本中引用，整个脚本格式不变

#service_name以进程中的唯一关键字命名，或者以jar包名命名
server_name="../build-demo-1.0.jar"

#得到进程号
PID_NUM=$(ps -ef |grep $server_name|grep -v grep|awk '{print $2}' |sed ':label;N;s/\n/ /;b label')

#某些服务会启动多个进程,则一次全部停止，同样作用于一个进程的服务
for i in $PID_NUM
do
  kill -9 $PID_NUM
  echo -e "$server_name is\033[1;31m STOP \033[0m"
done