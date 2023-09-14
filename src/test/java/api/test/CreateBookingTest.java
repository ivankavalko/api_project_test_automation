package api.test;

import api.test.entity.Booking;
import api.test.entity.BookingDates;
import api.test.entity.BookingResponseBody;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class CreateBookingTest extends AbstractTest {

    @Test
    public void createBookingTest() {

        //Arrange

        String addURI = "https://restful-booker.herokuapp.com/booking";

        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + token);
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");



        Booking bookingRequestBody = new Booking();
        bookingRequestBody.setFirstname("Jason");
        bookingRequestBody.setLastname("Versus");
        bookingRequestBody.setTotalprice(145);
        bookingRequestBody.setDepositpaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(LocalDate.of(2020,4,20));
        bookingDates.setCheckout(LocalDate.of(2023,5,31));

        bookingRequestBody.setBookingdates(bookingDates);

        bookingRequestBody.setAdditionalneeds("Dinner");

        HttpEntity<Booking> entity = new HttpEntity<>(bookingRequestBody, headers);


        //Act

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BookingResponseBody> response = restTemplate.exchange
                (addURI, HttpMethod.POST, entity, BookingResponseBody.class);


        //Assert
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        BookingResponseBody bookingResponseBody = response.getBody();
        Booking bookingResponse = bookingResponseBody.getBooking();
        assertNotNull(bookingResponse);
        assertEquals(bookingResponse.getFirstname(), bookingRequestBody.getFirstname());
        assertEquals(bookingResponse.getLastname(), bookingRequestBody.getLastname());
        assertEquals(bookingResponse.getTotalprice(), bookingRequestBody.getTotalprice());
        assertEquals(bookingResponse.isDepositpaid(), bookingRequestBody.isDepositpaid());
        assertEquals(bookingResponse.getBookingdates().getCheckin(), bookingRequestBody.getBookingdates().getCheckin());
        assertEquals(bookingResponse.getBookingdates().getCheckout(), bookingRequestBody.getBookingdates().getCheckout());
        assertEquals(bookingResponse.getAdditionalneeds(), bookingRequestBody.getAdditionalneeds());
        assertNotNull(bookingResponseBody.getBookingid());
    }
}


