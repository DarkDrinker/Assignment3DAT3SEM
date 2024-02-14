package Week3.ExcersiseInClass;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();


        //grab all employess and print them
       /* List<Employee> listOfemployee = EmployeeDAO.number1();
        for (Employee employee :
                listOfemployee) {
            System.out.println(employee.getFirstName());
            System.out.println(employee.getLastName());
            System.out.println(employee.getEmail());
            System.out.println(employee.getSalary());
            System.out.println(employee.getDepartment());
        } */

        List<Employee> listOfemployee2 = EmployeeDAO.number2();
        System.out.println("these employees make more than 1000");
        for (Employee employee :
                listOfemployee2) {
            System.out.println(employee.getFirstName() + " " + employee.getLastName());


        }

        List<Employee> listOfemployee3 = EmployeeDAO.number3();
        System.out.println("these employees work in Finance");
        for (Employee employee :
                listOfemployee3) {
            System.out.println(employee.getFirstName() + " " + employee.getLastName());
        }
        List<Employee> listOfemployee4 = EmployeeDAO.number4();
        System.out.println("these employees start with a");
        for (Employee employee :
                listOfemployee4) {
            System.out.println(employee.getFirstName() + " " + employee.getLastName());
        }


        System.out.println("update employee salary");
        Employee anders = employeeDAO.findById(1);
        System.out.println("before");
        System.out.println(anders.getFirstName()+anders.getLastName()+anders.getSalary());
        EmployeeDAO.number5(1);
        anders = employeeDAO.findById(1);
        System.out.println("after");
        System.out.println(anders.getFirstName()+anders.getLastName()+anders.getSalary());

        System.out.println("update employee department");
        System.out.println("before");
        System.out.println(anders.getFirstName()+anders.getLastName()+anders.getDepartment());
        EmployeeDAO.number6(1);
        anders = employeeDAO.findById(1);
        System.out.println("after");
        System.out.println(anders.getFirstName()+anders.getLastName()+anders.getDepartment());



        System.out.println(EmployeeDAO.number7());

        System.out.println(EmployeeDAO.number8());



        // close connection
        EmployeeDAO.close();
    }
}
