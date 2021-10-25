
package Classes;

import java.time.LocalDate;

public class History {
    public Reader reader;
    public Book book;
    public LocalDate issueDate;
    public LocalDate returnDate;

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
    
    
}
