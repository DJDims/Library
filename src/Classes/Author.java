package Classes;

public class Author{
    public String name;
    public String surename;
    public int bornYear;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public int getBornYear() {
        return bornYear;
    }

    public void setBornYear(int bornYear) {
        this.bornYear = bornYear;
    }

    @Override
    public String toString() {
        return "Author{" + "name=" + name + ", surename=" + surename + ", bornYear=" + bornYear + '}';
    }
}