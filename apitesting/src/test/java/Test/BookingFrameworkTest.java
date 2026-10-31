package Test;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

import GET.GetBookings;
import PATCH.PatchBooking;
import POST.PostBooking;
import POST.PostLogin;
import PUT.PutUpdateBooking;
import DELETE.DeleteBooking;

public class BookingFrameworkTest {

    Playwright playwright;
    APIRequest request;
    APIRequestContext APIContext;
    String token;
    int bookingId;

   @BeforeClass(alwaysRun=true)
    public void setup() {

        playwright = Playwright.create();
       
        APIContext = playwright.request().newContext(
            new APIRequest.NewContextOptions()
                .setExtraHTTPHeaders(
                    Map.of(
                        "Accept", "application/json",
                        "Content-Type", "application/json"
                    )
                )
        );
        
    }

    @Test(groups={"Regression"},priority=1)
    public void Login(){
        System.out.println("Login:");
        PostLogin login = new PostLogin(APIContext);
        token=login.postLogin();
        System.out.println("token:"+token);
    }
     @Test(groups={"Regression"},priority=2)
    public void CreateBooking(){
        System.out.println("CreateBooking");
        PostBooking pullAllData = new PostBooking(APIContext);
        bookingId=pullAllData.postApiCall();
    }
    @Test(groups={"Regression"},priority=3)
    public void pullBookedData(){
        System.out.println("DatafromBackend:");
        GetBookings getBookings = new GetBookings(APIContext,bookingId);
        getBookings.getUsersData();
        getBookings.getSpecificUserData();
    }
    @Test(groups={"Regression"},dependsOnMethods={"Login"},priority=4)
    public void updateBooking(){
        System.out.println("Update booking: ");
        PutUpdateBooking UpdateBooking = new PutUpdateBooking(APIContext,token,bookingId);
        UpdateBooking.putApiCall();
    }
    @Test(groups={"Regression"},dependsOnMethods={"CreateBooking"},priority=5)
    public void PatchBooking(){
        System.out.println("PatchBooking:");
        PatchBooking patch = new PatchBooking(APIContext,bookingId,token);
        patch.patchBooking();
    }
    @Test(groups={"Regression"},dependsOnMethods={"CreateBooking"},priority=6)
    public void DeleteBooking(){
        System.out.println("PatchBooking:");
        DeleteBooking patch = new DeleteBooking(APIContext,token,bookingId);
        patch.deleteBooking();
    }
    @AfterClass
    public void TearDown() {
        if (request != null) {
            APIContext.dispose();
            playwright.close();
        }
    }

}
