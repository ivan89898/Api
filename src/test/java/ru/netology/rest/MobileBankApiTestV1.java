package ru.netology.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

class MobileBankApiTestV1 {
    @Test
    void shouldReturnDemoAccounts() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }
    @Test
    void shouldCurrencyCheckDemoAccounts1() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                //.header("Content type", "application/json; charset=UTF-8")
                .contentType(ContentType.JSON)
                .body("",hasSize(3))
                .body("[0].currency",equalTo("RUB"))
                .body("[1].currency",equalTo("USD"))
                .body("[2].currency",equalTo("RUB"))
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }
}
