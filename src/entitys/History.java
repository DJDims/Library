
package entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Reader reader;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Book book;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnedDate;
    
    private boolean expired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
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

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public void setExpired(){
        this.expired = true;
    }
    
    public boolean getExpired(){
        return expired;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("History{")
                .append("id=")
                .append(id)
                .append(", reader=")
                .append(reader)
                .append(", book=")
                .append(book)
                .append(", issueDate=")
                .append(issueDate)
                .append(", returnDate=")
                .append(returnDate)
                .append(", returnedDate=")
                .append(returnedDate)
                .append(", expired=")
                .append(expired)
                .append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.reader);
        hash = 13 * hash + Objects.hashCode(this.book);
        hash = 13 * hash + Objects.hashCode(this.issueDate);
        hash = 13 * hash + Objects.hashCode(this.returnDate);
        hash = 13 * hash + Objects.hashCode(this.returnedDate);
        hash = 13 * hash + (this.expired ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final History other = (History) obj;
        if (this.expired != other.expired) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.reader, other.reader)) {
            return false;
        }
        if (!Objects.equals(this.book, other.book)) {
            return false;
        }
        if (!Objects.equals(this.issueDate, other.issueDate)) {
            return false;
        }
        if (!Objects.equals(this.returnDate, other.returnDate)) {
            return false;
        }
        if (!Objects.equals(this.returnedDate, other.returnedDate)) {
            return false;
        }
        return true;
    }

}
