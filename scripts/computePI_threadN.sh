#!/usr/bin/env bash

ROOT=".."
ENV="java -classpath $ROOT/target/classes/ "

ROUND = "1"
CHANNEL_SIZE = "1024";
LENGTH_OF_STREAM = "10000";
NUM_OF_SAMPLINGS = "10000";

echo ">>>>>> baseline (sequantial) <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner_Sequential $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done

echo ">>>>>> 1 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner 1 $CHANNEL_SIZE $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done

echo ">>>>>> 2 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner 2 $CHANNEL_SIZE $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done

echo ">>>>>> 3 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner 3 $CHANNEL_SIZE $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done

echo ">>>>>> 4 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner 4 $CHANNEL_SIZE $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done

echo ">>>>>> 5 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner 5 $CHANNEL_SIZE $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done

echo ">>>>>> 6 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner 6 $CHANNEL_SIZE $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done

echo ">>>>>> 7 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner 7 $CHANNEL_SIZE $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done

echo ">>>>>> 8 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI_Stream2.Runner 8 $CHANNEL_SIZE $LENGTH_OF_STREAM $NUM_OF_SAMPLINGS
done
