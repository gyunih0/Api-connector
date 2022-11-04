package bryansoi.apiconnector.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RequiredArgsConstructor
@Component
public class Connector {
    private final RestTemplate restTemplate;

    public ResponseEntity<String> process(String url, String method, Map<String, String> header,
                                          MultiValueMap<String, String> queryParams, MultiValueMap<String, String> body) {
        // header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        for (Map.Entry<String, String> entry : header.entrySet()) {
            headers.add(entry.getKey(), entry.getValue());
        }

        // uri -> for request
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParams(queryParams) // queryparam 추가
                .encode(StandardCharsets.UTF_8)
                .build();

        // with header + body
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(uri.toString(), selectMethod(method), entity, String.class);
    }

    private HttpMethod selectMethod(String method) {
        if (method.equals("POST")) {
            return HttpMethod.POST;
        }
        return HttpMethod.GET;
    }
}
