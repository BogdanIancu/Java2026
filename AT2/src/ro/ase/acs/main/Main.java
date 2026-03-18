package ro.ase.acs.main;

import ro.ase.acs.classes.Student;
import ro.ase.acs.classes.TuitionType;
import ro.ase.acs.interfaces.Learnable;

import java.io.*;
import java.util.*;

public class Main {
    public static void interchange(String x, String y)
    {
        String aux = x;
        x = y;
        y = aux;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Student s = new Student();
        System.out.println(s.name);
        s.setGrades(new float[] { 8, 9.5f, 10 });

        Student s2 = null;
        try {
            s2 = (Student)s.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        s2.name = "George";
        s2.setAge(19);
        System.out.println(s.name);

        s.setTuition(TuitionType.BUDGET);
        s2.setTuition(TuitionType.TAX);

        s2.display();

        Learnable learnable = s2;
        System.out.println(learnable.getNumberOfLearningHours());
        System.out.println();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);

        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }

        list.remove(Integer.valueOf(1));

        System.out.println();
        for(int x : list)
        {
            System.out.println(x);
        }

        Set<Student> set = new TreeSet<>();
        set.add(s);
        set.add(s2);

        for(Iterator<Student> it = set.iterator(); it.hasNext();)
        {
            System.out.println(it.next());
        }

        System.out.println();
        set = new HashSet<>();
        set.add(s);
        Student s3 = null;
        try {
            s3 = (Student)s2.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        set.add(s2);
        set.add(s3);
        System.out.println(set);

        Map<String, Long> phones = new HashMap<>();
        phones.put("Ionel", 40745123478L);
        phones.put("Gigel", 40727167655L);

        System.out.println(phones.get("Ionel"));

        for(Map.Entry<String, Long> entry : phones.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            System.out.println(entry.getValue());
        }

        String x = "ceva";
        String y = "altceva";
        interchange(x, y);
        System.out.println("x = " + x + " y = " + y);

        try {
            FileOutputStream file = new FileOutputStream("something.txt");
            OutputStreamWriter writer = new OutputStreamWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("first line");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("something.txt closed");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("First value: ");
        int a = scanner.nextInt();
        System.out.println("Second value: ");
        int b = scanner.nextInt();
        System.out.println("Sum = " + (a + b));

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Student.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(s);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
