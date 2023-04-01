package illia.bookshop;

import illia.bookshop.author.Author;
import illia.bookshop.author.AuthorRepository;
import illia.bookshop.book.Book;
import illia.bookshop.book.BookRepository;
import illia.bookshop.book.Genre;
import illia.bookshop.user.User;
import illia.bookshop.user.UserRepository;
import illia.bookshop.user.UserType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DevelopmentConfig {
    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepository, BookRepository bookRepository, AuthorRepository authorRepository) {
        return args -> {
            Author james_dashner = new Author("James Dashner");
            Author vladimir_nabokov = new Author("Vladimir Nabokov");
            Author stephen_king = new Author("Stephen King");
            Author michael_connelly = new Author("Michael Connelly");
            Author a_j_finn = new Author("A. J. Finn");
            authorRepository.saveAll(List.of(james_dashner, vladimir_nabokov, stephen_king, michael_connelly, a_j_finn));

            bookRepository.save(new Book("The Maze Runner", LocalDate.of(2014, 5, 6), 384, james_dashner, "desc", Genre.Science_fiction, 7.99f, "9781909489400", 10));
            bookRepository.save(new Book("Lolita", LocalDate.of(2000, 3, 2), 336, vladimir_nabokov, "desc", Genre.Modern, 9.99f, "9780141182537", 10));
            bookRepository.save(new Book("End of Watch – The Bill Hodges Trilogy", LocalDate.of(2017, 4, 5), 384, stephen_king, "desc", Genre.Thriller, 8.99f, "9781473642379", 10));
            bookRepository.save(new Book("The Crossing – Harry Bosch Series", LocalDate.of(2016, 6, 10), 432, michael_connelly, "desc", Genre.Crime, 8.99f, "9781409145875", 10));
            bookRepository.save(new Book("The Woman in the Window", LocalDate.of(2018, 12, 27), 448, a_j_finn, "desc", Genre.Crime, 8.99f, "9780008234188", 10));

            userRepository.save(new User("user", new BCryptPasswordEncoder().encode("password"),"test", UserType.ADMIN, "address", "zip", "+37069", true));
            userRepository.save(new User("gustavo.fring@gmail.com", new BCryptPasswordEncoder().encode("password"), "Gus Fring", UserType.CUSTOMER, "El Paso", "zip", "hidden", true));
        };
    }
}
