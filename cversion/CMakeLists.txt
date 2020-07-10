cmake_minimum_required(VERSION 3.16)
project(cversion)

set(CMAKE_CXX_STANDARD 20)

add_executable(serialSum serialSum.cpp)
add_executable(stdSum parallelSum.cpp)