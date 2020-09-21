package com.pankaj.interview.immutable;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

// 1. Declare your class as final, So other classes can't extend it and break its immutability
final class MyImmutableClass {

    // 2. Make all your fields private they can't be accessed outside your class
    // 3. Mark them as final, so no one can modify them anywhere else apart from the constructor,
    // if you do not have any specific requirement to not do so
    private final int id;
    private final String name;
    private final Date dob;
    private ArrayList<String> list;

    // 4. Create an constructor with argument so you can assign instantiate your object with a proper state
    public MyImmutableClass(int id, String name, Date dob, ArrayList<String> list) {
        this.id = id;
        this.name = name;
        // 5. Initialise all your fields by deeply copying them if they are not immutable in nature
        this.dob = new Date(dob.getTime());

        this.list = (ArrayList<String>) list.clone();

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

    public ArrayList<String> getList() {
        return (ArrayList<String>) list.clone();
    }

    @Override
    public String toString() {
        return "MyImmutableClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", list=" + list +
                '}';
    }
}

public class ImmutableClassExample {


    public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, ParseException {
        Date dob = new SimpleDateFormat("dd-mm-yyyy").parse("10-12-1993");
        ArrayList<String> list =new ArrayList<>();
        list.add("abc");
        list.add("xyz");
        MyImmutableClass employee = new MyImmutableClass(1, "Naresh", dob,list);
        // even "pqr" is added in list its not added to actual employee object.
        employee.getList().add("pqr");

        System.out.println(employee);
        // MyImmutableClass{id=1, name='Naresh', dob=Sun Jan 10 00:12:00 IST 1993, list=[abc, xyz]}



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
        // MyImmutableClass{id=1, name='Naresh', dob=Sun Jan 10 00:12:00 IST 1993, list=[abc, xyz]}

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
        // MyImmutableClass{id=1, name='Naresh', dob=Sun Jan 10 00:12:00 IST 1993, list=[abc, xyz]}

        // Using Reflection break immutability
        // If we not add below line in constructor
        //  System.setSecurityManager(new SecurityManager());

    }
}