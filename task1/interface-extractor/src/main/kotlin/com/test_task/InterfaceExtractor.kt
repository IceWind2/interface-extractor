package com.test_task

class InterfaceExtractor {
    companion object {
        private val extractor: ClassExtractor = JavaClassExtractor()
        private val generator: InterfaceGenerator = JavaInterfaceGenerator()

        fun extractInterface(path: String) {
            val info = extractor.extractData(path)
            generator.generateInterface(info)
        }
    }
}