package com.test_task

import com.github.javaparser.ParseProblemException
import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import java.io.File
import java.io.FileNotFoundException

class InterfaceExtractor {
    companion object {
        private fun importClass(path: String) : CompilationUnit? {
            var CU : CompilationUnit? = null
            try {
                CU = StaticJavaParser.parse(File(path))
            } catch(e: FileNotFoundException) {
                println("Exception: ${e.message}")
            } catch (e: ParseProblemException) {
                println("Exception: ${e.problems.joinToString{it.message + "\n"}}");
            }

            return CU;
        }

        fun extractInterface(path: String) {
            val cu = importClass(path)
        }
    }
}