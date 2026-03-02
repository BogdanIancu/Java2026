package ro.ase.acs.main;

import ro.ase.acs.classes.Student;
import ro.ase.acs.classes.TuitionType;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Student s = new Student();
        System.out.println(s.name);
        s.setGrades(new float[] { 8, 9, 10 });

        Student s2 = (Student)s.clone();
        s2.name = "George";
        System.out.println(s.name);

        s.setTuition(TuitionType.BUDGET);
        s2.setTuition(TuitionType.TAX);
    }
}
