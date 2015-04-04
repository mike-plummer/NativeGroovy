#!/bin/bash

 # The MIT License (MIT)
 #
 # Copyright (c) 2015 Mike Plummer
 #
 # Permission is hereby granted, free of charge, to any person obtaining a copy
 # of this software and associated documentation files (the "Software"), to deal
 # in the Software without restriction, including without limitation the rights
 # to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 # copies of the Software, and to permit persons to whom the Software is
 # furnished to do so, subject to the following conditions:
 #
 # The above copyright notice and this permission notice shall be included in
 # all copies or substantial portions of the Software.
 #
 # THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 # IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 # FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 # AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 # LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 # OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 # THE SOFTWARE.


# Find the directory this script lives in
SCRIPT_DIR=`dirname "$BASH_SOURCE"`

if [ `uname` != 'Darwin' ]
then
    LIBRARY_EXTENSION="so"
else
    LIBRARY_EXTENSION="dylib"
fi

# Compile our simple C function
#  -c      Compile but do not link
#  -Wall   Turn on additional warnings
#  -Werror Make all warnings into errors
#  -fpic   Generate "position independent code" - required for shared libraries
gcc -c -Wall -Werror -fpic "$SCRIPT_DIR"/src/main/c/nativegroovy.c -o "$SCRIPT_DIR"/src/main/c/nativegroovy.o

# Generate the shared library using the output from the first compile
gcc -shared -o "$SCRIPT_DIR"/libnativegroovy."$LIBRARY_EXTENSION" "$SCRIPT_DIR"/src/main/c/nativegroovy.o

# Cleanup the intermediate .o artifact
rm "$SCRIPT_DIR"/src/main/c/nativegroovy.o
