
package Tools;

import Classes.*;
import java.util.Scanner;


public class Functions {
    Scanner scanner = new Scanner(System.in);
    
    public void showHints(){
        System.out.println("");
        System.out.println("Выберите опцию");
        System.out.println("0) Выход");
        System.out.println("1) Добавить автора");
        System.out.println("2) Вывести список авторов");
        System.out.println("3) Добавить читателя");
        System.out.println("4) Вывести список читателей");
        System.out.println("5) Взять книгу");
        System.out.println("6) Вывести список взятых книг");
        System.out.println("7) Вывести подсказки");
        System.out.println("");
    }
    
    public Author addAuthor(){
        Author author = new Author();
        
        System.out.print("Имя автора: ");
        author.setName(scanner.next());
        
        System.out.print("Фамилия автора: ");
        author.setSurename(scanner.next());
        
        System.out.print("Год рождения:");
        author.setBornYear(scanner.nextInt());
        
        return author;
    }
    
    public Book addBook(){
        Book book = new Book();
        
        System.out.println("Название книги");
        book.setTitle(scanner.next());
        
        System.out.println("Авторы книги");
//        book.setAuthors(authors);
        
        System.out.println("Год публикации книги");
        book.setPublishYear(scanner.nextInt());
        
        System.out.println("Количество книг");
        book.setCount(scanner.nextInt());
        
        return book;
    }
    
    public History addHistory(){
        History history = new History();
        
//        for (int i = 0; i < authorsArray; i++) {
//            Object object = arr[i];
//            
//        }
        
        return null;
    }
    
    public Reader addReader(){
        Reader reader = new Reader();
        
        System.out.print("Имя читателя");
        reader.setFirstname(scanner.next());
                
        System.out.print("Фамилия читателя");
        reader.setSurename(scanner.next());
                
        System.out.print("Телефон читателя");
        reader.setPhoneNumber(scanner.next());
                
        return reader;
    }
}
