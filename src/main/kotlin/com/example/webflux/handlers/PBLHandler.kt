package com.example.webflux.handlers

import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class PBLHandler {
    suspend fun search(request: ServerRequest): ServerResponse {
        val expected = """
      {
        "id": 1,
        "priority": 1,
        "title": "test1",
        "body": "A.C. xxx"
      }
        """.trimIndent()
        request.attributes()
        return ok().contentType(APPLICATION_JSON).bodyValueAndAwait(expected)
    }
}
