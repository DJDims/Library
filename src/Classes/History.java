
package Classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class History implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    public Reader reader;
    
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    public Book book;
    
    @Temporal(TemporalType.TIMESTAMP)
    public Date issueDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    public Date returnDate;
    
    public boolean expired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
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
