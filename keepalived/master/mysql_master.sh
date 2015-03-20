export MYSQL_HOME=/usr/
export PATH=$MYSQL_HOME/bin:$PATH
mysql="$MYSQL_HOME/bin/mysql"

echo "============================================" >> /home/vagrant/debug_master.log
echo `date` >> /home/vagrant/debug_master.log

host=`hostname`
echo $host >> /home/vagrant/debug_master.log

if [ $host = "cent1" ]
then
  slave_host=10.203.10.11
  new_master=10.203.10.10
else
  slave_host=10.203.10.10
  new_master=10.203.10.11
fi

echo "slave_host = " $slave_host >> /home/vagrant/debug_master.log
echo "new_master = " $new_master >> /home/vagrant/debug_master.log

echo $host "I am entering Master" >> /home/vagrant/debug_master.log

$mysql -e "SET GLOBAL read_only=false;"
echo "Setting Global variable status = " $? >> /home/vagrant/debug_master.log

mysqlrpladmin --slaves=root:'abc123'@$new_master:3306 --candidates=root:'abc123'@$new_master:3306 failover >>/home/vagrant/debug_master.log

echo "Failover status = " $? >> /home/vagrant/debug_master.log


#mysqlrpladmin --master=root@'$prev_master':3306 --slaves=root@$slave_host':3306 --new-master=root@'$slave_host':3306 --demote-master switchover

