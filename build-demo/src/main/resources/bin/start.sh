#!/bin/sh

# 以下路径根据实际情况修改，可使用相对路径（使用相对路径则注意运行脚本时必须在bin目录下运行，否则会找不到对应的路径），最好使用绝对路径
# jar 包所在路径
server_name="../build-demo-1.0.jar"
# 配置文件路径
server_config_location="../config/"
# 日志目录路径
server_log_location="../logs/"
# 日志所在路径
server_log_name="../logs/logs.log"

# 如果日志目录不存在先创建
if [ ! -d ${server_log_location} ];then
mkdir ${server_log_location}
else
echo ""
fi

# nohup：在后台运行jar包
# -jar ${server_name}：指定启动的jar包
# --spring.config.location：指定配置文件目录或者文件名称，如果是目录，以/结束
# > ${server_log_name}：指定日志输出路径
# 2>&1 ：将正常的运行日志和错误日志合并输入到指定日志
# & 在后台运行
nohup java -jar ${server_name}  --spring.config.location=${server_config_location} > ${server_log_name}  2>&1 &

echo ${server_name} is starting ......

tail -f ${server_log_name}
