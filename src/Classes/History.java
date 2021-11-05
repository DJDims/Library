
package Classes;

import java.io.Serializable;
import java.time.LocalDate;

public class History implements Serializable{
    public Reader reader;
    public Book book;
    public LocalDate issueDate;
    public LocalDate returnDate;
    public boolean expired;

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
    
    public void toogleExpired(){
        this.expired = !this.expired;
    }
    
    public boolean getExpired(){
        return expired;
    }

    @Override
    public String toString() {
        if (!expired) {
            return "History{" + "reader=" + reader + ", book=" + book + ", issueDate=" + issueDate + ", returnDate=" + returnDate + '}';
        } else {
            return "History{" + "reader=" + reader + ", book=" + book + ", issueDate=" + issueDate + ", returnDate=" + returnDate + ", \u001b[31mСрочно вернуть книгу!" +'}';
        }
    }
}
