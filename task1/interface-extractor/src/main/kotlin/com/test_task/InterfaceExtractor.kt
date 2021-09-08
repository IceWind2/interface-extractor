package com.test_task

import com.github.javaparser.ParseProblemException
import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.visitor.VoidVisitorAdapter
import java.io.File
import java.io.FileNotFoundException

class InterfaceExtractor {

    private class MethodNameCollector :  VoidVisitorAdapter<InterfaceInfo>() {
        private fun extractMethodData(md: MethodDeclaration) : Method {
            val method = Method(md.nameAsString, md.typeAsString)
            md.modifiers.forEach { method.modifiers.add(it.toString()) }
            md.parameters.forEach { method.params.add(Signature(it.nameAsString, it.typeAsString)) }

            return  method
        }

        override fun visit(md: MethodDeclaration, info: InterfaceInfo) {
            super.visit(md, info);
            info.methods.add(extractMethodData(md))
        }

        override fun visit(cid: ClassOrInterfaceDeclaration, info: InterfaceInfo) {
            if (cid.nameAsString.equals(info.name)) {
                super.visit(cid, info)
            }
        }
    }

    companion object {
        private fun importClass(path: String) : CompilationUnit? {
            var CU : CompilationUnit? = null
            try {
                CU = StaticJavaParser.parse(File(path))
            } catch(e: FileNotFoundException) {
                println("Error: ${e.message}")
            } catch (e: ParseProblemException) {
                println("Error: ${e.problems.joinToString{it.message + "\n"}}")
            }

            return CU;
        }

        private fun extractData(CU: CompilationUnit) : InterfaceInfo {
            val info = InterfaceInfo("TestClass")
            val visitor = MethodNameCollector()

            val targetClass = CU.findFirst(ClassOrInterfaceDeclaration::class.java)
                                            { it.nameAsString.equals(info.name) }.get()

            visitor.visit(targetClass, info)
            return info
        }

        fun extractInterface(path: String) {
            val cu = importClass(path)

            if (cu != null) {
                val info = extractData(cu)
                info.methods.forEach{ println(it.toString())}
            }
        }
    }
}