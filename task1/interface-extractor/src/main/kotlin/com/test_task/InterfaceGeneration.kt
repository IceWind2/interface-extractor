package com.test_task

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Modifier
import java.io.File
import java.nio.file.Paths

internal abstract class InterfaceGenerator(config: Config) {
    protected val _config = config

    abstract fun generateInterface(info: InterfaceInfo?)
}

internal class JavaInterfaceGenerator(config: Config) : InterfaceGenerator(config) {

    private fun createCode(info: InterfaceInfo, name: String) : String {
        val cu = CompilationUnit()
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

        val code = createCode(info, name)
        File(path).writeText(code);
        println("Created $path")
    }
}

