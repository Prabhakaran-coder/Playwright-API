package POST;

import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import resource.Login;

public class PostLogin {

    APIRequestContext APIContext;
    Gson gson =new Gson();
    String gsonPayload;
    String token;
    
    String Base_url="https://restful-booker.herokuapp.com/auth";

    public PostLogin(APIRequestContext APIContext){
        this.APIContext=APIContext;
    }

   
    public void loadLogin(){
        Login login = new Login();
        String username= System.getProperty("username");
        String password = System.getProperty("password");
        login.setUsername(username);
        login.setPassword(password);
        gsonPayload=gson.toJson(login);
        System.out.println("username:"+username);
    } 
    
    public String postLogin(){
        loadLogin();
        APIResponse response = APIContext.post(Base_url, RequestOptions.create().setData(gsonPayload));
        System.out.println("Ststus"+response.statusText());
        Assert.assertEquals(response.ok(), true);
        JsonObject responseObject = JsonParser.parseString(response.text()).getAsJsonObject();
        token=responseObject.get("token").getAsString();
        response.dispose();
        return token;
    }
}
