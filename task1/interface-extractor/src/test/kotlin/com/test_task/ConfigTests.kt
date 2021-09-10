package com.test_task

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class ConfigTests {
    private val path = "src/test/kotlin/com/test_task/testclass.txt"
    private val extractor = JavaClassExtractor(Config())

    @Test
    fun classNameTest1() {
        val config = Config (
            "java",
            "java",
            "TestClass",
            null,
            null,
            listOf("public", "private"),
            "newInterface",
            null
        )

        extractor.setConfig(config)
        val info = extractor.extractData(path)
        assertNotNull(info)
        assertEquals(3, info.methods.size)
    }

    @Test
    fun classNameTest2() {
        val config = Config (
            "java",
            "java",
            "InnerClass",
            null,
            null,
            listOf("public", "private"),
            "newInterface",
            null
        )

        extractor.setConfig(config)
        val info = extractor.extractData(path)
        assertNotNull(info)
        assertEquals(2, info.methods.size)
    }

    @Test
    fun whiteListTest() {
        val config = Config (
            "java",
            "java",
            "TestClass",
            listOf("genericFunc"),
            null,
            listOf("public"),
            "newInterface",
            null
        )
        extractor.setConfig(config)
        val info = extractor.extractData(path)
        assertNotNull(info)
        assertEquals(3, info.methods.size)
    }

    @Test
    fun blackListTest() {
        val config = Config (
            "java",
            "java",
            "TestClass",
            null,
            listOf("get"),
            listOf("public"),
            "newInterface",
            null
        )
        extractor.setConfig(config)
        val info = extractor.extractData(path)
        assertNotNull(info)
        assertEquals(1, info.methods.size)
    }

    @Test
    fun accessTest() {
        val config = Config (
            "java",
            "java",
            "TestClass",
            null,
            null,
            listOf("private"),
            "newInterface",
            null
        )
        extractor.setConfig(config)
        val info = extractor.extractData(path)
        assertNotNull(info)
        assertEquals(1, info.methods.size)
    }
}