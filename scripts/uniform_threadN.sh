#!/usr/bin/env bash
ROOT=".."
ENV="java -classpath $ROOT/target/classes/ "

ROUND="3"
STREAM_LENGTH="100000000"
NUM_OF_KEYS="10000"
RANGE_OF_VALS="1000"

echo ">>>>>> baseline (no child thread) <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_Single_Uniform $STREAM_LENGTH $NUM_OF_KEYS $RANGE_OF_VALS
done

echo ">>>>>> 1 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_N_Uniform 1 $STREAM_LENGTH $NUM_OF_KEYS $RANGE_OF_VALS
done

echo ">>>>>> 2 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_N_Uniform 2 $STREAM_LENGTH $NUM_OF_KEYS $RANGE_OF_VALS
done

echo ">>>>>> 4 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_N_Uniform 4 $STREAM_LENGTH $NUM_OF_KEYS $RANGE_OF_VALS
done

echo ">>>>>> 8 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_N_Uniform 8 $STREAM_LENGTH $NUM_OF_KEYS $RANGE_OF_VALS
done

echo ">>>>>> 16 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_N_Uniform 16 $STREAM_LENGTH $NUM_OF_KEYS $RANGE_OF_VALS
done

echo ">>>>>> 32 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_N_Uniform 32 $STREAM_LENGTH $NUM_OF_KEYS $RANGE_OF_VALS
done
