#!/usr/bin/env python
import socket
import subprocess
import sys
from datetime import datetime

remoteServerRange    = str(sys.argv[1])
remoteServer = remoteServerRange
hostlist = remoteServer.split(".XXX")
print("")
t1 = datetime.now()
socket.setdefaulttimeout(0.5)
total_hosts = 0

for i in range(0,256):
    for k in range(0,256):
        remoteServer = "{}.{}.{}".format(hostlist[0],i,k)
        remoteServerIP  = socket.gethostbyname(remoteServer)

        try:
            remote_hostname = socket.gethostbyaddr(remoteServerIP)
        except socket.herror:
            remote_hostname = "N/A"

        try:
            for port in (80,8080):
                sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                result = sock.connect_ex((remoteServerIP, port))
                if result == 0:
                    print "{}\t{}".format(remoteServerIP,remote_hostname[0])
                    total_hosts += 1
                sock.close()

        except KeyboardInterrupt:
            sys.exit()

t2 = datetime.now()
total =  t2 - t1
print("")
print("Total number of web servers: {}".format(total_hosts))
print('Scan duration: {}'.format(total))
