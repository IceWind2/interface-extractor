package com.test_task

import java.nio.file.Paths

internal abstract class InterfaceGenerator(config: Config) {
    protected val _config = config

    abstract fun generateInterface(info: InterfaceInfo?)
}

internal class JavaInterfaceGenerator(config: Config) : InterfaceGenerator(config) {

    override fun generateInterface(info: InterfaceInfo?) {
        if (info == null) {
            println("Generation error")
            return
        }

        val name = _config.interfaceName ?: info.name + "Interface"
        val path = _config.exportPath ?: "${Paths.get(info.path).parent}\\$name.java"

        println("Created $path")
    }
}

