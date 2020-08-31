package com.pankaj.interview.hascode;


import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EqualsTrueButHashCodeDiff {

    public static void main(String[] args) {
        HashMap<Employee,String> employeeMap = new HashMap<Employee,String>();
        Employee employee1 = new Employee(1, "Sachin1", new Date(1987, 2, 1), new BigDecimal(100000));
        Employee employee2 = new Employee(1, "Sachin1", new Date(1987, 2, 1), new BigDecimal(100000));
        Employee employee3 = new Employee(1, "Arun", new Date(1987, 2, 1), new BigDecimal(100000));
        Employee employee4 = new Employee(3, "Kedar", new Date(1987, 2, 1), new BigDecimal(100000));
        Employee employee5 = new Employee(2, "Rajesh", new Date(1987, 2, 1), new BigDecimal(100000));
        Employee employee6 = new Employee(5, "Saaaaa", new Date(1987, 2, 1), new BigDecimal(100000));
//        employeeMap.put(employee1,"Same");
//        employeeMap.put(employee2,"Same");
//        employeeMap.put(employee2,"same");
        System.out.println("employee1.equals(employee2): "+employee1.equals(employee2));
        System.out.println("employee3.equals(employee4): "+employee3.equals(employee4));
        System.out.println("employee1.hashCode(): "+employee1.hashCode());
        System.out.println("employee2.hashCode(): "+employee2.hashCode());
//        System.out.println("employee2.hashCode(): "+employee2.hashCode());
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);
        //sort employees array using Comparator by Salary
        Collections.sort(employees, new NameSorter());
        System.out.println("\nEmployees list sorted by Name:");
        employees.forEach(emp -> System.out.println(emp));
    }


}

class Employee {
    private final long id;
    private final String name;
    private final Date dateOfBirth;
    private final BigDecimal salary;
    AtomicInteger atomicInteger = new AtomicInteger(1);

    public Employee(long id, String name, Date dateOfBirth, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        Employee employee = (Employee) o;
        if(employee.getName().equals("Sachin1")){
            return  true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", salary=" + salary +
                '}';
    }
}

class NameSorter implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}