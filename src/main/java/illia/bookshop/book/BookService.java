package illia.bookshop.book;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book getBookBId(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Book> createBook(Book book) {
        bookRepository.save(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteBookById(Long bookId) {
        try {
            Optional<Book> book = bookRepository.findById(bookId);
            bookRepository.delete(book.get());
            return new ResponseEntity<>(String.format("The book with id: %s was deleted successfully.", bookId), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(String.format("The book with id: %s was not found.", bookId), HttpStatus.NOT_FOUND);
        }
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book patchBookById(Long bookId, Book bookPatch) {
        Book updatedBook = bookRepository.findById(bookId).map((book) -> book.toBuilder()
                        .bookTitle(Optional.ofNullable(bookPatch.getBookTitle()).orElse(book.getBookTitle()))
                        .description(Optional.ofNullable(bookPatch.getDescription()).orElse(book.getDescription()))
                        .build())
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.NOT_FOUND, String.format("The book with ID: %s not found.", bookId)));

        bookRepository.save(updatedBook);
        return updatedBook;
    }
}
