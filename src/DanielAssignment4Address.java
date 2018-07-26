import java.io.Serializable;

public class DanielAssignment4Address implements Serializable {
    String name;
    String street;
    String city;
    String state;
    String zip;

    DanielAssignment4Address(String name, String street, String city, String state, String zip) {
        this.street = street;
        this.name = name;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}
