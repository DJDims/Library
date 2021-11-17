package Library;


import Classes.Author;
import Classes.Book;
import Classes.History;
import Classes.Reader;
import Tools.SaverToFile;
import Interface.Keeping;
import Tools.SaverToBase;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class App {
    Scanner scanner = new Scanner(System.in);
    Keeping keeping = new SaverToFile();
//    Keeping keeping = new SaverToBase();
    
    List <Reader> readersArray = new ArrayList<>();
    List <Book> booksArray = new ArrayList<>();
    List <History> historysArray = new ArrayList<>();
    List <Author> authorsArray = new ArrayList<>();

    public App() {
        readersArray = keeping.loadReaders();
        booksArray = keeping.loadBooks();
        historysArray = keeping.loadHistorys();
        authorsArray = keeping.loadAuthors();
        checkExpiredBooks();
    }
        
    public void run(){
        boolean appRunnign = true; 
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
        System.out.println("12) Изменить пользователя");
    }
    
    private void addReader(){
        Reader reader = new Reader();
        
        System.out.print("Имя читателя: ");
        reader.setFirstname(scanner.next());
                
        System.out.print("Фамилия читателя: ");
        reader.setSurename(scanner.next());
                
        System.out.print("Телефон читателя: ");
        reader.setPhoneNumber(scanner.next());
        
        readersArray.add(reader);
        keeping.saveReaders(readersArray);
    }
    
    private void showReaders(){
        if (!readersArray.isEmpty()) {
            System.out.println("----- Список читателей -----");
            for (int i = 0; i < readersArray.size(); i++) {
                    System.out.println(i+1 + ") " + readersArray.get(i).toString());
            }
            System.out.println("----- Список читателей -----");
        } else {
            System.out.println("Нет добавленных читателей");
        }
    }
    
    private void addAuthor(){
        Author author = new Author();
        
        System.out.print("Имя автора: ");
        author.setName(scanner.next());
        System.out.print("Фамилия автора: ");
        author.setSurename(scanner.next());
        System.out.print("Год рождения: ");
        author.setBornYear(inputInt());
        
        authorsArray.add(author);
        keeping.saveAuthors(authorsArray);
    }
    
    private void showAuthors(){
        if (!authorsArray.isEmpty()) {
            System.out.println("----- Список авторов -----");
            for (int i = 0; i < authorsArray.size(); i++) {
                System.out.println(i+1 + ") " + authorsArray.get(i).toString());
            }
            System.out.println("----- Список авторов -----");
        } else {
            System.out.println("Нет добавленных авторов");
        }
    }
    
    private void addBook(){
        if (!authorsArray.isEmpty()) {
            Book book = new Book();
            List<Author> bookAuthors = new ArrayList<>();
            int countOfAuthors;
            int authorNumber;

            System.out.print("Название книги: ");
            book.setTitle(scanner.next());
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

            booksArray.add(book);
            keeping.saveBooks(booksArray);
        } else {
            System.out.println("Операция невозможна");
	}
    }
    
    private void showBooks(){
        if (!booksArray.isEmpty()) {
            System.out.println("----- Список книг -----");
            for (int i = 0; i < booksArray.size(); i++) {
                System.out.println(i+1 + ") " + booksArray.get(i).toString());
            }
            System.out.println("----- Список книг -----");
        } else {
            System.out.println("Нет добавленных книг");
        }
    }
    
    private void addHistory(){
        if (!booksArray.isEmpty() && !readersArray.isEmpty() && countBooks() > 0) {
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

            history.book.takeBook();

            history.setIssueDate(localdateToDate(LocalDate.now()));
            history.setReturnDate(localdateToDate(LocalDate.now().plusWeeks(2)));

            System.out.println("---------- Взять книгу ----------");

            historysArray.add(history);
            keeping.saveHistorys(historysArray);
        } else {
		System.out.println("Операция невозможна");
	}
    }
    
    private void extendReturnDate(){
        if (showTakedBooks()) {
            int numberOfBookToExtend = inputInt();
            LocalDate newDate = dateToLocaldate(historysArray.get(numberOfBookToExtend-1).getReturnDate());
            newDate = newDate.plusWeeks(2);
            historysArray.get(numberOfBookToExtend-1).setReturnDate(localdateToDate(newDate));
        }
    }
    
    private void returnBook(){
        if (showTakedBooks()) {
            int numberOfBookToReturn = inputInt();
            if (numberOfBookToReturn <= historysArray.size()) {
                    historysArray.remove(numberOfBookToReturn-1);
                    System.out.println("Книга возвращена");
            }
	}
    }
    
    private boolean showTakedBooks(){
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
    
    private void changeBook() {
        System.out.println("----- Список книг -----");
        for (int i = 0; i < booksArray.size(); i++) {
            System.out.println(i+1 + ") " + booksArray.get(i).getTitle());
        }
        System.out.println("----- Список книг -----");
        System.out.print("Введите номер книги: ");
        int numberOfBookToChange = inputInt();
        
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
                String newName = scanner.next();
                booksArray.get(numberOfBookToChange-1).setTitle(newName);
                break;
            case 2:
                //изменить год публикации
                System.out.print("Новый год публикации: ");
                int newYear = inputInt();
                booksArray.get(numberOfBookToChange-1).setPublishYear(newYear);
                break;
            case 3:
                //изменить количество
                System.out.print("Новое количество: ");
                int newCount = inputInt();
                booksArray.get(numberOfBookToChange-1).setCount(newCount);
                break;
            default:
                System.out.println("Введена неверная опция");
                break;
        }
        keeping.saveBooks(booksArray);
    }
    
    private void changeReader() {
        System.out.println("----- Список читателей -----");
        for (int i = 0; i < readersArray.size(); i++) {
            System.out.println(i+1 + ") " + readersArray.get(i).getFirstname());
        }
        System.out.println("----- Список читателей -----");
        
        System.out.print("Введите номер читателя: ");
        int numberOfReaderToChange = inputInt();
        
        System.out.println("Выберите что хотите изменить\n");
        System.out.println("1) Имя\n"
                        + "2) Фамилию\n"
                        + "3) Номер");
        System.out.print("-->");
        int paramToChange = inputInt();
        
        switch(paramToChange){
            case 1:
                //изменить название
                System.out.print("Новое имя: ");
                String newFristname = scanner.next();
                readersArray.get(numberOfReaderToChange-1).setFirstname(newFristname);
                break;
            case 2:
                //изменить год публикации
                System.out.print("Новая фамилия: ");
                String newSurename = scanner.next();
                readersArray.get(numberOfReaderToChange-1).setSurename(newSurename);
                break;
            case 3:
                //изменить количество
                System.out.print("Новый номер: ");
                String newPhone = scanner.next();
                readersArray.get(numberOfReaderToChange-1).setPhoneNumber(newPhone);
                break;
            default:
                System.out.println("Введена неверная опция");
                break;
        }
    }
    
    private int countBooks(){
        int count = 0;
        for (int i = 0; i < booksArray.size(); i++) {
            count += booksArray.get(i).getCount();
        }
        return count;
    }
    
    private void checkExpiredBooks(){
        for (int i = 0; i < historysArray.size(); i++) {
            if (historysArray.get(i).getReturnDate().before(localdateToDate(LocalDate.now()))) {
                historysArray.get(i).setExpired();
            }
        }
    }
    
    private int inputInt() {
	do {
            try {
                String inputedNumber = scanner.next();
                return Integer.parseInt(inputedNumber);
            } catch(Exception e) {
                System.out.println("Введены неверные данные.");
                System.out.print("Попробуйте еще раз -->");
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
