package bryansoi.apiconnector.connector;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class TestApiService {

    public MultiValueMap<String, String> headerParams = new LinkedMultiValueMap<>();

    public MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    public MultiValueMap<String, String> bodyParams = new LinkedMultiValueMap<>();

    private final String baseUrl = "https://openapi.naver.com/v1/search/encyc.json";

    private final ApiConnector apiConnector;

    @Autowired
    public TestApiService(ApiConnector apiConnector) {
        this.apiConnector = apiConnector;
    }

    public String naverTest(String testData) {

        headerParams.add("X-Naver-Client-Id", "5wqt9i_rAwIjYlmy86Dn");
        headerParams.add("X-Naver-Client-Secret", "pCEUZRNYUW");

        queryParams.add("query", testData);
        queryParams.add("display", "10");


        String response = apiConnector.connect("get", baseUrl, queryParams, headerParams, bodyParams);

        return response;
    }
}
