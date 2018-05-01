package corporation.darkshadow.ocr.Pojo;

/**
 * Created by darkshadow on 28/4/18.
 */

public class CardDetails {
    private String name,email,phone;

    public CardDetails(){
        name = "null";
        email = "null";
        phone = "null";
    }

    public CardDetails(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {

        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
