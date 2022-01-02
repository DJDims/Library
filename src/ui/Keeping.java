
package ui;

import java.util.List;

import entitys.Author;
import entitys.Book;
import entitys.History;
import entitys.Reader;

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
