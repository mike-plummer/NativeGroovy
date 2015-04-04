/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Mike Plummer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
 
package com.objectpartners.plummer.nativegroovy.jnaerator

import java.nio.ByteBuffer
import java.nio.charset.Charset

import nativegroovy.NativegroovyLibrary

import org.bridj.Pointer
import org.bridj.Pointer.StringType

import com.sun.jna.Native

def framework

/*
 * Do a little reflection on the library we're interfacing with. Since the
 * auto-generated JNA and BridJ bindings both supply a JAR with the same
 * class we can look at that class to determine whether it was generated 
 * with JNA or BridJ bindings. This affects how we need to call the method(s).
 * Normally you wouldn't have to do this since you would just choose one or 
 * the other, but this helps to reduce boilerplate code in this example.
 */
if(NativegroovyLibrary.class.isAnnotationPresent(org.bridj.ann.Library)){
	framework = 'BridJ'
} else {
	framework = 'JNA';
}
//Define a GString that includes the name of the framework we're bound to.
def value = "Go go gadget ${framework}"

try {
	/*
	 * This is the ugly part - native code deals with Pointers and Memory Blocks -
	 * lions, tigers, and bears, oh my! Luckily each framework supplies fairly
	 * friendly structures to build these constructs and use them in Java/Groovy ways.
	 */
	if(NativegroovyLibrary.class.isAnnotationPresent(org.bridj.ann.Library)){
		//Build a pointer to the 'value' GString defined above - the native code
		//is expecting a pointer to a Character array.
		value = Pointer.pointerToString(value, StringType.C, Charset.defaultCharset())
		//Pass the pointer to the Native Library function
		NativegroovyLibrary.printValue(value)
	} else {
		//Build a ByteBuffer - roughly analagous to a Pointer to memory block
		value = ByteBuffer.wrap(Native.toByteArray(value))
		//Tell JNA to build an interface to the Native Library
		def library = Native.loadLibrary(NativegroovyLibrary.class)
		//Pass the ByteBuffer to the Native Library function
		library.printValue(value)
	}
} catch (UnsatisfiedLinkError ule) {
	println "Failed to resolve library"
}