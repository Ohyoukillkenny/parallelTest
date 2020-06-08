#!/usr/bin/env bash

#!/usr/bin/env bash
ROOT=".."
ENV="java -classpath $ROOT/target/classes/ "

ROUND="3"
NUM_OF_SAMPLINGS="10000";

echo ">>>>>> baseline (sequantial) <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI.Thread_Single_Uniform $NUM_OF_SAMPLINGS
done

echo ">>>>>> 1 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI.ComputePI_Parallel 1 $NUM_OF_SAMPLINGS
done

echo ">>>>>> 2 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI.ComputePI_Parallel 2 $NUM_OF_SAMPLINGS
done

echo ">>>>>> 3 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI.ComputePI_Parallel 3 $NUM_OF_SAMPLINGS
done

echo ">>>>>> 4 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI.ComputePI_Parallel 4 $NUM_OF_SAMPLINGS
done

echo ">>>>>> 5 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI.ComputePI_Parallel 5 $NUM_OF_SAMPLINGS
done

echo ">>>>>> 6 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI.ComputePI_Parallel 6 $NUM_OF_SAMPLINGS
done

echo ">>>>>> 7 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI.ComputePI_Parallel 7 $NUM_OF_SAMPLINGS
done

echo ">>>>>> 8 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI.ComputePI_Parallel 8 $NUM_OF_SAMPLINGS
done

echo ">>>>>> 8 threads in the thread pool <<<<<<<"
for ((i = 0 ; i < $ROUND ; i++)); do
  $ENV computePI.ComputePI_Parallel 9 $NUM_OF_SAMPLINGS
done
