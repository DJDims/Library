package Classes;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ElementCollection(fetch = FetchType.LAZY)
    private Author[] authors;
    private int publishYear;
    private int count;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void takeBook(){
        this.count -= 1;
    }

    public void returnBook(){
        this.count += 1;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", authors=" + Arrays.toString(authors) + ", publishYear=" + publishYear + ", count=" + count + '}';
    }
}