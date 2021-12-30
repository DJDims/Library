package Library;

import classes.Author;
import classes.Book;
import classes.History;
import classes.Reader;
import facade.AuthorFacade;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class App {
    private Scanner scanner = new Scanner(System.in);
    private AuthorFacade authorsFacade;
    private BookFacade booksFacade;
    private HistoryFacade historysFacade;
    private ReaderFacade readersFacade;

    public App() {
        init();
    }
    
    private void init(){
        authorsFacade = new AuthorFacade(Author.class);
        booksFacade = new BookFacade(Book.class);
        historysFacade = new HistoryFacade(History.class);
        readersFacade = new ReaderFacade(Reader.class);
    }
        
    public void run(){
        boolean appRunnign = true; 
        checkExpiredBooks();
        int task;

        while (appRunnign) {
            showHints();
            System.out.print("Опция --> ");
            task = inputInt();
            
            switch (task){
                case 0:
                    //выход
                    System.out.println("Досвидания. Приходите еще!");
                    appRunnign = false;
                    break;

                case 1:
                    //Добавить читателя
                    addReader();
                    break;

                case 2:
                    //Вывести список читателей
                    showReaders();
                    break;

                case 3:
                    //Добавить автора
                    addAuthor();
                    break;
                    
                case 4:
                    //Вывести список авторов
                    showAuthors();
                    break;
                    
                case 5:
                    //Добавить книгу
                    addBook();
                    break;

                case 6:
                    //Вывести список книг
                    showBooks();
                    break;
                    
                case 7:
                    //Взять книгу
                    addHistory();
                    break;
                    
                case 8:
                    //Продлить книгу
                    extendReturnDate();
                    break;
                    
                case 9:
                    returnBook();
                    break;
                    
                case 10:
                    //Вывести список взятых книг
                    showTakedBooks();
                    break;
                    
                case 11:
                    //Изменить товар
                    changeBook();
                    break;
                    
                case 12:
                    //Изменить пользователя
                    changeReader();
                    break;
                    
                case 13:
                    //Изменить автора
                    changeAuthor();
                    break;
                    
                default:
                    System.out.println("Введена неверная опция");
                    break;
            }
        } 
    }
    
    private void showHints(){
        System.out.println("");
        System.out.println("Выберите опцию");
        System.out.println("0) Выход");
        System.out.println("1) Добавить читателя");
        System.out.println("2) Вывести список читателей");
        System.out.println("3) Добавить автора");
        System.out.println("4) Вывести список авторов");
        System.out.println("5) Добавить книгу");
        System.out.println("6) Вывести список книг");
        System.out.println("7) Взять книгу");
        System.out.println("8) Продлить книгу");
        System.out.println("9) Вернуть книгу");
        System.out.println("10) Вывести список взятых книг");
        System.out.println("11) Изменить книгу");
        System.out.println("12) Изменить читателя");
        System.out.println("13) Изменить автора");
    }
    
    private void addReader(){
        Reader reader = new Reader();
        
        System.out.print("Имя читателя: ");
        reader.setFirstname(scanner.nextLine());
                
        System.out.print("Фамилия читателя: ");
        reader.setSurename(scanner.nextLine());
                
        System.out.print("Телефон читателя: ");
        reader.setPhoneNumber(scanner.nextLine());
        
        readersFacade.create(reader);
    }
    
    private Set<Integer> showReaders(){
        Set<Integer> numbersOfReaders = new HashSet<>();
        List<Reader> readersArray = readersFacade.findAll();
        if (!readersArray.isEmpty()) {
            System.out.println("----- Список читателей -----");
            for (int i = 0; i < readersArray.size(); i++) {
                System.out.println(i+1 + ") " + readersArray.get(i).toString());
                numbersOfReaders.add(i+1);
            }
            System.out.println("----- Список читателей -----");
        } else {
            System.out.println("Нет добавленных читателей");
        }
        return numbersOfReaders;
    }
    
    private void addAuthor(){
        Author author = new Author();
        
        System.out.print("Имя автора: ");
        author.setName(scanner.nextLine());
        System.out.print("Фамилия автора: ");
        author.setSurename(scanner.nextLine());
        System.out.print("Год рождения: ");
        author.setBornYear(inputInt());
        
        authorsFacade.create(author);
    }
        
    private Set<Integer> showAuthors(){
        Set<Integer> numbersOfAuthors = new HashSet<>();
        List<Author> authorsArray = authorsFacade.findAll();
        if (!authorsArray.isEmpty()) {
            System.out.println("----- Список авторов -----");
            for (int i = 0; i < authorsArray.size(); i++) {
                System.out.println(i+1 + ") " + authorsArray.get(i).toString());
                numbersOfAuthors.add(i+1);
            }
            System.out.println("----- Список авторов -----");
        } else {
            System.out.println("Нет добавленных авторов");
        }
        return numbersOfAuthors;
    }
    
    private void addBook(){
        List<Author> authorsArray = authorsFacade.findAll();
        if (authorsArray.isEmpty()) {
            System.out.println("Операция невозможна");
            return;
        }
        
        Book book = new Book();
        List<Author> bookAuthors = new ArrayList<>();
        int countOfAuthors;
        int authorNumber;

        System.out.print("Название книги: ");
        book.setTitle(scanner.nextLine());
        System.out.print("Количество авторов: ");
        countOfAuthors = inputInt();

        for (int i = 0; i < countOfAuthors; i++) {
            showAuthors();
            System.out.println("Введите номер автора. Если автора нет в списке введите 0");
            authorNumber = inputInt();
            if (authorNumber == 0) {
                System.out.println("Для начала добавле автора!");
                return;
            } else {
                bookAuthors.add(authorsArray.get(authorNumber-1));
                book.setAuthors(bookAuthors);
            }
        }

        System.out.print("Год публикации книги: ");
        book.setPublishYear(inputInt());
        System.out.print("Количество книг: ");
        book.setCount(inputInt());

        booksFacade.create(book);
    }
    
    private Set<Integer> showBooks(){
        Set<Integer> numbersOfBooks = new HashSet<>();
        List<Book> booksArray = booksFacade.findAll();
        if (!booksArray.isEmpty()) {
            System.out.println("----- Список книг -----");
            for (int i = 0; i < booksArray.size(); i++) {
                if (booksArray.get(i).getCount() > 0) {
                    System.out.println(i+1 + ") " + booksArray.get(i).toString());
                }
            }
            System.out.println("----- Список книг -----");
        } else {
            System.out.println("Нет добавленных книг");
        }
        return null;
    }
    
    private void addHistory(){
        List<Book> booksArray = booksFacade.findAll();
        List<Reader> readersArray = readersFacade.findAll();
        
        if (booksArray.isEmpty() && readersArray.isEmpty() && countBooks() == 0) {
            System.out.println("Операция невозможна");
            return;
        }
        
        History history = new History();

        System.out.println("---------- Взять книгу ----------");

        System.out.println("----- Список читателей -----");
        for (int i = 0; i < readersArray.size(); i++) {
            System.out.println(i+1 + ") " + readersArray.get(i).getFirstname());
        }
        System.out.println("----- Список читателей -----");
        System.out.print("Введите номер читателя: ");
        history.setReader(readersArray.get(inputInt()-1));

        System.out.println("----- Список книг -----");
        for (int i = 0; i < booksArray.size(); i++) {
            System.out.println(i+1 + ") " + booksArray.get(i).getTitle());
        }
        System.out.println("----- Список книг -----");
        System.out.print("Введите номер книги: ");
        history.setBook(booksArray.get(inputInt()-1));

        history.getBook().takeBook();

        history.setIssueDate(localdateToDate(LocalDate.now()));
        history.setReturnDate(localdateToDate(LocalDate.now().plusWeeks(2)));

        System.out.println("---------- Взять книгу ----------");

        historysFacade.edit(history);
    }
    
    private void extendReturnDate(){
        List<History> historysArray = historysFacade.findAll();
        if (showTakedBooks()) {
            System.out.print("Выберите книгу, которую хотите продлить: ");
            int numberOfBookToExtend = inputInt();
            LocalDate newDate = dateToLocaldate(historysArray.get(numberOfBookToExtend-1).getReturnDate());
            newDate = newDate.plusWeeks(2);
            historysArray.get(numberOfBookToExtend-1).setReturnDate(localdateToDate(newDate));
        }
    }
    
    private void returnBook(){
        List<History> historysArray = historysFacade.findAll();
        if (showTakedBooks()) {
            System.out.print("Введите номер возвращаемой книги: ");
            int numberOfBookToReturn = inputInt();
            if (numberOfBookToReturn <= historysArray.size()) {
                History historyToDelete = historysFacade.findById((long)numberOfBookToReturn-1);
                historysArray.remove(numberOfBookToReturn-1);
                historysFacade.edit(historyToDelete);
                System.out.println("Книга возвращена");
            }
	}
    }
    
    private boolean showTakedBooks(){
        boolean flag = false;
        List<History> historysArray = historysFacade.findAll();

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
    
    private void changeBook() {
        List<Book> booksArray = booksFacade.findAll();
        if (booksArray.isEmpty()) {
            System.out.println("Нет книг");
            return;
        }
        
        System.out.println("----- Список книг -----");
        for (int i = 0; i < booksArray.size(); i++) {
            System.out.println(i+1 + ") " + booksArray.get(i).getTitle());
        }
        System.out.println("----- Список книг -----");
        System.out.print("Введите номер книги: ");
        int numberOfBookToChange = inputInt();
        Book bookToChange = booksFacade.findById((long)numberOfBookToChange);

        System.out.println("Выберите что хотите изменить\n");
        System.out.println("1) Название\n"
                        + "2) Год публикации\n"
                        + "3) Количество");
        System.out.print("-->");
        int paramToChange = inputInt();

        switch(paramToChange){
            case 1:
                //изменить название
                System.out.print("Новое имя: ");
                String newName = scanner.nextLine();
                bookToChange.setTitle(newName);
                break;
            case 2:
                //изменить год публикации
                System.out.print("Новый год публикации: ");
                int newYear = inputInt();
                bookToChange.setPublishYear(newYear);
                break;
            case 3:
                //изменить количество
                System.out.print("Новое количество: ");
                int newCount = inputInt();
                bookToChange.setCount(newCount);
                break;
            default:
                System.out.println("Введена неверная опция");
                break;
        }
        booksFacade.edit(bookToChange);
    }
    
    private void changeReader() {
        List<Reader> readersArray = readersFacade.findAll();
        if (readersArray.isEmpty()) {
            System.out.println("Нет читателей");
            return;
            
        }
        System.out.println("----- Список читателей -----");
        for (int i = 0; i < readersArray.size(); i++) {
            System.out.println(i+1 + ") " + readersArray.get(i).getFirstname());
        }
        System.out.println("----- Список читателей -----");

        System.out.print("Введите номер читателя: ");
        int numberOfReaderToChange = inputInt();
        Reader readerToChange = readersFacade.findById((long)numberOfReaderToChange);

        System.out.println("Выберите что хотите изменить");
        System.out.println("1) Имя\n"
                        + "2) Фамилию\n"
                        + "3) Номер");
        System.out.print("-->");
        int paramToChange = inputInt();

        switch(paramToChange){
            case 1:
                //изменить название
                System.out.print("Новое имя: ");
                String newFristname = scanner.nextLine();
                readerToChange.setFirstname(newFristname);
                break;
            case 2:
                //изменить год публикации
                System.out.print("Новая фамилия: ");
                String newSurename = scanner.nextLine();
                readerToChange.setSurename(newSurename);
                break;
            case 3:
                //изменить количество
                System.out.print("Новый номер: ");
                String newPhone = scanner.nextLine();
                readerToChange.setPhoneNumber(newPhone);
                break;
            default:
                System.out.println("Введена неверная опция");
                break;
        }
        readersFacade.edit(readerToChange);
    }
    
    private void changeAuthor() {
        List<Author> authorsArray = authorsFacade.findAll();
        if (authorsArray.isEmpty()) {
            System.out.println("Нет авторов");
            return;
        }
        
        System.out.println("----- Список авторов -----");
        for (int i = 0; i < authorsArray.size(); i++) {
            System.out.println(i+1 + ") " + authorsArray.get(i).toString());
        }
        System.out.println("----- Список авторов -----");

        System.out.print("Введите номер автора: ");
        int numberOfAuthorToChange = inputInt();
        Author authorToChange = authorsFacade.findById((long)numberOfAuthorToChange);

        System.out.println("Выберите что хотите изменить");
        System.out.println("1) Имя\n"
                        + "2) Фамилию\n"
                        + "3) Год рождения");
        System.out.print("-->");
        int paramToChange = inputInt();

        switch(paramToChange){
            case 1:
                //изменить имя
                System.out.print("Введите новое имя: ");
                String newFristname = scanner.nextLine();
                authorToChange.setName(newFristname);
                break;
            case 2:
                //изменить фамилию
                System.out.print("Введите новую фамилию: ");
                String newSurename = scanner.nextLine();
                authorToChange.setSurename(newSurename);
                break;
            case 3:
                //изменить год рождения
                System.out.print("Введите новый год рождения");
                int newYear = inputInt();
                authorToChange.setBornYear(newYear);
                break;
            default:
                System.out.println("Введена неверная опция");
                break;
        }
        authorsFacade.edit(authorToChange);
    }
    
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    private int countBooks(){
        List<Book> booksArray = booksFacade.findAll();
        int count = 0;
        for (int i = 0; i < booksArray.size(); i++) {
            count += booksArray.get(i).getCount();
        }
        return count;
    }
    
    private void checkExpiredBooks(){
        List<History> historysArray = historysFacade.findAll();
        if (historysArray.isEmpty()) {
            return;
        }
        for (int i = 0; i < historysArray.size(); i++) {
            if (historysArray.get(i).getReturnDate().before(localdateToDate(LocalDate.now()))) {
                historysArray.get(i).setExpired();
            }
        }
    }
    
    private int inputInt(){
	do {
            try {
                String inputedNumber = scanner.nextLine();
                return Integer.parseInt(inputedNumber);
            } catch(Exception e) {
                System.out.println("Введены неверные данные.");
                System.out.print("Попробуйте еще раз-->");
            }
	} while(true);
    }
    
    private LocalDate dateToLocaldate(Date dateToConvert){
	return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private Date localdateToDate(LocalDate dateToConvert){
        return Date.from(dateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
