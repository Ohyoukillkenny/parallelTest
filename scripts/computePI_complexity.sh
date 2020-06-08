#!/usr/bin/env bash

ROOT=".."
ENV="java -classpath $ROOT/target/classes/ "

ROUND="3"
CHANNEL_SIZE="1024"
LENGTH_OF_STREAM="100000"

# different number of samplings

echo ">>>>>> baseline (sequantial) <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner_Sequential $LENGTH_OF_STREAM 100
done

for ((j = 2 ; j < 17 ; j++)); do
echo ">>>>>> $j threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner $j $CHANNEL_SIZE $LENGTH_OF_STREAM 100
done
done


echo ">>>>>> baseline (sequantial) <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner_Sequential $LENGTH_OF_STREAM 500
done

for ((j = 2 ; j < 17 ; j++)); do
echo ">>>>>> $j threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner $j $CHANNEL_SIZE $LENGTH_OF_STREAM 500
done
done


echo ">>>>>> baseline (sequantial) <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner_Sequential $LENGTH_OF_STREAM 1000
done

for ((j = 2 ; j < 17 ; j++)); do
echo ">>>>>> $j threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner $j $CHANNEL_SIZE $LENGTH_OF_STREAM 1000
done
done


echo ">>>>>> baseline (sequantial) <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner_Sequential $LENGTH_OF_STREAM 5000
done

for ((j = 2 ; j < 17 ; j++)); do
echo ">>>>>> $j threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner $j $CHANNEL_SIZE $LENGTH_OF_STREAM 5000
done
done


echo ">>>>>> baseline (sequantial) <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner_Sequential $LENGTH_OF_STREAM 10000
done

for ((j = 2 ; j < 17 ; j++)); do
echo ">>>>>> $j threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner $j $CHANNEL_SIZE $LENGTH_OF_STREAM 10000
done
done


echo ">>>>>> baseline (sequantial) <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner_Sequential $LENGTH_OF_STREAM 20000
done

for ((j = 2 ; j < 17 ; j++)); do
echo ">>>>>> $j threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner $j $CHANNEL_SIZE $LENGTH_OF_STREAM 20000
done
done

