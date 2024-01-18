package List.Correcao.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import List.Correcao.entities.Employee;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        System.out.println("How many employees will be registred?");
        int n = sc.nextInt();
        List<Employee> list = new ArrayList<>(); // list é uma interface que tem que ser instaciada através de uma
                                                 // classe concreta
        for (int i = 0; i < n; i++) {
            System.out.println("Employee #" + (i + 1) + ":");
            System.out.println("Id:");
            Integer id = sc.nextInt();
            while (hasId(list, id)) {  
                System.out.println("This Id number id already registred. Please insert a new id");   
                id = sc.nextInt(); // bloqueia até que um id não existente seja digitado
            }        
            System.out.println("Name: ");
            sc.nextLine(); // limpa buffer depois do nextInt // sempre fazer isso em um nextLine() depois
                           // de uma nextInt()
            String name = sc.nextLine();
            System.out.println("Salary: ");
            Double salary = sc.nextDouble();

            Employee e = new Employee(id, name, salary);

            list.add(e); // não é necessário chamar o método toString(). Uma vez que list é do tipo
                         // String e Employee
                         // já contém um toString, a conversão é feita automaticamente
           

        }
        /*
         * for (Employee e: list){
         * System.out.println(e);
         * }
         */
        System.out.println("Type the employee id that will have a salary increase");
        int idsalary = sc.nextInt();

        Integer pos = position(list, idsalary);

        if (pos != null) {
            System.out.println("What percentage will be increased on the salary?");
            Double percentage = sc.nextDouble();
            list.get(pos).increaseSalary(percentage);
        } else {
            System.out.println("Id not found");
        }

        for (Employee e : list) {
            System.out.println(e);
        }
        System.out.println("This Id already exists. Please, restart the program");
        sc.close();
    }

    public static Integer position(List<Employee> list, int id) { // método FORA do método main que recebe uma List e um
                                                                  // id como
        // paramet.
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) { // metod. .get() pega o elemento de índice i e aplica o getter getId(),
                return i; // comparando com o id que foi passado como parametr.
            } // achando, será retornado o int i, n achando temos null
        } // se trocássemos Integer por int, deveveríamos marcar um retorno -1(análogo a
          // null para int)
        return null;
    }
    public static boolean hasId(List<Employee> list, int id) {
        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }
}
