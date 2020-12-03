package ${package}.domain;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroupResourceTest {
    @LocalServerPort
    int randomServerPort;

    @Test
    void getAllGroups() {
        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:"+randomServerPort)
                .build()
                .get()
                .uri("/groups")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().json("[{\"id\":1,\"name\":\"apparel\"},{\"id\":2,\"name\":\"house\"},{\"id\":3,\"name\":\"vehicle\"}]");
    }

    @Ignore
    @Test
    void getAllGroupsWithWebClient() throws InterruptedException {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:" + randomServerPort+"/groups")
                .build();

        Flux<CGroup> groupFlux = client.get().retrieve().bodyToFlux(CGroup.class);

        CGroup groupResource = groupFlux.log().blockLast();

        assertEquals(3, groupResource.getId());
    }

}