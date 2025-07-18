
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("teste 1: find by id");

        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println();

        System.out.println("teste 2: find by department");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);

        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println();

        System.out.println("teste 3: find all seller");
        list = sellerDao.findAll();

        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println();

        System.out.println("teste 4: insert seller");
        Seller newSeller = new Seller(null, "nando2", "nando2@gmail.com", new Date(), 4000.00, department);
        sellerDao.insert(newSeller);
        System.out.println("new seller: " + newSeller.getId());

        System.out.println();

        System.out.println("teste 5: insert seller");
        seller = sellerDao.findById(1);
        seller.setName("nandooooooooooo");
        sellerDao.update(seller);
        System.out.println("atualizado");

        System.out.println();

        System.out.println("teste 6: delete seller");
        System.out.println("Id para ser deletado: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Seller deletado");

        sc.close();

    }
}
