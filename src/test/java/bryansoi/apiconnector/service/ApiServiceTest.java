package bryansoi.apiconnector.service;

import bryansoi.apiconnector.config.Connector;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class ApiServiceTest {

    @Value("${naver.client.key}")
    private String naverClientKey;
    @Value("${naver.secret.key}")
    private String naverSecretKey;
    private final RestTemplate restTemplate = new RestTemplate();
    private final Connector connector = new Connector(restTemplate);

    @Test
    void checkKeys() {
        System.out.println(naverClientKey);
        System.out.println(naverSecretKey);
        Assertions.assertThat(naverClientKey).isNull();
        Assertions.assertThat(naverSecretKey).isNull();
        // 런타임에 값이 주입되는 org.springframework.beans.factory.annotation.Value 때문에 null값을 갖게 됨
    }

    @Test
    void connectNaverSearchApi() {
        //given
        String naverSearchUrl = "https://openapi.naver.com/v1/search/blog.json";

        String methodType = "GET";

        Map<String, String> header = new HashMap<>();

        header.put("Host", "openapi.naver.com");
        header.put("User-Agent", "curl/7.49.1");
        header.put("Accept", "*/*");
        header.put("X-Naver-Client-Id", "u37MwB_R7fOuSv1rJDhT");
        header.put("X-Naver-Client-Secret", "_vjHHYh4j2");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();

        param.put("query", Collections.singletonList(("search")));

        System.out.println(connector.process(naverSearchUrl, "GET",header, param, body));

    }
}