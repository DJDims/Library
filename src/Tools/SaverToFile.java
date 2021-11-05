
package Tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Classes.Book;
import Classes.History;
import Classes.Reader;
import Ui.Keeping;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SaverToFile implements Keeping {

    @Override
    public void saveReaders(List<Reader> readersArray) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream("data/Readers");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(readersArray);
            oos.flush();
        } catch (FileNotFoundException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.INFO, "", ex);
        } catch (IOException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.SEVERE, "", ex);
        }
    }

    @Override
    public List<Reader> loadReaders() {
        List<Reader> readersArray = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream("data/Readers");
            ois = new ObjectInputStream(fis);
            readersArray = (List<Reader>) ois.readObject();
        } catch (FileNotFoundException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.INFO, "", ex);
        } catch (IOException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.SEVERE, "", ex);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.INFO, "", ex);
        }
        return readersArray;
    }

    @Override
    public void saveHistorys(List<History> historysArray) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream("data/Historys");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(historysArray);
            oos.flush();
        } catch (FileNotFoundException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.INFO, "", ex);
        } catch (IOException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.SEVERE, "", ex);
        }
    }

    @Override
    public List<History> loadHistorys() {
        List<History> historysArray = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream("data/Historys");
            ois = new ObjectInputStream(fis);
            historysArray = (List<History>) ois.readObject();
        } catch (FileNotFoundException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.INFO, "", ex);
        } catch (IOException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.SEVERE, "", ex);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.INFO, "", ex);
        }
        return historysArray;
    }

    @Override
    public void saveBooks(List<Book> booksArray) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream("data/Books");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(booksArray);
            oos.flush();
        } catch (FileNotFoundException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.INFO, "", ex);
        } catch (IOException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.SEVERE, "", ex);
        }
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> booksArray = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream("data/Books");
            ois = new ObjectInputStream(fis);
            booksArray = (List<Book>) ois.readObject();
        } catch (FileNotFoundException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.INFO, "", ex);
        } catch (IOException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.SEVERE, "", ex);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(SaverToFile.class.getName()).log(Level.INFO, "", ex);
        }
        return booksArray;
    }
    
}
