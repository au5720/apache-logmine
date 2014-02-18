#!/bin/bash
yesterday=`date -d "1 day ago" +"%d/%b/%Y"`

cat /modsec/apache/logs/latest.log | grep "$yesterday" > /tmp/apache-latest.log
cd /home/jennifer/apache-logmine
lein run
cd /home/jennifer
