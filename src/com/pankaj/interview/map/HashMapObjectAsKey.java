package com.pankaj.interview.map;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HashMapObjectAsKey {
    public static void main(String[] args) {
//
        HashMap<Employee,String> employeeMap = new HashMap<Employee,String>();
        // Employee1 add
        Employee employee1 = new Employee(1,"Sachin",new Date(1987,2,1),new BigDecimal(100000));
        employeeMap.put(employee1,"India");
        System.out.println("Employee1 added:");
        for (Map.Entry<Employee, String> employeeStringEntry : employeeMap.entrySet()) {
            System.out.println(employeeStringEntry.getKey().toString()
                    +" \nValue: "+employeeStringEntry.getValue()
                    +" \nHasCode: "+employeeStringEntry.getKey().hashCode());
        }
        System.out.println("map.get(): "+employeeMap.get(employee1)+"\n");
        // Employee1 update with name Rahul
        Employee immutableUpdatedEmployee1 =new Employee(1,"Rahul",new Date(1987,2,1),new BigDecimal(100000));
        System.out.println("Employee1 update with name Rahul");
        for (Map.Entry<Employee, String> employeeStringEntry : employeeMap.entrySet()) {
            System.out.println(employeeStringEntry.getKey().toString()
                    +" \nValue: "+employeeStringEntry.getValue()
                    +" \nHasCode: "+employeeStringEntry.getKey().hashCode());
        }
        System.out.println("map.get(): "+employeeMap.get(immutableUpdatedEmployee1)+"\n");
        // Returns null

        // Employee2 add  with id 1
        Employee employee2 = new Employee(1,"Sachin",new Date(1987,2,1),new BigDecimal(100000));
        System.out.println("employee2 added with same id and name");
        employeeMap.put(employee2,"Japan");
        for (Map.Entry<Employee, String> employeeStringEntry : employeeMap.entrySet()) {
            System.out.println(employeeStringEntry.getKey().toString()
                    +" \nValue: "+employeeStringEntry.getValue()
                    +" \nHasCode: "+employeeStringEntry.getKey().hashCode());
        }
        System.out.println("map.get(): "+employeeMap.get(employee2)+"\n");
        // Employee3 add with id 2
        Employee employee3 = new Employee(2,"Sachin",new Date(1987,2,1),new BigDecimal(100000));
        System.out.println("Employee3 add with id 2");
        employeeMap.put(employee3,"US");
        System.out.println("For Loop on total entries:");
        for (Map.Entry<Employee, String> employeeStringEntry : employeeMap.entrySet()) {
            System.out.println(employeeStringEntry.getKey().toString()
                    +" \nValue: "+employeeStringEntry.getValue()
                    +" \nHasCode: "+employeeStringEntry.getKey().hashCode());
        }
        System.out.println("map.get(): "+employeeMap.get(employee3)+"\n");

        System.out.println("Total key size: "+employeeMap.keySet().size());
        // output
        /**
         Employee1 added:
         Employee{id=1, name='Sachin', dateOfBirth=Tue Mar 01 00:00:00 IST 3887, salary=100000}
         Value: India
         HasCode: -1764804367
         map.get(): India

         Employee1 update with name Rahul
         Employee{id=1, name='Sachin', dateOfBirth=Tue Mar 01 00:00:00 IST 3887, salary=100000}
         Value: India
         HasCode: -1764804367
         map.get(): null

         employee2 added with same id and name
         Employee{id=1, name='Sachin', dateOfBirth=Tue Mar 01 00:00:00 IST 3887, salary=100000}
         Value: Japan
         HasCode: -1764804367
         map.get(): Japan

         Employee3 add with id 2
         For Loop on total entries:
         Employee{id=1, name='Sachin', dateOfBirth=Tue Mar 01 00:00:00 IST 3887, salary=100000}
         Value: Japan
         HasCode: -1764804367
         Employee{id=2, name='Sachin', dateOfBirth=Tue Mar 01 00:00:00 IST 3887, salary=100000}
         Value: US
         HasCode: -1764774576
         map.get(): US

         Total key size: 2
         **/
    }

}



final class Employee {
    private final long id;
    private final String name;
    private final Date dateOfBirth;
    private final BigDecimal salary;

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
        return new Date(dateOfBirth.getTime());
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        if (id != employee.id) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(employee.dateOfBirth) : employee.dateOfBirth != null)
            return false;
        return salary != null ? salary.equals(employee.salary) : employee.salary == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
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
