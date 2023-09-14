package api.test;

import api.test.entity.CreateTokenResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;

public abstract class AbstractTest {

    protected String token;

    @BeforeClass
    public void beforeClass(){
        token = fetchToken("admin", "password123");
    }

    protected String fetchToken(String username, String password){
        String addURI = "https://restful-booker.herokuapp.com/auth";

        String payload = "{\n" +
                "    \"username\" : \"" + username + "\",\n" +
                "    \"password\" : \"" + password + "\"\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();

        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");


        HttpEntity<String> entity = new HttpEntity<>(payload, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CreateTokenResponse> response = restTemplate.exchange(addURI, HttpMethod.POST, entity, CreateTokenResponse.class);


        return response.getBody().getToken();
    }

}
