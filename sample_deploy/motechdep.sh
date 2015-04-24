#~/bin/bash
#########################MOTECH DEPLOYMENT SCRIPT#############################################
echo "################################WELCOME TO NMS DEPLOYEMENT#####################################"


function prompt_error
{
   echo -e "ERROR ................................
            .........................EXITING \n"
   exit 1
}

function remote_file_transfer
{
#First Argument IP address
#Second Argument USERNAME
#Third Argument PASSWORD
   echo -e " Transfering files to $1\n"
   scp ./build.xml $2:$3@$1:/tmp/motech/.
}
echo -e "Configuration type \n
	LOCAL:[1]\n
	REMOTE:[2]"
read config_type
if [ $config_type -eq 1 ]
  then
   echo -e "LOCAL configuration selecteed\n"
else
   if [ $config_type -eq 2 ]
   then
     echo -e "REMOTE configuration source\n"
   else
      prompt_error
   fi
fi
		


echo - "Enter the number of servers to deploy motech"
read total_server
echo -e "Total servers to configure $total_server\n\a\a"
server=0
while [[ $server -lt $total_server ]]
do
	echo -e "ENTER the detail of `expr $server + 1` server\n\a\a"
        echo -e "IP Address of Server \n\a"
        read va_ip
	ip[$server]="$va_ip"
        echo -e "USER NAME  of $va_ip"
	read va_user
	username[$server]="$va_user"
        echo -e " Password of $va_ip"
	read va_pass
	password[$server]="$va_pass"
        #Fetch the ANT HOME of REMOTE Server 
        $ANT_HOME/bin/ant ssh.ant.home -Dusername=${username[$server]}  -Dpassword=${password[$server]} -Dip=${ip[$server]} 
        remote_ant_home[$server]=$(head -1 /tmp/remote_output)
        echo -e "REMOTE ANT HOME is $remote_ant_home[$server]}\n"        
	server=`expr $server + 1`
done
server=0
while [[ $server -lt $total_server ]]
do
	echo -e "##########STARTED  ACTION ON :  ${ip[$server]} \n"
        $ANT_HOME/bin/ant ssh.command.execute -Dusername=${username[$server]}  -Dpassword=${password[$server]} -Dip=${ip[$server]} -Dcommand="mkdir -p /tmp/motech"
        
	echo -e "########## TRANSFERRING FILES ${ip[$server]} ####################\n"
         $ANT_HOME/bin/ant remote.file.transfer -Dusername=${username[$server]}  -Dpassword=${password[$server]} -Dip=${ip[$server]}  -Dsrcdir=/home/grameen/ant_test/deploy -Dtarget=/tmp/motech/.
        echo -e "###############STOPING TOMCAT ###########\n"
        $ANT_HOME/bin/ant ssh.command.execute -Dusername=${username[$server]}  -Dpassword=${password[$server]} -Dip=${ip[$server]} -Dcommand="${remote_ant_home[$server]}/bin/ant -buildfile /tmp/motech nms.stop"

	server=`expr $server + 1`
done


server=0
while [[ $server -lt $total_server ]]
do
        echo -e "########## STARTED DEPLOYMENT ON : ${ip[$server]} \n"
        $ANT_HOME/bin/ant ssh.command.execute -Dusername=${username[$server]}  -Dpassword=${password[$server]} -Dip=${ip[$server]} -Dcommand="$ANT_HOME/bin ant /tmp/motech nms.deploy.motech"

        server=`expr $server + 1`
done

