package ro.ase.acs.classes;

public abstract class Person implements Cloneable {
    protected static final int RETIREMENT_AGE = 65;
    protected String personalId;

    public Person() {
        personalId = "";
    }

    public Person(String personalId) {
        this.personalId = personalId;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Person copy = (Person) super.clone();
        copy.personalId = this.personalId;
        return copy;
    }

    public abstract void display();
}
