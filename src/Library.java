public class Library {
    public static void main(String[] args){
        Author gluhovski = new Author("Дмитрий", "Глуховский", 1979);
        Author ark_strugatski = new Author("Аркадий", "Стругацкий", 1925);
        Author bor_strugatski = new Author("Борис", "Стругацкий", 1933);
        Author lukyanenko = new Author("Сергей", "Лукьяненко", 1968);
        Author ellison = new Author("Харлан", "Эллисон", 1934);
        Author phil_dick = new Author("Филлип", "Дик", 1928);

        Author[] metroAuthors = new Author[]{gluhovski};
        Book metro = new Book("Метро 2033", metroAuthors, 2005);

        Author[] piknikAuthors = new Author[]{ark_strugatski, bor_strugatski};
        Book piknik = new Book("Пикник на обочине", piknikAuthors, 1972);

        Author[] labAuthors = new Author[]{lukyanenko};
        Book labirint = new Book("Лабиринт отражений", labAuthors, 1997);

        Author[] ihnmbimsAuthors = new Author[]{ellison};
        Book ihnmbims = new Book("У меня нет рта, но я должен кричать", ihnmbimsAuthors, 1967);

        Author[] bladerunnerAuthors = new Author[]{phil_dick};
        Book bladerunner = new Book("Бегущий по лезвию", bladerunnerAuthors, 1968);

        Book[] books = new Book[]{metro, piknik, labirint, ihnmbims, bladerunner};


        bladerunner.toString();
        // books[2].toString();
    }
}
