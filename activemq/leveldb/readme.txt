activemq  version - 5.11.11
zookeeper version - 3.4.6

activemq connection with levelDB not happening @ 61616. 

Following warnings are visible in activemq.log

2015-03-30 15:47:18,225 | INFO  | Promoted to master | org.apache.activemq.leveldb.replicated.MasterElector | main-EventThread
2015-03-30 15:47:18,346 | INFO  | Using the pure java LevelDB implementation. | org.apache.activemq.leveldb.LevelDBClient | ActiveMQ BrokerService[localhost] Task-1
2015-03-30 15:47:18,385 | WARN  | listeners are taking too long to process the events | org.apache.activemq.leveldb.replicated.groups.ChangeListenerSupport | main-EventThread
2015-03-30 15:47:18,385 | WARN  | listeners are taking too long to process the events | org.apache.activemq.leveldb.replicated.groups.ChangeListenerSupport | main-EventThread
