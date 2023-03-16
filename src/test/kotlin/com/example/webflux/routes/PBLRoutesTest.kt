package com.example.webflux.routes

import io.kotest.core.annotation.AutoScan
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@AutoScan
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PBLRoutesTest : DescribeSpec() {
  override fun extensions() = listOf(SpringExtension)

  @LocalServerPort
  private var port: Int = 0

  init {
    describe("success smoke test") {
        it("one of PBL") {
        val expected = """
          {
            "id": 1,
            "priority": 1,
            "title": "test1",
            "body": "A.C. xxx"
          }
        """.trimIndent()

        WebTestClient
            .bindToServer()
            .baseUrl("http://localhost:$port")
            .build()
            .get()
            .uri { uriBuilder ->
              uriBuilder
                  .path("/todos")
                  .build()
            }
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody<String>().isEqualTo(expected)
      }
    }
  }
}