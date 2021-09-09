package com.test_task

import com.sksamuel.hoplite.ConfigLoader
import java.lang.Exception

class InterfaceExtractor {
    companion object {
        private var config: Config

        init {
            try {
                config = ConfigLoader().loadConfigOrThrow("/config.yaml");
                println("Using custom config")
            } catch (e: Exception) {
                config = Config()
                println("${e.message}\nUsing default config")
            }
        }

        private val extractor: ClassExtractor = JavaClassExtractor(config)
        private val generator: InterfaceGenerator = JavaInterfaceGenerator(config)

        fun extractInterface(path: String) {
            val info = extractor.extractData(path)
            generator.generateInterface(info)
        }
    }
}