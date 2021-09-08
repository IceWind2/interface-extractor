package com.test_task

import java.lang.Exception

fun main(args: Array<String>) {
    try {
        InterfaceExtractor.extractInterface("src/test/kotlin/com/test_task/testclass.java")
    } catch (e: Exception) {
        println("Exception: ${e.message}");
    }
}