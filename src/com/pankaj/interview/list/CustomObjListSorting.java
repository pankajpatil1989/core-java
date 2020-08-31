package com.pankaj.interview.list;

import java.util.*;

public class CustomObjListSorting {

    /**
     * This class shows how to sort custom objects array/list
     * implementing Comparable and Comparator interfaces
     *
     * @param args
     */
    public static void main(String[] args) {

        //sorting custom object array
        List<Employee> employees = new ArrayList<Employee>();
//        Employee[] empArr = new Employee[4];
        employees.add(new Employee(10, "Mikey", 25, 10000));
        employees.add(new Employee(20, "Arun", 29, 20000));
        employees.add(new Employee(5, "Lisa", 35, 5000));
        employees.add(new Employee(1, "Pankaj", 32, 50000));
//        employees.add(new Employee(1, "Pankaj", 32, 50000));

        //sorting employees array using Comparable interface implementation
        Collections.sort(employees);
        System.out.println("Default Sorting of Employees list:");
        employees.forEach(emp -> System.out.println(emp));

        //sort employees array using Comparator by Salary
        Collections.sort(employees, new SalarySorter());
        System.out.println("\nEmployees list sorted by Salary:");
        employees.forEach(emp -> System.out.println(emp));

        //sort employees array using Comparator by Age
        Collections.sort(employees, new AgeSorter());
        System.out.println("\nEmployees list sorted by Age:");
        employees.forEach(emp -> System.out.println(emp));

        //sort employees array using Comparator by Name
        Collections.sort(employees, new NameSorter());
        System.out.println("\nEmployees list sorted by Name:");
        employees.forEach(emp -> System.out.println(emp));

        //Employees list sorted by ID and then name using Comparator class
        employees.add(new Employee(1, "Mikey", 25, 10000));
        Collections.sort(employees, new EmployeeComparatorByIdAndName());
        System.out.println("\nEmployees list sorted by ID and Name");
        employees.forEach(emp -> System.out.println(emp));
    }
}

class Employee implements Comparable<Employee> {

    private int id;
    private String name;
    private int age;
    private long salary;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getSalary() {
        return salary;
    }

    public Employee(int id, String name, int age, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee emp) {
        //let's sort the employee based on an id in ascending order
        //returns a negative integer, zero, or a positive integer as this employee id
        //is less than, equal to, or greater than the specified object.
        return (this.id - emp.id);
    }

    @Override
    //this is required to print the user-friendly information about the Employee
    public String toString() {
        return "[id=" + this.id + ", name=" + this.name + ", age=" + this.age + ", salary=" +
                this.salary + "]";
    }
// First way
//    /**
//     * Comparator to sort employees list or array in order of Salary
//     */
//    public static Comparator<Employee> SalaryComparator = new Comparator<Employee>() {
//
//        @Override
//        public int compare(Employee e1, Employee e2) {
//            return (int) (e1.getSalary() - e2.getSalary());
//        }
//    };
//
//    /**
//     * Comparator to sort employees list or array in order of Age
//     */
//    public static Comparator<Employee> AgeComparator = new Comparator<Employee>() {
//
//        @Override
//        public int compare(Employee e1, Employee e2) {
//            return e1.getAge() - e2.getAge();
//        }
//    };
//
//
//    /**
//     * Comparator to sort employees list or array in order of Name
//     */
//    public static Comparator<Employee> NameComparator = new Comparator<Employee>() {
//
//        @Override
//        public int compare(Employee e1, Employee e2) {
//            return e1.getName().compareTo(e2.getName());
//        }
//    };
}
// Second way
class AgeSorter implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getAge() - o2.getAge();
    }
}

class NameSorter implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class SalarySorter implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return (int) (o1.getSalary() - o2.getSalary());
    }
}

class EmployeeComparatorByIdAndName implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        int flag = o1.getId() - o2.getId();
        if(flag==0) flag = o1.getName().compareTo(o2.getName());
        return flag;
    }

}