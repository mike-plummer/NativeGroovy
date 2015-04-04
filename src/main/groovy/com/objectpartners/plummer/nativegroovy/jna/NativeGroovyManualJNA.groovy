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

package com.objectpartners.plummer.nativegroovy.jna

import com.sun.jna.Native
import com.sun.jna.Library


/**
 * A manually-defined interface that matches the functions
 * provided by the shared library.
 */
interface NativeExample extends Library {
	void printValue(String value)
};

try {
	/*
	 * Attempt to load the native library and map it to an instance
	 * of the Library interface we defined above. We're cheating a bit
	 * here and providing a fully-qualified filepath to our library since
	 * it isn't OS-provided - normally you would piggyback on the standard
	 * library lookup path using the 'jna.library.path' system property
	 */
	def nativeLibrary = Native.loadLibrary(System.getProperty('nativegroovy.library.path'), NativeExample.class)
	
	/*
	 * Attempt to execute a function from the library. This actually invokes
	 * the native code from the shared library using JNA to route the Java
	 * method call
	 */
	nativeLibrary.printValue('Go go gadget Manual JNA')
} catch( UnsatisfiedLinkError e ) {
	println 'Failed to load library'
	e.printStackTrace()
	return;
}

