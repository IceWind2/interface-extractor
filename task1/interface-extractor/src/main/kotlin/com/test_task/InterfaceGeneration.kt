package com.test_task

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Modifier
import java.io.File
import java.nio.file.Paths

internal abstract class InterfaceGenerator(config: Config) {
    protected var _config = config

    fun setConfig(config: Config) {
        _config = config
    }

    abstract fun generateInterface(info: InterfaceInfo?)
}

internal class JavaInterfaceGenerator(config: Config) : InterfaceGenerator(config) {

    private fun generateCode(info: InterfaceInfo) : String {
        val cu = CompilationUnit()
        val name = _config.interfaceName ?: info.name + "Interface"
        val newInterface = cu.addInterface(name).setPublic(true)

        info.methods.forEach {method ->
            val newMethod = newInterface.addMethod(method.sign.name)
            method.modifiers.forEach{
                newMethod.addModifier(Modifier.Keyword.valueOf(it.uppercase()))
            }
            method.params.forEach {
                newMethod.addParameter(it.type, it.name)
            }
            method.typeParams.forEach {
                newMethod.addTypeParameter(it)
            }
            newMethod.setType(method.sign.type)
            newMethod.removeBody()
        }

        return newInterface.toString()
    }

    override fun generateInterface(info: InterfaceInfo?) {
        if (info == null) {
            println("Generation error")
            return
        }

        val name = _config.interfaceName ?: info.name + "Interface"
        val path = _config.exportPath ?: "${Paths.get(info.path).parent}\\$name.java"

        val code = generateCode(info)

        File(path).writeText(code)
        println("Created $path")
    }
}

