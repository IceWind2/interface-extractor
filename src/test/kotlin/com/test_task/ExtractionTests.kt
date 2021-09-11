package com.test_task

import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class ExtractionTests {
    private val config = Config (
        "java",
        "java",
        "TestClass",
        listOf("genericFunc"),
        listOf("get"),
        listOf("public"),
        "newInterface",
        null
    )

    private val path = "src/test/kotlin/com/test_task/testclass.txt"
    private val extractor: ClassExtractor
    private val info: InterfaceInfo?

    init {
        extractor = JavaClassExtractor(config)
        info = extractor.extractData(path)
    }

    @Test
    fun methodsExtractionTest() {
        val info = extractor.extractData(path)
        assertNotNull(info)
        assertEquals(2, info.methods.size)
        assertEquals("genericFunc", info.methods[0].sign.name)
        assertEquals("something", info.methods[1].sign.name)
    }

    @Test
    fun methodsAccessTest() {
        val info = extractor.extractData(path)
        assertNotNull(info)
        assertEquals("private", info.methods[0].accessModifier)
        assertEquals("public", info.methods[1].accessModifier)
    }

    @Test
    fun methodsTypeTest() {
        val info = extractor.extractData(path)
        assertNotNull(info)
        assertEquals("void", info.methods[0].sign.type)
        assertEquals("List<Loan>", info.methods[1].sign.type)
    }

    @Test
    fun methodsModifiersTest() {
        val info = extractor.extractData(path)
        assertNotNull(info)
        assertContains(info.methods[0].typeParams, "T")
        assertTrue { info.methods[1].isStatic }
    }

    @Test
    fun methodsParamsTest() {
        val info = extractor.extractData(path)
        assertNotNull(info)
        assertEquals(1, info.methods[0].params.size)
        assertEquals("T", info.methods[0].params[0].type)
        assertEquals(2, info.methods[1].params.size)
        assertEquals("List<Loan>", info.methods[1].params[0].type)
    }

    @Test
    fun methodBodyTest() {
        val info = extractor.extractData(path)
        assertNotNull(info)
        assertNotNull(info.methods[1].body)
    }
}