package resource;

public class BookingDates {
    private String checkin;
    private String checkout;

    public String getCheckin(){
        return checkin;
    }
    public void setCheckin(String checkin_yy_mm_dd){
        this.checkin=checkin_yy_mm_dd;
    }
    public String getCheckout(){
        return checkout;
    }
    public void setCheckout(String checkout_yy_mm_dd){
        this.checkout=checkout_yy_mm_dd;
    }
}
