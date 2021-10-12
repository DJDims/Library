import java.util.Arrays;

public class Book{
    public String title;
    public Author[] authors;

    public int publishYear;

    public Book(String title, Author[] authors, int publishYear) {
        this.title = title;
        this.authors = authors;
        this.publishYear = publishYear;
    }

    public String getBookTitle(){
        return this.title;
    }

    public Author[] getBookAuthors(){
        return this.authors;
    }

    public int getBookPublishYear(){
        return this.publishYear;
    }

    public void setBookTitle(String title){
        this.title = title;
    }

    public void setBookAuthors(Author[] authors){
        this.authors = authors;
    }

    public void setBookPublishYear(int publishYear){
        this.publishYear = publishYear;
    }

    // @Override
    public String toString(){
        return "Название книги \"" + title + "\" \nАвтор: " + Arrays.toString(authors) + "\n Год публикации " + publishYear;
    }
}