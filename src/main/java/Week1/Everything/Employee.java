package Week1.Everything;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class Employee {
    private String name;
    private int age;
    private LocalDate birthdate;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Employee(String name, LocalDate birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge1() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Month getBirthMonth() {
        return birthdate.getMonth();
    }

    public int getAge() {
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

}