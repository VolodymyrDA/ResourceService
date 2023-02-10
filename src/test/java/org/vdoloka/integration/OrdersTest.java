package org.vdoloka.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.vdoloka.dto.OrderDto;
import org.vdoloka.dto.OrderInfoDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
//@TestPropertySource(locations = "classpath:application-test.properties")
public class OrdersTest {
    @LocalServerPort
    int port;
    private static final String ORDERS_URL = "/orders/";
    private static final String PAGE_PARAM = "page";
    private static final String ITEM_PER_PAGE_PARAM = "itemPerPage";

    @Autowired
    private TestRestTemplate restTemplate;

    private List<OrderInfoDto> orderInfoDtos;
    private OrderDto orderDto;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;

        orderInfoDtos = new ArrayList<>();
        orderInfoDtos.add(OrderInfoDto.builder().id(1).build());
        orderInfoDtos.add(OrderInfoDto.builder().id(2).build());

        orderDto = OrderDto.builder().resourceId(1).build();
    }

    @Test
    public void testGetPageData() {
        given()
                .auth().
                preemptive().basic("1", "1")
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
    public void testAddOrder() {
        given()
                .auth().
                basic("1", "1")
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

