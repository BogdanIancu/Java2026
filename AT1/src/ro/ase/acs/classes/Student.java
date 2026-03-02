package ro.ase.acs.classes;

import java.util.Arrays;

public class Student {
    public String name;
    protected float[] grades;
    private int age;
    private TuitionType tuition;

    public Student() {
        name = "Anonymous";
        grades = new float[1];
        grades[0] = 8;
        age = 20;
    }

    public Student(String name, float[] grades, int age) {
        this.name = name;
        if(grades != null) {
            this.grades = new float[grades.length];
            for(int i = 0; i < grades.length; i++) {
                this.grades[i] = grades[i];
            }
        }
        this.age = age;
    }

    public float[] getGrades() {
        if(grades != null) {
            return Arrays.copyOf(grades, grades.length);
        }
        return null;
    }

    public void setGrades(float[] grades) {
        if(grades != null) {
            this.grades = new float[grades.length];
            System.arraycopy(grades, 0, this.grades, 0, grades.length);
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TuitionType getTuition() {
        return tuition;
    }

    public void setTuition(TuitionType tuition) {
        this.tuition = tuition;
    }

    @Override
    public Object clone() {
        Student copy = new Student();
        copy.name = this.name;
        if(this.grades != null) {
            copy.grades = this.grades.clone();
        }
        else {
            copy.grades = null;
        }
        copy.age = this.age;
        copy.tuition = this.tuition;
        return copy;
    }
}
