#include <iostream>
#include <chrono>
#include <thread>

#define MAX_NUM_THREADS	32

void sum(long times, double * result){
    double sum = 0.0;
    double round = 10000000;
    for (long i = 0; i < times; i++) {
        for (double j = 0; j < round; j = j + 1) {
            sum += j;
        }
    }
    *result = sum;
}

int main(int argc, char *argv[]) {
    using namespace std::chrono; // for time testing
    int numOfThreads = atoi(argv[argc - 1]);
    long complexity = atol(argv[argc - 2]);

    high_resolution_clock::time_point start_time = high_resolution_clock::now();

    long complexity_for_thread = complexity / numOfThreads;
    long complexity_for_first_thread = complexity_for_thread + complexity % numOfThreads;

    std::thread threads[numOfThreads];
    double results[MAX_NUM_THREADS] = { 0 };
    int t;

    // create the threads
    for(t = 0; t < numOfThreads; t++) {
        if (t == 0) {
            threads[t] = std::thread(sum, complexity_for_first_thread, &results[t]);
        } else {
            threads[t] = std::thread(sum, complexity_for_thread, &results[t]);
        }
    }

    // join the threads
    for(t=0; t < numOfThreads; t++) {
        threads[t].join();
    }

    // collected the results
    double result = 0.0;
    for(t=0; t<numOfThreads; t++) {
        result += results[t];
    }

    high_resolution_clock::time_point end_time = high_resolution_clock::now();

    duration<double> time_span = duration_cast<duration<double>>(end_time - start_time);
    std::cout << "Results = " << result << " with " << numOfThreads << " threads" << std::endl;
    std::cout << "It takes " << time_span.count() << " seconds.";

    return 0;
}