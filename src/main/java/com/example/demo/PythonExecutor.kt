package com.example.demo

import java.io.BufferedReader
import java.time.Duration
import java.util.concurrent.TimeUnit


class PythonExecutor(
    private val pythonPath: String = "python3",
    private val timeout: Duration = Duration.ofSeconds(5)
) {

    data class Result(
        val stdout: String,
        val stderr: String,
        val exitCode: Int,
        val timedOut: Boolean
    )

    fun execute(code: String): Result {

        val process = ProcessBuilder(pythonPath, "-c", code)
            .redirectErrorStream(false)
            .start()

        val finished = process.waitFor(timeout.toMillis(), TimeUnit.MILLISECONDS)

        if (!finished) {
            process.destroyForcibly()
            return Result(
                stdout = "",
                stderr = "Process timed out",
                exitCode = -1,
                timedOut = true
            )
        }

        val stdout = process.inputStream.bufferedReader().use(BufferedReader::readText)
        val stderr = process.errorStream.bufferedReader().use(BufferedReader::readText)

        return Result(
            stdout = stdout.trim(),
            stderr = stderr.trim(),
            exitCode = process.exitValue(),
            timedOut = false
        )
    }
}