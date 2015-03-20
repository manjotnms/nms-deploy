export MYSQL_HOME=/usr/
export PATH=$MYSQL_HOME/bin:$PATH
mysql="$MYSQL_HOME/bin/mysql"

host=`hostname`
echo "============================================" >> /home/vagrant/debug_slave.log
date >> /home/vagrant/debug_slave.log
echo $host >> /home/vagrant/debug_slave.log

if [ "$host" = "cent1" ];
then
  slave_host=10.203.10.10
  master_host=10.203.10.11
else
  slave_host=10.203.10.11
  master_host=10.203.10.10
fi

echo $slave_host >> /home/vagrant/debug_slave.log
echo $master_host >> /home/vagrant/debug_slave.log

echo $host "I am entering backup" >> /home/vagrant/debug_slave.log

$mysql -e "SET GLOBAL read_only=true;"
echo "Setting Global variable status = " $? >> /home/vagrant/debug_slave.log

mysqlreplicate --master=root:'abc123'@$master_host:3306 --slave=root:'abc123'@$slave_host:3306 --rpl-user=rpl:rpl >>/home/vagrant/debug_slave.log
#mysqlreplicate --master=root@'$master_host':3306 --slave=root@'$slave_host':3306 >> /home/vagrant/debug_slave.log
echo "Replicate status = " $? >> /home/vagrant/debug_slave.log
