package com.test_task

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.NodeList
import com.github.javaparser.ast.stmt.BlockStmt
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
            if (method.accessModifier == "private") {
                newMethod.addModifier(Modifier.Keyword.DEFAULT)
            }
            if (method.isStatic) {
                newMethod.addModifier(Modifier.Keyword.STATIC)
            }

            method.params.forEach {
                newMethod.addParameter(it.type, it.name)
            }
            method.typeParams.forEach {
                newMethod.addTypeParameter(it)
            }
            newMethod.setType(method.sign.type)

            if (method.accessModifier == "private" || method.isStatic) {
                val body = StaticJavaParser.parseBlock(method.body)
                newMethod.setBody(body)
            } else {
                newMethod.removeBody()
            }
        }

        return newInterface.toString()
    }

    override fun generateInterface(info: InterfaceInfo?) {
        if (info == null) {
            println("Generation error\n")
            return
        }

        val name = _config.interfaceName ?: info.name + "Interface"
        val path = _config.exportPath ?: "${Paths.get(info.path).parent ?: "."}\\$name.java"

        val code = generateCode(info)

        File(path).writeText(code)
        println("Created $path\n")
    }
}

