
import java.sql.Date;
import java.util.ArrayList;
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

        System.out.println("Qual operação irá fazer no Banco? ");
        System.out.println("1: Localizar todos vendedores");
        System.out.println("2: Localizar vendedor por ID");
        System.out.println("3: Localizar vendedores por ID do departamento");
        System.out.println("4: Deletar vendedor");
        System.out.println("5: Atualizar vendedor");
        System.out.println("6: Adicionar novo vendedor");

        System.out.println();

        System.out.print("Digite o número: ");
        int a = sc.nextInt();

        System.out.println();
        System.out.println("Resultado:");

        switch (a) {
            case 1 -> { //FIND ALL
                List<Seller> list = sellerDao.findAll();

                for (Seller obj : list) {
                    System.out.println(obj);

                }
            }
            case 2 -> { // FIND BY ID
                System.out.println("Digite o ID do vendedor: ");
                int id = sc.nextInt();

                Seller seller = new Seller();

                seller = sellerDao.findById(id);

                System.out.println(seller);

            }
            case 3 -> { // FIND BY DEPARTMENT ID 
                System.out.println("Digite o ID do departamento: ");
                int id = sc.nextInt();

                Department dep = new Department(id, null);

                List<Seller> list = new ArrayList<>();
                list = sellerDao.findByDepartment(dep);

                for (Seller obj : list) {
                    System.out.println(obj);
                }

            }
            case 4 -> { //DELETE
                System.out.println("Digite o ID do vendedor: ");
                int id = sc.nextInt();

                sellerDao.deleteById(id);

                System.out.println("Vendedor excluido!");

            }
            case 5 -> { //UPDATE
                System.out.println("Digite o ID do vendedor: ");
                int id = sc.nextInt();
                sc.nextLine();

                Seller seller = sellerDao.findById(id);

                if (seller == null) {
                    System.out.println("Vendedor não encontrado!");

                } else {
                    System.out.println("Digite o novo nome do vendedor: ");
                    String name = sc.nextLine();

                    System.out.println("Digite o novo e-mail do vendedor: ");
                    String email = sc.nextLine();

                    System.out.println("Digite a nova data de nascimento (YYYY-MM-DD): ");
                    String birthStr = sc.nextLine();
                    Date birthDate = Date.valueOf(birthStr);

                    System.out.println("Digite o novo salário base: ");
                    double baseSalary = sc.nextDouble();

                    System.out.println("Digite o novo ID do departamento: ");
                    int deptId = sc.nextInt();
                    Department dept = new Department(deptId, null);

                    seller.setName(name);
                    seller.setEmail(email);
                    seller.setBirthdate(birthDate);
                    seller.setSalary(baseSalary);
                    seller.setDepartment(dept);

                    sellerDao.update(seller);
                    System.out.println("Atualização concluída com sucesso!");
                }

            }
            case 6 -> { // INSERT
                System.out.println("Insira os dados do novo vendedor: ");

                System.out.print("Nome: ");
                String name = sc.next();

                System.out.println("E-mail: ");
                String email = sc.nextLine();

                System.out.println("Data de nascimento (YYYY-MM-DD): ");
                String birthStr = sc.nextLine();
                Date birthDate = Date.valueOf(birthStr);

                System.out.println("Salário base: ");
                double baseSalary = sc.nextDouble();

                System.out.println("Digite o ID do departamento: ");
                int deptId = sc.nextInt();
                Department dept = new Department(deptId, null);

                Seller seller = new Seller(null, name, email, birthDate, baseSalary, dept);

                sellerDao.insert(seller);

                System.out.println("Vendedor adicionado com sucesso!");
            }
            default -> {
                System.out.println("Operação Inválida");
            }
        }

        sc.close();

    }
}
