package com.test_task

import org.junit.Test
import java.io.File
import kotlin.test.assertNotNull

internal class GenerationTests {
    val dirpath = File("").absolutePath

    private val config = Config (
        "java",
        "java",
        "TestClass",
        null,
        listOf("g"),
        listOf("public"),
        "newInterface",
        "${dirpath}\\intfc.java"
    )

    private val path = "src/test/kotlin/com/test_task/testclass.txt"
    private val extractor: ClassExtractor
    private val generator: InterfaceGenerator


    init {
        extractor = JavaClassExtractor(config)
        generator = JavaInterfaceGenerator(config)
    }

    @Test
    fun correctSyntaxTest() {
        val info = extractor.extractData(path)
        generator.generateInterface(info)
        extractor.setConfig(Config())
        val interfaceFile = File("${dirpath}/intfc.java")
        val testInfo = extractor.extractData(interfaceFile.path)
        assertNotNull(testInfo)
        interfaceFile.delete()
    }
}