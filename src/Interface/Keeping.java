
package Interface;

import java.util.List;

import Classes.Book;
import Classes.History;
import Classes.Reader;

public interface Keeping {
    public void saveReaders(List <Reader> readersArray);
    public List<Reader> loadReaders();

    public void saveHistorys(List <History> historysArray);
    public List<History> loadHistorys();

    public void saveBooks(List <Book> booksArray);
    public List<Book> loadBooks();

}
