package bryansoi.apiconnector.connector;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Configuration
public class ApiConnector {

    private final RestTemplate restTemplate;


    @Autowired
    public ApiConnector(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    private MultiValueMap<String, String> queryParams;
//    private MultiValueMap<String, String> headerParams;
//    private MultiValueMap<String, String> body;


    /*
    * return type 미정
    * body, headerParams Nullable 하게 수정 필요
    * httpMethod 분기 필요
    * */
    String connect(
            String httpMethod,
            String BaseUrl ,
            MultiValueMap<String, String> queryParams,
            MultiValueMap<String, String> headerParams,
            MultiValueMap<String, String> body) {


        // Uri Build
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(BaseUrl)
                .queryParams(queryParams)
                .encode()
                .build();

        System.out.println("uri = " + uri.toString());



        // Header Build
        HttpHeaders headers = new HttpHeaders();

        if (!headerParams.isEmpty()) {
            for (String headerName : headerParams.keySet()) {
                headers.add(headerName, headerParams.getFirst(headerName));
            }
        }


        // HttpEntity Build
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);


        /*
        RestTemplate
        Response Type 미정
         */
        ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(resultMap.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // 대충 string 으로 구현하였으나 객체로 만들 예정
        return result;
    }

}
