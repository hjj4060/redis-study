package com.example.demo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Duration

class PythonExecutorTest {
    private val executor = PythonExecutor(timeout = Duration.ofSeconds(2))

    @Test
    fun `should execute simple python code`() {
        val result = executor.execute("print('hello world')")

        print(result.stdout)
        assertFalse(result.timedOut)
        assertEquals(0, result.exitCode)
        assertEquals("hello world", result.stdout)
        assertTrue(result.stderr.isEmpty())
    }

    @Test
    fun `should capture python error`() {
        val result = executor.execute("raise Exception('boom')")

        assertFalse(result.timedOut)
        assertNotEquals(0, result.exitCode)
        assertTrue(result.stderr.contains("boom"))
    }

    @Test
    fun `should timeout long running code`() {
        val result = executor.execute(
            """
            import time
            time.sleep(10)
            """.trimIndent()
        )

        assertTrue(result.timedOut)
        assertEquals(-1, result.exitCode)
    }
}