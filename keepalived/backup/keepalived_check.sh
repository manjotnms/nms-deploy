#!/bin/bash
# monitor mysql status
# if this node mysql is dead and its slave delay less than 120 seconds, then stop its keepalived. The other node will bind the IP.

mysqladmin -uroot --bind-address=10.203.10.11 ping 
#mysqladmin -uroot -pabc123 ping
if [ $? -ne 0 ]; then
  echo "mysql down"
  #service keepalived stop
  exit 1 #bad
else
  echo "mysql up"
  exit 0 #good
fi
 

  
