#!/bin/bash

mkdir -p $OUTPUT_DIR
echo "Building shared library. Binary placed in: $OUTPUT_DIR/$OUTPUT_LIBNAME"

gcc -shared -lrt -O2 -Wall \
    -fno-omit-frame-pointer  -Wno-int-conversion -Wno-int-to-pointer-cast \
    -I$JAVA_HOME/include -I$JAVA_HOME/include/linux ./src/main/c/*.c -o \
    $OUTPUT_DIR/$OUTPUT_LIBNAME