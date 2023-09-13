package api.test.entity;

import lombok.Data;

@Data
public class BookingResponse {

    private String firstname = "Josh";
    private String lastname = "Allen";
    private int totalprice = 111;
    private boolean depositpaid = true;
    private String additionalneeds = "superb owls";

}
