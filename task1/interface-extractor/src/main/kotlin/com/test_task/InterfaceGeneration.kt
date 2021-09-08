package com.test_task

internal interface InterfaceGenerator {
    fun generateInterface(info: InterfaceInfo?)
}

internal class JavaInterfaceGenerator : InterfaceGenerator {

    override fun generateInterface(info: InterfaceInfo?) {
        if (info == null) {
            println("Generation error")
            return
        }

        info.methods.forEach { println(it.toString()) }
    }
}

