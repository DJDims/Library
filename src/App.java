
import Classes.Author;
import Classes.Book;
import Classes.History;
import Classes.Reader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class App {
    Scanner scanner = new Scanner(System.in);
    
    Book[] booksArray = new Book[10];
    Reader[] readersArray = new Reader[10];
    List <History> historysArray = new ArrayList<>();
        
    public void run(){
        boolean appRunnign = true; 
        int task;
        
        showHints();
        
        while (appRunnign) {
            System.out.print("Опция --> ");
            task = scanner.nextInt();
            
            int countOfReaders = lastReader();
//            int countOfHistorys = lastHistory();
            int countOfBooks = lastBook();
            int booksSummaryCount = countBooks();

            switch (task){
                case 0:
                    //выход
                    System.out.println("Досвидания. Приходите еще!");
                    appRunnign = false;
                    break;

                case 1:
                    //Добавить читателя
                    if (countOfReaders < 10) {
                        readersArray[countOfReaders] = addReader();
                    } else {
                        System.out.println("Максимальное количество читателей");
                    }
                    break;

                case 2:
                    //Вывести список книг
                    if (countOfReaders > 0) {
                        System.out.println("----- Список читателей -----");
                        for (int i = 0; i < countOfReaders; i++) {
                            System.out.println(i+1 + ") " + readersArray[i].toString());
                        }
                        System.out.println("----- Список читателей -----");
                    } else {
                        System.out.println("Нет добавленных читателей");
                    }
                    break;

                case 3:
                    //Добавить книгу
                   if (countOfBooks < 10) {
                       booksArray[countOfBooks] = addBook();
                   } else {
                       System.out.println("Максимально количество книг");
                   }
                    break;

                case 4:
                    //Вывести список книг
                    if (countOfBooks > 0) {
                        System.out.println("----- Список книг -----");
                        for (int i = 0; i < countOfBooks; i++) {
                            System.out.println(i+1 + ") " + booksArray[i].toString());
                        }
                        System.out.println("----- Список книг -----");
                    } else {
                        System.out.println("Нет добавленных книг");
                    }
                    break;
                case 5:
                    //Взять книгу
                    if (historysArray.isEmpty() && countOfBooks > 0 && countOfReaders > 0 && booksSummaryCount > 0) {
                        historysArray.add(addHistory());
                    } else {
                        System.out.println("Операция невозможна");
                    }
                    break;
                case 6:
                    //Вернуть книгу
//                    if (showTakedBooks()) {
//                        int numberOfBookToReturn = scanner.nextInt();
//                        for (int i = 0; i < booksArray.length; i++) {
//                            Book booksArray1 = booksArray[i];
//                            
//                        }
//                    }
                    if (showTakedBooks()) {
                        int numberOfBookToReturn = scanner.nextInt();
                        if (numberOfBookToReturn <= historysArray.size()) {
                            historysArray.remove(numberOfBookToReturn-1);
                        }
                    }
                    break;
                case 7:
                    //Продлить книгу
                    showTakedBooks();
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

        Author[] authorsArray = new Author[countOfAuthors];
        for (int i = 0; i < authorsArray.length; i++) {
            authorsArray[i] = addAuthor();
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
        int countOfReaders = lastReader();
        int countOfBooks = lastBook();
        
        System.out.println("");
        //--------------- Выбор читателя ---------------
        System.out.println("----- Список читателей -----");
        for (int i = 0; i < countOfReaders; i++) {
            System.out.println(i+1 + ") " + readersArray[i].getFirstname());
        }
        System.out.println("----- Список читателей -----");
        System.out.print("Введите номер читателя: ");

        history.setReader(readersArray[scanner.nextInt()-1]);
        //--------------- Выбор читателя ---------------

        //--------------- Выбор книги ---------------
        System.out.println("----- Список книг -----");
        for (int i = 0; i < countOfBooks; i++) {
            System.out.println(i+1 + ") " + booksArray[i].getTitle());
        }
        System.out.println("----- Список книг -----");
        System.out.print("Введите номер книги: ");
        history.setBook(booksArray[scanner.nextInt()-1]);
        //--------------- Выбор книги ---------------

        //--------------- Установка дат ---------------
        history.issueDate = LocalDate.now();
        history.returnDate = history.issueDate.plusWeeks(2);
        //--------------- Установка дат ---------------

        history.book.takeBook();

        System.out.println("");
        
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
    
    public int lastBook(){
        int lastElement = 0;
        boolean full = false;
        for (int i = 0; i < booksArray.length; i++) {
            if (booksArray[i] == null){
                lastElement = i;
                full = true;
                break;
            }
        }
        if (!full && booksArray[0] != null) {
            lastElement = 10;
        }
        return lastElement;
    }
    
//    public int lastHistory(){
//        int lastElement = 0;
//        boolean full = false;
//        for (int i = 0; i < historysArray.size(); i++) {
//            if (historysArray.get(i) == null){
//                lastElement = i;
//                full = true;
//                break;
//            }
//        }
//        if (!full && historysArray.get(0) != null) {
//            lastElement = 10;
//        }
//        return lastElement;
//    }
    
    public int lastReader(){
        int lastElement = 0;
        boolean full = false;
        for (int i = 0; i < readersArray.length; i++) {
            if (readersArray[i] == null){
                lastElement = i;
                full = true;
                break;
            }
        }
        if (!full && readersArray[0] != null) {
            lastElement = 10;
        }
        return lastElement;
    }

    public int countBooks(){
        int count = 0;
        int countOfBooks = lastBook();
        for (int i = 0; i < countOfBooks; i++) {
            count += booksArray[i].getCount();
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

}
