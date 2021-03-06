package com.test_task

internal class Signature(val name: String = "", val type: String = "") {}

internal class Method(var sign: Signature = Signature(), val params: MutableList<Signature> = mutableListOf()) {
    constructor(name: String, type: String) : this() {
        this.sign = Signature(name, type)
    }

    var isStatic = false
    var accessModifier: String? = null
    var body: String? = null
    var typeParams : MutableList<String> = mutableListOf()

    override fun toString(): String {
        val argsString = params.joinToString("") {"${it.type} ${it.name}, "}.dropLast(2)
        return "$accessModifier " +
                "${if (isStatic) "static" else ""} " +
                "${sign.type} " +
                "${sign.name} " +
                "($argsString) " +
                "${if (body == null) ";" else body}"
    }
}

internal class InterfaceInfo(val name: String = "", val path: String = "") {
    val methods: MutableList<Method> = mutableListOf()
}

data class Config(
    val sourceLang: String = "java",
    val targetLang: String = "java",
    val className: String? = null,
    val whiteList: List<String>? = null,
    val blackList: List<String>? = null,
    val accessModifiers: List<String> = listOf("public"),
    val interfaceName: String? = null,
    val exportPath: String? = null
)