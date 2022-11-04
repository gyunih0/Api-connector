package bryansoi.apiconnector.service;

import bryansoi.apiconnector.config.Connector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiService {
    private final Connector connector;
    public String connect(String url, String method, Map<String, String> header,
                        MultiValueMap<String, String> queryParams, MultiValueMap<String, String> body) {
        ResponseEntity<String> process = connector.process(url, method,header, queryParams, body);
        return process.getBody();
    }
}
