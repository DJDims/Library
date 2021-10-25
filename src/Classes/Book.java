package Classes;

import java.util.Arrays;

public class Book{
    public String title;
    public Author[] authors;
    public int publishYear;
    public int count;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", authors=" + authors + ", publishYear=" + publishYear + ", count=" + count + '}';
    }
}