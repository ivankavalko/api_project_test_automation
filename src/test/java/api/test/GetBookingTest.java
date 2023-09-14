package api.test;

import api.test.entity.BookingIdResponse;
import api.test.entity.BookingResponse;
import api.test.entity.CreateTokenResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class GetBookingTest extends AbstractTest {

    @Test
    public void getBookingIdsTest() {
        //Arrange

        String addURI = "https://restful-booker.herokuapp.com/booking";

        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + token);
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");


        HttpEntity<String> entity = new HttpEntity<>(headers);

        //Act

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<BookingIdResponse>> response = restTemplate.exchange
                (addURI, HttpMethod.GET, entity, new ParameterizedTypeReference<List<BookingIdResponse>>(){});

        //Assert

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        List<BookingIdResponse> bookingIds = response.getBody();
        assertNotNull(bookingIds);
        assertFalse(bookingIds.isEmpty());
    }

    @Test
    public void getBookingTest(){
        //Arrange

        String addURI = "https://restful-booker.herokuapp.com/booking/{id}";

        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + token);
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");


        HttpEntity<String> entity = new HttpEntity<>(headers);

        //Act

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BookingResponse> response =
                restTemplate.exchange(addURI, HttpMethod.GET, entity, BookingResponse.class, getExcitingBookingId());

        //Assert

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        BookingResponse booking = response.getBody();
        assertNotNull(booking);
    }


    private String getExcitingBookingId(){
        String addURI = "https://restful-booker.herokuapp.com/booking";

        String token = fetchToken("admin", "password123");

        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + token);
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");


        HttpEntity<String> entity = new HttpEntity<>(headers);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<BookingIdResponse>> response = restTemplate.exchange
                (addURI, HttpMethod.GET, entity, new ParameterizedTypeReference<List<BookingIdResponse>>(){});

        return response.getBody().get(0).getBookingid();
    }

}
