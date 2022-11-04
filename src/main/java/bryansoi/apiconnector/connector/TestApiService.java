package bryansoi.apiconnector.connector;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class TestApiService {


    private final ApiConnector apiConnector;

    @Autowired
    public TestApiService(ApiConnector apiConnector) {
        this.apiConnector = apiConnector;
    }

    public String naverTest(String testData) {

        String baseUrl = "https://openapi.naver.com/v1/search/encyc.json";

        apiConnector.addHeader("X-Naver-Client-Id", "5wqt9i_rAwIjYlmy86Dn");
        apiConnector.addHeader("X-Naver-Client-Secret", "pCEUZRNYUW");
        apiConnector.addQuery("query", testData);
        apiConnector.addQuery("display", "10");


        String response = apiConnector.connect("get", baseUrl);

        return response;
    }
}
