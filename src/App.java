
import java.util.Date;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {

    public static void main(String[] args) throws Exception {
        Department obj = new Department(1, "books");
        Seller seller = new Seller(1, "nando", "nando@mail", new Date(), 300.00, obj);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println(obj);
        System.out.println(seller);
    }
}
