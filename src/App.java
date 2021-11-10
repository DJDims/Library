
import Classes.Author;
import Classes.Book;
import Classes.History;
import Classes.Reader;
import Tools.SaverToFile;
import Interface.Keeping;
import Tools.SaverToBase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
    Scanner scanner = new Scanner(System.in);
//    Keeping keeping = new SaverToFile();
    Keeping keeping = new SaverToBase();
    
    List <Reader> readersArray = new ArrayList<>();
    List <Book> booksArray = new ArrayList<>();
    List <History> historysArray = new ArrayList<>();

    public App() {
//        readersArray = keeping.loadReaders();
        booksArray = keeping.loadBooks();
//        historysArray = keeping.loadHistorys();
        checkExpiredBooks();
    }
        
    public void run(){
        boolean appRunnign = true; 
        int task;

        showHints();
        
        while (appRunnign) {
            System.out.print("Опция --> ");
            task = scanner.nextInt();
            
            int booksSummaryCount = countBooks();

            switch (task){
                case 0:
                    //выход
                    System.out.println("Досвидания. Приходите еще!");
                    appRunnign = false;
                    break;

                case 1:
                    //Добавить читателя
                    readersArray.add(addReader());
                    keeping.saveReaders(readersArray);
                    break;

                case 2:
                    //Вывести список читателей
                    if (!readersArray.isEmpty()) {
                        System.out.println("----- Список читателей -----");
                        for (int i = 0; i < readersArray.size(); i++) {
                            System.out.println(i+1 + ") " + readersArray.get(i).toString());
                        }
                        System.out.println("----- Список читателей -----");
                    } else {
                        System.out.println("Нет добавленных читателей");
                    }
                    break;

                case 3:
                    //Добавить книгу
                       booksArray.add(addBook());
                       keeping.saveBooks(booksArray);
                    break;

                case 4:
                    //Вывести список книг
                    if (!booksArray.isEmpty()) {
                        System.out.println("----- Список книг -----");
                        for (int i = 0; i < booksArray.size(); i++) {
                            System.out.println(i+1 + ") " + booksArray.get(i).toString());
                        }
                        System.out.println("----- Список книг -----");
                    } else {
                        System.out.println("Нет добавленных книг");
                    }
                    break;
                    
                case 5:
                    //Взять книгу
                    if (!booksArray.isEmpty() && !readersArray.isEmpty() && booksSummaryCount > 0) {
                        historysArray.add(addHistory());
                        keeping.saveHistorys(historysArray);
                    } else {
                        System.out.println("Операция невозможна");
                    }
                    break;
                    
                case 6:
                    //Вернуть книгу
                    if (showTakedBooks()) {
                        int numberOfBookToReturn = scanner.nextInt();
                        if (numberOfBookToReturn <= historysArray.size()) {
                            historysArray.remove(numberOfBookToReturn-1);
                            System.out.println("Книга возвращена");
                        }
                    }
                    break;
                    
                case 7:
                    //Продлить книгу
                    if (showTakedBooks()) {
                        int numberOfBookToExtend = scanner.nextInt();
                        historysArray.get(numberOfBookToExtend-1).setReturnDate(historysArray.get(numberOfBookToExtend-1).getReturnDate().plusWeeks(2));
                    }
                    break;
                    
                case 8:
                    //Вывести список взятых книг
                    showTakedBooks();
                    break;
                    
                case 9:
                    //Вывести подсказки
                    showHints();
                    break;
                default:
                    System.out.println("Введена неверная опция");
                    break;
            }
        }
    }
    
    public void showHints(){
        System.out.println("");
        System.out.println("Выберите опцию");
        System.out.println("0) Выход");
        System.out.println("1) Добавить читателя");
        System.out.println("2) Вывести список читателей");
        System.out.println("3) Добавить книгу");
        System.out.println("4) Вывести список книг");
        System.out.println("5) Взять книгу");
        System.out.println("6) Вернуть книгу");
        System.out.println("7) Продлить книгу");
        System.out.println("8) Вывести список взятых книг");
        System.out.println("9) Вывести подсказки");
        System.out.println("");
    }
    
    public Author addAuthor(){
        Author author = new Author();
        
        System.out.println("");
        System.out.print("Имя автора: ");
        author.setName(scanner.next());
        
        System.out.print("Фамилия автора: ");
        author.setSurename(scanner.next());
        
        System.out.print("Год рождения:");
        author.setBornYear(scanner.nextInt());
        System.out.println("");
        
        return author;
    }
    
    public Book addBook(){
        Book book = new Book();
        int countOfAuthors;
        
        System.out.println("");
        System.out.print("Название книги: ");
        book.setTitle(scanner.next());
        
        System.out.print("Количество авторов: ");
        countOfAuthors = scanner.nextInt();

        List<Author> authorsArray = new ArrayList<>();
        for (int i = 0; i < countOfAuthors; i++) {
            authorsArray.add(addAuthor());
        }
        
        book.setAuthors(authorsArray);
        
        System.out.print("Год публикации книги: ");
        book.setPublishYear(scanner.nextInt());
        
        System.out.print("Количество книг: ");
        book.setCount(scanner.nextInt());
        System.out.println("");
        
        return book;
    }
    
    public History addHistory(){
        History history = new History();
        
        System.out.println("---------- Взять книгу ----------");
        //--------------- Выбор читателя ---------------
        System.out.println("----- Список читателей -----");
        for (int i = 0; i < readersArray.size(); i++) {
            System.out.println(i+1 + ") " + readersArray.get(i).getFirstname());
        }
        System.out.println("----- Список читателей -----");
        System.out.print("Введите номер читателя: ");

        history.setReader(readersArray.get(scanner.nextInt()-1));
        //--------------- Выбор читателя ---------------

        //--------------- Выбор книги ---------------
        System.out.println("----- Список книг -----");
        for (int i = 0; i < booksArray.size(); i++) {
            System.out.println(i+1 + ") " + booksArray.get(i).getTitle());
        }
        System.out.println("----- Список книг -----");
        System.out.print("Введите номер книги: ");
        history.setBook(booksArray.get(scanner.nextInt()-1));
        history.book.takeBook();
        //--------------- Выбор книги ---------------

        //--------------- Установка дат ---------------
        history.setIssueDate(LocalDate.now());
        history.setReturnDate(LocalDate.now().plusWeeks(2));
        //--------------- Установка дат ---------------

        System.out.println("---------- Взять книгу ----------");
        
        return history;
    }
    
    public Reader addReader(){
        Reader reader = new Reader();
        
        System.out.println("");
        System.out.print("Имя читателя: ");
        reader.setFirstname(scanner.next());
                
        System.out.print("Фамилия читателя: ");
        reader.setSurename(scanner.next());
                
        System.out.print("Телефон читателя: ");
        reader.setPhoneNumber(scanner.next());
        System.out.println("");
        
        return reader;
    }

    public int countBooks(){
        int count = 0;
        for (int i = 0; i < booksArray.size(); i++) {
            count += booksArray.get(i).getCount();
        }
        return count;
    }

    public boolean showTakedBooks(){
        boolean flag = false;

        if (!historysArray.isEmpty()) {
            System.out.println("----- Список взятых книг -----");
            for (int i = 0; i < historysArray.size(); i++) {
                System.out.println(i+1 + ") " + historysArray.get(i).toString());
            }
            System.out.println("----- Список взятых книг -----");
            flag = true;
        } else {
            System.out.println("Нет взятых книг");
        }
        return flag;
    }
    
    public void checkExpiredBooks(){
        for (int i = 0; i < historysArray.size(); i++) {
            if (historysArray.get(i).getReturnDate().isAfter(LocalDate.now())) {
                historysArray.get(i).toogleExpired();
            }
        }
    }
}
