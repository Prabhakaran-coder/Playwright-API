package POST;

import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import resource.Booking;
import resource.BookingDates;

public class PostBooking {
   
    APIRequestContext APIContext;
    Booking booking = new Booking();
    BookingDates bookingdate = new BookingDates();
    Gson gson = new Gson();
    String jsonPayload;
    
    String Base_url="https://restful-booker.herokuapp.com/booking/";

     public PostBooking(APIRequestContext APIContext){
        this.APIContext=APIContext;
         loadData();
    }
   
    private void loadData(){
        booking.setFirstname("Dhurudhar");
        booking.setLastname("Mahi");
        booking.setTotalprice(111);
        booking.setDepositpaid(false);
        booking.setBookingdates(bookingdate);
        booking.setAdditionalneeds("Breakfast");
        bookingdate.setCheckin("2026-11-02");        
        bookingdate.setCheckout("2026-11-010");
        jsonPayload = gson.toJson(booking);
    }

   
    public int postApiCall(){
        APIResponse response = APIContext.post(Base_url, RequestOptions.create().setData(jsonPayload));
        System.out.println(response.statusText()+response.status());
        Assert.assertEquals(response.ok(), true);
        
        JsonObject reponseBody = JsonParser.parseString(response.text()).getAsJsonObject();
        int bookingId= reponseBody.get("bookingid").getAsInt();
        System.out.println("BookingId: "+bookingId);
        response.dispose();
        return bookingId;
    }
}
