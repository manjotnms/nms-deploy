#~/bin/bash
#########################MOTECH DEPLOYMENT SCRIPT#############################################
echo "################################WELCOME TO NMS DEPLOYEMENT#####################################"

declare -a ip
declare -a username
declare -a password
declare -a remote_ant_home
declare -a total_server
declare -a module_version
declare -a module_build
declare -a module_name

function motech_stop
{
echo -e "###############STOPING TOMCAT ###########\n"
server=0
while [[ $server -lt $total_server ]]
do
        $ANT_HOME/bin/ant ssh.command.execute -Dusername=${username[$server]}  -Dpassword=${password[$server]} -Dip=${ip[$server]} -Dcommand="${remote_ant_home[$server]}/bin/ant -buildfile /tmp/motech nms.stop"
	server=`expr $server + 1`
done

}

function module_to_install
{
echo -e "###############DETAILS OF MODULES TO INSTALL \n"
echo -e "ENTER TOTAL MODULES TO INSTALL \n"
read module_count
module=0
while [[ $module -lt $module_count ]]
do
    echo -e "ENTER THE MODULE NAME \n"
    read va_module_name
    module_name[$module]="$va_module_name"
    echo -e "ENTER the MODULE BUILD ID \n"
    read va_module_build
    module_build[$module]="$va_module_build"

    echo -e "ENTER the MODULE VERSION \n"
    read va_module_version
    module_version[$module]="$va_module_version"

    module=`expr $module + 1`
done
}

function module_dep
{
echo -e "################STARTING MODULE DEPLOYMENT######################### \n"

server=0
while [[ $server -lt $total_server ]]
do
        module=0
        while [[ $module -lt $module_count ]]
        do
		
        done

done
}
function motech_dep
{
server=0
while [[ $server -lt $total_server ]]
do
        echo -e "##########STARTED  ACTION ON :  ${ip[$server]} \n"
        $ANT_HOME/bin/ant ssh.command.execute -Dusername=${username[$server]}  -Dpassword=${password[$server]} -Dip=${ip[$server]} -Dcommand="mkdir -p /tmp/motech"

        echo -e "########## TRANSFERRING FILES ${ip[$server]} ####################\n"
         $ANT_HOME/bin/ant remote.file.transfer -Dusername=${username[$server]}  -Dpassword=${password[$server]} -Dip=${ip[$server]}  -Dsrcdir=/home/grameen/ant_test/deploy -Dtarget=/tmp/motech/.
        server=`expr $server + 1`
done

}
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

echo - e "Enter the number of servers to deploy motech"
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
		

echo -e "Deployment type \n
         MOTECH Only [1] \n
         MOTECH + NMS MODULES [2]\n
         ONLY NMS MODULES [3]"
read dep_type
case $dep_type in
	1) echo -e "#####################STARTING MOTECH ONLY DEP###################### \n" 
           motech_dep                                                                            ;;
        2) echo -e "#####################STARTING MOTECH + NMS MODULES DEP#################### \n" ;;
        3) echo -e "#####################STARTING ONLY NMS MODULES DEP######################## \n" ;;
        *) echo -e "##############################WRONG OPTION TRY AGAIN###################### \n"
esac


server=0
while [[ $server -lt $total_server ]]
do
        echo -e "########## STARTED DEPLOYMENT ON : ${ip[$server]} \n"
        $ANT_HOME/bin/ant ssh.command.execute -Dusername=${username[$server]}  -Dpassword=${password[$server]} -Dip=${ip[$server]} -Dcommand="$ANT_HOME/bin ant /tmp/motech nms.deploy.motech"

        server=`expr $server + 1`
done

