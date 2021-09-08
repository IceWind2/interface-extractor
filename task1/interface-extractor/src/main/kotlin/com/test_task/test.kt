package com.test_task

import java.lang.Exception

fun main(args: Array<String>) {
    try {
        InterfaceExtractor.extractInterface("src/test/kotlin/com/test_task/testclass.txt")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}