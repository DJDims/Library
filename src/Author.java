public class Author{
    public String name;
    public String surename;

    public int bornYear;

    public Author(String name, String surename, int bornYear) {
        this.name = name;
        this.surename = surename;
        this.bornYear = bornYear;
    }

    public String getAuthorName(){
        return this.name;
    }

    public String getAuthorSurename(){
        return this.surename;
    }

    public int getAuthorBornYear(){
        return this.bornYear;
    }

    public void setAuthorName(String name){
        this.name = name;
    }

    public void setAuthorSurename(String surename){
        this.surename = surename;
    }

    public void setAuthorBornYear(int bornYear){
        this.bornYear = bornYear;
    }

    // @Override
    public String toString() {
        return "Имя и фамилия автора " + name + surename + "\nГод рождения " + bornYear;
    }
}