package DELETE;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
public class DeleteBooking {
   APIRequestContext APIContext;
   String token;
   int bookingId;
   String Base_url="https://restful-booker.herokuapp.com/booking/";
   public DeleteBooking(APIRequestContext APIContext,String token,int bookingId){
    this.APIContext=APIContext;
    this.token=token;
    this.bookingId=bookingId;
   }

   public void deleteBooking(){
    APIResponse response= APIContext.delete(Base_url+bookingId,RequestOptions.create().setHeader("Cookie","token="+token));
    Assert.assertEquals(response.ok(),true);
    System.out.println("Reponse Status:"+response.statusText());
    response.dispose();
    }

}
