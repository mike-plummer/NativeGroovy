# NativeGroovy
Examples of several methods to execute native code from Groovy

Setup
---------------
All code and scripts were developed on an 64-bit Ubuntu 14.04 platform and has been verified to also work on a Mac OSX 10.10.2 system. Other OS/Hardware combos may require modifications. Note that the file 'buildLibrary.sh' requires execute permissions - you may have to manually add this using chmod depending on your setup.

Execution
---------------
This project is designed to be easily run using Gradle. The three available tasks are:

1. runManualJNA (gradle -q runManualJNA)

   Compiles and executes an example of binding to a native library using a manually-generated interface.

2. runBoundJNA (gradle -q runBoundJNA)

   Compiles and executes an example of binding to a native library using auto-generated bindings via the JNA framework.

3. runBoundBridJ (gradle -q runBoundBridJ)

   Compiles and executes an example of binding to a native library using auto-generated bindings via the BridJ framework.

Description
---------------
Each of the three examples demonstrate a method of interfacing Groovy (or Java) code with a native library; sometimes native libraries are the only interface available to interact with hardware/APIs/etc. or can supply efficiency gains in the form of optimized compiled code for complex functions. Each example has varying setup and overhead costs as well as differing levels of code-cleanliness. These frameworks are streamlined alternatives to standard Java Native Interface (JNI) calls which have a reputation for being unwieldy and heavy on boilerplate.

For more info take a look at the log post this example was written for:
https://objectpartners.com/2015/04/21/interfacing-groovy-and-java-with-native-libraries/

Licensing
--------------
This code is provided under the terms of the MIT license: basically you're free to do whatever you want with it, but no guarantees are made to its validity, stability, or safety. All works referenced by or utilized by this project are the property of their respective copyright holders and retain licensing that may be more restrictive.

Additional Information
-------------
Documentation for each tool used in these examples can be obtained via the [NativeLibs4Java project](https://github.com/nativelibs4java)
