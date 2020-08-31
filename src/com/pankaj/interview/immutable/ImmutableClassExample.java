package com.pankaj.interview.immutable;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 1. Declare your class as final, So other classes can't extend it and break its immutability
final class MyImmutableClass {

    // 2. Make all your fields private they can't be accessed outside your class
    // 3. Mark them as final, so no one can modify them anywhere else apart from the constructor,
    // if you do not have any specific requirement to not do so
    private final int id;
    private final String name;
    private final Date dob;

    // 4. Create an constructor with argument so you can assign instantiate your object with a proper state
    public MyImmutableClass(int id, String name, Date dob) {
        this.id = id;
        this.name = name;
        // 5. Initialise all your fields by deeply copying them if they are not immutable in nature
        this.dob = new Date(dob.getTime());
        //this line prevent it form serialization and reflection
        System.setSecurityManager(new SecurityManager());
    }

    // 6. Do not provide setters for your fields, or define them private if you have some requirement
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // 7. Instead of returning objects from the getters return deep copy them if your objects are not immutable
    public Date getDob() {
        return new Date(dob.getTime());
    }

    @Override
    public String toString() {
        return "ImmutableEmployee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }

}

public class ImmutableClassExample {


    public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, ParseException {
        Date dob = new SimpleDateFormat("dd-mm-yyyy").parse("10-12-1993");
        MyImmutableClass employee = new MyImmutableClass(1, "Naresh", dob);

        System.out.println(employee);
        // Prints - ImmutableEmployee{id=1, name='Naresh', dob=Sun Jan 10 00:12:00 IST 1993}

        dob.setMonth(1);
        System.out.println(dob);
        // Prints - Wed Feb 10 00:12:00 IST 1993

        Date temp = employee.getDob();
        temp.setMonth(2);
        System.out.println(temp);
        // Prints - Wed Mar 10 00:12:00 IST 1993

        System.out.println(employee.getDob());
        // Prints - Sun Jan 10 00:12:00 IST 1993
        System.out.println(employee);
        // Prints - ImmutableEmployee{id=1, name='Naresh', dob=Sun Jan 10 00:12:00 IST 1993}

        // Let's get class and use reflection to modify private fields...
        Class<?> muttableClass = employee.getClass();

        System.out.println("Setting the immutable field int...");
        Field fi = muttableClass.getDeclaredField("id");
        fi.setAccessible(true);
        fi.set(employee, 12345);

        System.out.println("Setting the immutable field String...");
        Field fd = muttableClass.getDeclaredField("name");
        fd.setAccessible(true);
        fd.set(employee, "Pankaj");


        // After Change
        System.out.println("\n---IMMUTABLE OBJECT AFTER---");
        System.out.println(employee);
        // ImmutableEmployee{id=12345, name='Pankaj', dob=Sun Jan 10 00:12:00 IST 1993}\

        // Using Reflection break immutability
        // If we not add below line in constructor
        //  System.setSecurityManager(new SecurityManager());

    }
}