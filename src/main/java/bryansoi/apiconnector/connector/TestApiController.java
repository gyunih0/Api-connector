package bryansoi.apiconnector.connector;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {

    private final TestApiService testApiService;

    @Autowired
    public TestApiController(TestApiService testApiService) {
        this.testApiService = testApiService;
    }

    @GetMapping("/api/test")
    public String testApi() {
        return testApiService.naverTest("item");
    }

}
