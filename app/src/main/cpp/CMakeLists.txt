# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(kp_lib_DIR ${CMAKE_SOURCE_DIR}/../../../knowledgepack-libs)
set(kp_inc_DIR ${kp_lib_DIR}/include)
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library(
        sensiml
        SHARED
        IMPORTED
)
set_target_properties(sensiml
        PROPERTIES IMPORTED_LOCATION
        ${kp_lib_DIR}/src/${CMAKE_ANDROID_ARCH_ABI}/libsensiml.so)
include_directories(${kp_inc_DIR})

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

add_library( # Sets the name of the library.
        knowledgepack-wrapper

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        knowledgepack-wrapper.cpp)

target_include_directories(knowledgepack-wrapper
        PRIVATE
        ${kp_inc_DIR})

target_link_libraries( # Specifies the target library.
        knowledgepack-wrapper
        android
        sensiml

        # Links the target library to the log library
        # included in the NDK.
        log)