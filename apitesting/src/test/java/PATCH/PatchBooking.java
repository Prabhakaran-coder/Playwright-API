package PATCH;

import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import resource.Booking;

public class PatchBooking {
    APIRequestContext APIContext;
    Gson gson=new Gson();
    String JsonPayload;
    int BookingId;
    String token;
    String firstname;
    Booking booking = new Booking();
    String Base_url="https://restful-booker.herokuapp.com/booking/";

    public PatchBooking(APIRequestContext APIContext,int BookingId,String token){
        this.APIContext=APIContext;
        this.BookingId=BookingId;
        this.token=token;
        PatchData();
    }
    private void PatchData(){
        booking.setFirstname("Kiran");
        booking.setLastname("Borae");
        firstname=booking.getFirstname();
        JsonPayload=gson.toJson(booking);
    }
    public void patchBooking(){
        APIResponse response = APIContext.patch(Base_url+BookingId, RequestOptions.create().setHeader("Cookie", "token="+token).setData(JsonPayload));
        System.out.println("response status:"+response.statusText());
        Assert.assertEquals(response.ok(), true);

        JsonObject responseBody= JsonParser.parseString(response.text()).getAsJsonObject();
        System.out.println("Firstname after patch:"+responseBody.get("firstname").getAsString());
        JsonObject bookingDates = responseBody.getAsJsonObject("bookingdates"); 
        String checkin = bookingDates.get("checkin").getAsString();
        System.out.println("Checkin:"+checkin);
        Assert.assertEquals(responseBody.get("firstname").getAsString(),firstname);
        response.dispose();
    }
}
