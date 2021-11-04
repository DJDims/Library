
package Classes;

import java.io.Serializable;
import java.time.LocalDate;

public class History implements Serializable{
    public Reader reader;
    public Book book;
    public LocalDate issueDate;
    public LocalDate returnDate;

    public Reader getReader() {
        return reader;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "History{" + "reader=" + reader + ", book=" + book + ", issueDate=" + issueDate + ", returnDate=" + returnDate + '}';
    }
}
