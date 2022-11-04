package bryansoi.apiconnector.connector;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Map;


/*
    response 방식 미정
    xml, json 변환 방식 분기점 필요

 */
@Configuration
public class ApiConnector {

    @Value("${apiConnector.responseType}")
    String responseType = "JSON";

    private MultiValueMap<String, String> query = new LinkedMultiValueMap<>();
    private MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
    private MultiValueMap<String, String> body = new LinkedMultiValueMap<>();


    private final RestTemplate restTemplate;
    @Autowired
    public ApiConnector(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    public HttpMethod methodSelector(String method) {
        switch (method) {
            case "get":
                return HttpMethod.GET;
            case "post":
                return HttpMethod.POST;
            case "put":
                return HttpMethod.PUT;
            case "delete":
                return HttpMethod.DELETE;
            default:
                return null;
        }
    }


    // parameters setter
    public void addHeader(String key, String value){
        this.header.add(key, value);
    }
    public void addQuery(String key, String value) {
        this.query.add(key, value);
    }
    public void addBody(String key, String value) {
        this.body.add(key, value);
    }


    // Api 호출 method
    public String connect(
            String httpMethod,
            String BaseUrl) {

        System.out.println("value: " + responseType);

        // Uri Build
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(BaseUrl)
                .queryParams(query)
                .encode()
                .build();

        System.out.println("uri = " + uri.toString());



        // Header Build
        HttpHeaders headers = new HttpHeaders();

        if (!header.isEmpty()) {
            for (String headerName : header.keySet()) {
                headers.add(headerName, header.getFirst(headerName));
            }
        }


        // HttpEntity Build
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);



        /*
        RestTemplate
        Response Type 미정
         */
        ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), methodSelector(httpMethod), entity, Map.class);

        System.out.println("resultMap.getStatusCode() = " + resultMap.getStatusCode());
        System.out.println("resultMap.getHeaders() = " + resultMap.getHeaders());
        System.out.println("resultMap.getBody() = " + resultMap.getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(resultMap.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // 대충 string 으로 구현하였으나 객체로 만들 예정 --> 불가능한듯
        return result;
    }

}
