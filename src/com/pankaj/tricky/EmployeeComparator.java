package com.pankaj.tricky;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Employee {
    int id;
    String name;
    long salary;

    public Employee(int id, String name, long salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
public class EmployeeComparator {

    public static void main(String[] args) {

        Employee emp1 = new Employee(5, "pqr", 10000);
        Employee emp2 = new Employee(2, "xyz", 5000);
        Employee emp3 = new Employee(7, "abc", 11000);
        Employee emp6 = new Employee(8, "abc", 6000);
        Employee emp4 = new Employee(1, "asdf", 2000);
        Employee emp5 = new Employee(1, "fdsa", 5000);

        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);
        employeeList.add(emp4);
        employeeList.add(emp5);
        employeeList.add(emp6);

        Comparator<Employee> nameComparator = (e1, e2) -> e1.getName().compareTo(e2.getName());
        Comparator<Employee> salaryComparator = ((Employee e1, Employee e2) -> {
            if (e1.getSalary() > e2.getSalary()) {
                return 1;
            } else {
                return -1;
            }
        });

        System.out.println("Employee list by Name and then salary :");
        Collections.sort(employeeList, Comparator.comparing(Employee::getName)
                .thenComparing(Employee::getSalary));
//                .thenComparing(Employee::getId));
        employeeList.stream().forEach(System.out::println);

        employeeList.sort(nameComparator);
        System.out.println("Employee list by Name :");
        employeeList.stream().forEach(System.out::println);

        employeeList.sort(salaryComparator);
        System.out.println("Employee list by Salary :");
        employeeList.stream().forEach(System.out::println);
    }
}

