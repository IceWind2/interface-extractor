package com.test_task

import java.lang.Exception

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Specify file")
        return
    }

    try {
        InterfaceExtractor.extractInterface(args[0])
    } catch (e: Exception) {
        println("Error: ${e.message}\n")
    }
}