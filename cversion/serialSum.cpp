#include <iostream>
#include <chrono>

double sum(long times){
    double sum = 0.0;
    double round = 10000000;
    for (long i = 0; i < times; i++) {
        for (double j = 0; j < round; j = j + 1) {
            sum += j;
        }
    }
    return sum;
}

int main(int argc, char *argv[]) {
    using namespace std::chrono; // for time testing

    long complexity = atol(argv[argc - 1]);

    high_resolution_clock::time_point start_time = high_resolution_clock::now();
    double result = sum(complexity);
    high_resolution_clock::time_point end_time = high_resolution_clock::now();

    duration<double> time_span = duration_cast<duration<double>>(end_time - start_time);
    std::cout << "Results = " << result << std::endl;
    std::cout << "It takes " << time_span.count() << " seconds.";

    return 0;
}
