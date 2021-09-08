package com.test_task

internal class Signature(_name: String = "", _type: String = "") {
    val name: String = _name
    val type: String = _type
}

internal class Method(_sign: Signature = Signature(), _params: MutableList<Signature> = mutableListOf()) {
    constructor(name: String, type: String) : this() {
        this.sign = Signature(name, type)
    }

    var modifiers : MutableList<String> = mutableListOf()
    var sign: Signature = _sign
    val params: MutableList<Signature> = _params

    override fun toString(): String {
        val argsString = params.joinToString("") {"${it.type} ${it.name}, "}.dropLast(2)
        val modifiersString = modifiers.joinToString("").dropLast(1)
        return "$modifiersString ${sign.type} ${sign.name} ($argsString);"
    }
}

internal class InterfaceInfo(_name: String = "", _methods: MutableList<Method> = mutableListOf()) {
    val name: String = _name
    val methods: MutableList<Method> = _methods
}