#!/usr/bin/env bash

ROOT=".."
ENV="java -classpath $ROOT/target/classes/ "

ROUND="3"
LENGTH_OF_STREAM="100000"
NUM_OF_SAMPLINGS="1000"

echo ">>>>>> baseline (sequantial) <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner_Sequential $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done

# different size of channel

for ((j = 2 ; j < 17 ; j++)); do
echo ">>>>>> $j threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner $j 4 $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done
done

for ((j = 2 ; j < 17 ; j++)); do
echo ">>>>>> $j threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner $j 8 $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done
done

for ((j = 2 ; j < 17 ; j++)); do
echo ">>>>>> $j threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner $j 16 $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done
done

for ((j = 2 ; j < 17 ; j++)); do
echo ">>>>>> $j threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner $j 64 $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done
done

for ((j = 2 ; j < 17 ; j++)); do
echo ">>>>>> $j threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner $j 256 $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done
done

for ((j = 2 ; j < 17 ; j++)); do
echo ">>>>>> $j threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner $j 1024 $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done
done

for ((j = 2 ; j < 17 ; j++)); do
echo ">>>>>> $j threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner $j 5096 $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done
done
