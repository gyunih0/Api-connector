package bryansoi.apiconnector.controller;

import bryansoi.apiconnector.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {
    private final ApiService apiService;
    //httpRequest를 가공해볼까라고 생각해봄
    @GetMapping
    public String getRequest(HttpRequest httpRequest) {
        return httpRequest.getRequestLine().toString();
    }

}
