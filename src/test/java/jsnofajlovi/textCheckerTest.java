package jsnofajlovi;

import jdk.internal.access.JavaNetHttpCookieAccess;

import java.net.HttpCookie;
import java.util.List;

class textCheckerTest {
    private String JSON_FILE = "reci.json";

    @org.junit.jupiter.api.Test
    void parse() {
        JavaNetHttpCookieAccess Json = null;
        List<HttpCookie> node= Json.parse(JSON_FILE);
    }
}