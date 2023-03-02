package org.vdoloka.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.vdoloka.dto.OrderDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class OrdersTest {
    @LocalServerPort
    int port;
    private static final String ORDERS_URL = "/orders/";
    private static final String PAGE_PARAM = "page";
    private static final String ITEM_PER_PAGE_PARAM = "itemPerPage";
    private OrderDto orderDto;
    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testGetPageData() {
        given()
                .auth().preemptive().basic("1", "MQ==")
                .log().all()
                .queryParam(PAGE_PARAM, 1)
                .queryParam(ITEM_PER_PAGE_PARAM, 10)
                .when()
                .get(ORDERS_URL)
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(2))
                .body("[0].id", is(1))
                .body("[1].id", is(2));
    }

    @Test
    void testAddOrder() {
        orderDto = OrderDto.builder().resourceId(1).build();
        given()
                .auth().basic("1", "MQ==")
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(orderDto)
                .when()
                .post(ORDERS_URL)
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value());
    }
}