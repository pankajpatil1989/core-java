package com.pankaj.interview.map;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HashMapEmployeeAsKey {

    public static void main(String[] args) {
        HashMap<Emp,String> employeeMap = new HashMap<Emp,String>();
        Emp employee1 = Emp.EmployeeBuilder.anEmployee().withId(1)
                .withName("Sachin")
                .withDateOfBirth(new Date(1987, 2, 1))
                .withSalary(new BigDecimal(100000))
                .build();
        employeeMap.put(employee1,"India");
        for (Map.Entry<Emp, String> employeeStringEntry : employeeMap.entrySet()) {
            System.out.println(employeeStringEntry.getKey().toString()
                    +" \nValue: "+employeeStringEntry.getValue()
                    +" \nHasCode: "+employeeStringEntry.getKey().hashCode());
        }
        System.out.println("map.get(): "+employeeMap.get(employee1)+"\n");
        Emp immutableUpdatedEmployee1 = Emp.EmployeeBuilder.anEmployee(employee1).withName("Rahul").build();
        System.out.println("Employee1 Updated name Rahul");
        System.out.println(immutableUpdatedEmployee1.toString());
        for (Map.Entry<Emp, String> employeeStringEntry : employeeMap.entrySet()) {
            System.out.println(employeeStringEntry.getKey().toString()
                    +" \nValue: "+employeeStringEntry.getValue()
                    +" \nHasCode: "+employeeStringEntry.getKey().hashCode());
        }
        System.out.println("map.get(): "+employeeMap.get(immutableUpdatedEmployee1));
        // Returns null
        System.out.println();

        Emp employee2 = Emp.EmployeeBuilder.anEmployee().withId(1)
                .withName("Sachin")
                .withDateOfBirth(new Date(1987, 2, 1))
                .withSalary(new BigDecimal(100000))
                .build();
        for (Map.Entry<Emp, String> employeeStringEntry : employeeMap.entrySet()) {
            System.out.println(employeeStringEntry.getKey().toString()
                    +" \nValue: "+employeeStringEntry.getValue()
                    +" \nHasCode: "+employeeStringEntry.getKey().hashCode());
        }
        System.out.println("map.get(): "+employeeMap.get(employee2));
        System.out.println("Total key size: "+employeeMap.keySet().size());
        // Now this works fine and it shall return  the correct object from the HashMap
    }

}
final class Emp {
    private final long id;
    private final String name;
    private final Date dateOfBirth;
    private final BigDecimal salary;
    public Emp(EmployeeBuilder employeeBuilder) {
        this.id = employeeBuilder.id;
        this.name = employeeBuilder.name;
        this.dateOfBirth = employeeBuilder.dateOfBirth;
        this.salary = employeeBuilder.salary;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp employee = (Emp) o;
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
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Date getDateOfBirth() {
        return (Date) dateOfBirth.clone();
    }
    public BigDecimal getSalary() {
        return salary;
    }

    static final class EmployeeBuilder {
        private long id;
        private String name;
        private Date dateOfBirth;
        private BigDecimal salary;
        private EmployeeBuilder() {
        }
        public static EmployeeBuilder anEmployee() {
            return new EmployeeBuilder();
        }
        public static EmployeeBuilder anEmployee(Emp employee) {
            return anEmployee().withId(employee.getId()).withName(employee.getName()).withDateOfBirth(employee.getDateOfBirth()).withSalary(employee.getSalary());
        }
        public EmployeeBuilder withId(long id) {
            this.id = id;
            return this;
        }
        public EmployeeBuilder withName(String name) {
            this.name = name;
            return this;
        }
        public EmployeeBuilder withDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public EmployeeBuilder withSalary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }
        public Emp build() {
            return new Emp(this);
        }
    }
}