#!/usr/bin/env bash
ROOT=".."
ENV="java -classpath $ROOT/target/classes/ "

ROUND="3"
STREAM_LENGTH="100000000"
RANGE_OF_VALS="1000"

echo ">>>>>> single thread, 8 keys <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_Single_Uniform $STREAM_LENGTH 8 $RANGE_OF_VALS
done

echo ">>>>>> single thread, 128 keys <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_Single_Uniform $STREAM_LENGTH 128 $RANGE_OF_VALS
done

echo ">>>>>> single thread, 1024 keys <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_Single_Uniform $STREAM_LENGTH 1024 $RANGE_OF_VALS
done

echo ">>>>>> single thread, 8096 keys <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_Single_Uniform $STREAM_LENGTH 8096 $RANGE_OF_VALS
done

echo ">>>>>> single thread, 32384 keys <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_Single_Uniform $STREAM_LENGTH 32384 $RANGE_OF_VALS
done

echo ">>>>>> single thread, 129536 keys <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_Single_Uniform $STREAM_LENGTH 129536 $RANGE_OF_VALS
done

echo ">>>>>> 4 threads, 8 keys <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_N_Uniform 4 $STREAM_LENGTH 8 $RANGE_OF_VALS
done

echo ">>>>>> 4 threads, 128 keys <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_N_Uniform 4 $STREAM_LENGTH 128 $RANGE_OF_VALS
done

echo ">>>>>> 4 threads, 1024 keys <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_N_Uniform 4 $STREAM_LENGTH 1024 $RANGE_OF_VALS
done

echo ">>>>>> 4 threads, 8096 keys <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_N_Uniform 4 $STREAM_LENGTH 8096 $RANGE_OF_VALS
done

echo ">>>>>> 4 threads, 32384 keys <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_N_Uniform 4 $STREAM_LENGTH 32384 $RANGE_OF_VALS
done

echo ">>>>>> 4 threads, 129536 keys <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV groupbySum.Thread_N_Uniform 4 $STREAM_LENGTH 129536 $RANGE_OF_VALS
done
