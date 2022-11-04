package bryansoi.apiconnector.config;

import bryansoi.apiconnector.connector.ApiConnector;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ApiConnectorConfig {

    @Bean
    HttpClient httpClient() {
        return HttpClientBuilder.create()
                .setMaxConnTotal(100)
                .setMaxConnPerRoute(5)
                .build();
    }

    @Bean
    HttpComponentsClientHttpRequestFactory factory(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(3000);
        factory.setHttpClient(httpClient);

        return factory;
    }

    @Bean
    RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

//    @Bean
//    ApiConnector apiConnector() {
//        return new ApiConnector(restTemplate(factory(httpClient())));
//    }

}

