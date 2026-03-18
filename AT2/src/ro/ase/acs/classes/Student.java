package ro.ase.acs.classes;

import ro.ase.acs.interfaces.Learnable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public final class Student extends Person implements Learnable, Serializable, Comparable<Student> {
    private static final long serialVersionUID = 2L;
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

    public Student(String personalId, String name, float[] grades, int age) {
        super(personalId);
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
    public final void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", grades=").append(Arrays.toString(grades));
        sb.append(", age=").append(age);
        sb.append(", tuition=").append(tuition);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Student copy = (Student) super.clone();
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

    @Override
    public int getNumberOfLearningHours() {
        return 4;
        //throw new RuntimeException("something");
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.age, o.age);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
