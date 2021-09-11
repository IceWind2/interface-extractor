package com.test_task

import com.sksamuel.hoplite.ConfigLoader
import java.io.File
import kotlin.Exception

class InterfaceExtractor {
    companion object {
        private var config: Config

        init {
            try {
                val configFile = File("${File("").absolutePath}\\config.yaml")
                config = ConfigLoader().loadConfigOrThrow(configFile);
                println("Using custom config\n")
            } catch (e: Exception) {
                config = Config()
                println("${e.message}\nUsing default config\n")
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