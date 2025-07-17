
import java.util.Date;
import model.entities.Department;
import model.entities.Seller;

public class App {

    public static void main(String[] args) throws Exception {
        Department obj = new Department(1, "books");
        Seller seller = new Seller(1, "nando", "nando@mail", new Date(), 300.00, obj);

        System.out.println(obj);
        System.out.println(seller);
    }
}
