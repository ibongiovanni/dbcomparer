#!/bin/bash

export CLASSPATH=""
for file in `ls target/dependency`; do export CLASSPATH=$CLASSPATH:target/dependency/$file; done
export CLASSPATH=$CLASSPATH:target/classes

echo "$#"
echo "$1"
echo "$2"
if [[ $# -ne 2 ]]; then
  echo "ERROR: Wrong number of params"
  echo "Use: program config_file1 config_file2"
else
  echo "*******************  EXECUTING PROGRAM ******************************************"
  java -cp $CLASSPATH App $1 $2
fi
