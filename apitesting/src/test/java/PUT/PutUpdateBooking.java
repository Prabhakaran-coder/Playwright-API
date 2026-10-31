package PUT;

import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import resource.Booking;
import resource.BookingDates;

public class PutUpdateBooking {

    APIRequestContext APIContext;
   
    Booking booking = new Booking();
    BookingDates bookingdate = new BookingDates();
    Gson gson = new Gson();
    String JsonPayload;
    String token;
    String firstname;
    int BookingId;

    String Base_url="https://restful-booker.herokuapp.com/booking/";
   
    public PutUpdateBooking(APIRequestContext APIContext,String token,int BookingId){
      
        this.APIContext=APIContext;
        this.token=token;
        this.BookingId=BookingId;
        pushData();
    }
      
    private void pushData(){
        booking.setFirstname("Dhuru");
        booking.setLastname("Mahi");
        booking.setTotalprice(111);
        booking.setDepositpaid(false);
        booking.setBookingdates(bookingdate);
        booking.setAdditionalneeds("Breakfast");
        bookingdate.setCheckin("2026-11-02");        
        bookingdate.setCheckout("2026-11-10");
        JsonPayload=gson.toJson(booking);
        firstname=booking.getFirstname();
    }

    public void putApiCall(){
       
        APIResponse response = APIContext.put(Base_url+BookingId, RequestOptions.create().setHeader("Cookie", "token="+token).setData(JsonPayload));
        System.out.println(response.status());
        System.out.println(response.statusText());
        Assert.assertEquals(response.ok(), true);

        JsonObject responseObject = JsonParser.parseString(response.text()).getAsJsonObject();
        Assert.assertEquals(responseObject.get("firstname").getAsString(),firstname);
        response.dispose();
    }

}
