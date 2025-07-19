
import java.util.List;
import java.util.Scanner;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class App2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepartmentDao depDao = DaoFactory.createDepartmentDao();

        System.out.println("Qual operação irá fazer no Banco? ");
        System.out.println("1: Localizar todos departamento");
        System.out.println("2: Adicionar novo departamento");
        System.out.println("3: Deletar departamento");
        System.out.println("4: Localizar departamento por ID");
        System.out.println("5: Atualizar departamento");

        System.out.println();

        System.out.print("Digite o número: ");
        int a = sc.nextInt();

        System.out.println();
        System.out.println("Resultado:");

        switch (a) {
            case 1 -> { //FIND ALL
                List<Department> list = depDao.findAll();

                for (Department obj : list) {
                    System.out.println(obj);

                }
            }
            case 2 -> { //INSERT
                System.out.println("Digite o nome do departamento:");
                String name = sc.next();

                Department dep = new Department(null, name);

                depDao.insert(dep);

                System.out.println("Departamento inserido!");
            }
            case 3 -> { //DELETE
                System.out.println("Digite o ID do departamento pra excluir: ");
                int id = sc.nextInt();

                depDao.deleteById(id);

                System.out.println("Departamento deletado!");
            }
            case 4 -> { //FIND BY ID
                System.out.println("Digite o ID do departamento para busca: ");
                int id = sc.nextInt();

                Department dep = depDao.findById(id);

                System.out.println(dep);

            }
            case 5 -> { //UPDATE
                System.out.println("Digite o Id do departamento para atualizar:");
                int id = sc.nextInt();

                System.out.println();

                System.out.print("Digite o novo nome: ");
                String name = sc.next();

                Department dep = new Department(id, name);
                depDao.update(dep);

                System.out.println("Departamento atualizado! " + dep);

            }
            default -> {
                System.out.println("Operação invalida!");
            }
        }

        sc.close();
    }

}
