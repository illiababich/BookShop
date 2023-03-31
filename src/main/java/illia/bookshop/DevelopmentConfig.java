package illia.bookshop;

import illia.bookshop.book.Book;
import illia.bookshop.book.BookRepository;
import illia.bookshop.book.Genre;
import illia.bookshop.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DevelopmentConfig {
    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepository, BookRepository bookRepository) {
        return args -> {
            bookRepository.save(new Book("title", LocalDate.now(), 213, "desc", Genre.Adventure, 12.23f));
            bookRepository.save(new Book("alice in the wonderland", LocalDate.now(), 213, "desc", Genre.Adventure, 12.23f));
            bookRepository.save(new Book("qwewre", LocalDate.now(), 213, "desc", Genre.Adventure, 12.23f));
            bookRepository.save(new Book("chess", LocalDate.now(), 213, "desc", Genre.Adventure, 12.23f));
            bookRepository.save(new Book("sapiens", LocalDate.now(), 213, "desc", Genre.Adventure, 12.23f));
        };
    }
}
