
package ui;

import java.util.List;

import classes.Author;
import classes.Book;
import classes.History;
import classes.Reader;

public interface Keeping {
    public void saveReaders(List <Reader> readersArray);
    public List<Reader> loadReaders();

    public void saveHistorys(List <History> historysArray);
    public List<History> loadHistorys();

    public void saveBooks(List <Book> booksArray);
    public List<Book> loadBooks();

    public void saveAuthors(List <Author> authorsArray);
    public List<Author> loadAuthors();
}
