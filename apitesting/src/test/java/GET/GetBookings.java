package GET;

import org.testng.Assert;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;

public class GetBookings {
  
    APIRequestContext APIContext;
    int BookingId;

    String Base_url="https://restful-booker.herokuapp.com/booking/";
    
    public GetBookings(APIRequestContext APIContext,int BookingId){
        this.APIContext=APIContext;
        this.BookingId=BookingId;
    }
   
    public void getUsersData(){
        APIResponse response = APIContext.get(Base_url);
        Assert.assertEquals(response.status(),200);
        JsonArray responseArray = JsonParser.parseString(response.text()).getAsJsonArray();
        for (Object responseData : responseArray) {
          System.out.println(responseData);
        }
       response.dispose();
    }
  
     public void getSpecificUserData(){
        APIResponse response = APIContext.get(Base_url+BookingId);
        Assert.assertEquals(response.status(),200);
        JsonObject responseData = JsonParser.parseString(response.text()).getAsJsonObject();
        System.out.println("Data: "+responseData);
        String firstname = responseData.get("firstname").getAsString();
       

        JsonObject bookingDates = responseData.getAsJsonObject("bookingdates");
        String checkin = bookingDates.get("checkin").getAsString();
        String checkout = bookingDates.get("checkout").getAsString();
        System.out.println("checkin:"+checkin);
        System.out.println("checkout:"+checkout);
        response.dispose();
    }

}
