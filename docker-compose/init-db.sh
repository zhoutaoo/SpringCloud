#!/usr/bin/env bash

for file in $(find /sql -name "*.sql" -exec ls {} \;| grep -v postgres | sort | tr ' ' '|' | tr '\n' ' ')
do
    file=$(echo ${file} | tr '|' ' ')
    printf "Applying update ${file}\n"
    mysql -uroot -p$MYSQL_ROOT_PASSWORD -h mysql < ${file}
done
