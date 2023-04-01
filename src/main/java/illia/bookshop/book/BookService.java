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

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public ResponseEntity<String> deleteBookById(Long bookId) {
        try {
            bookRepository.deleteById(bookId);
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
                        .publishDate(Optional.ofNullable(bookPatch.getPublishDate()).orElse(book.getPublishDate()))
                        .pageNumber(Optional.of(bookPatch.getPageNumber()).orElse(book.getPageNumber()))
                        .author(Optional.ofNullable(bookPatch.getAuthor()).orElse(book.getAuthor()))
                        .description(Optional.ofNullable(bookPatch.getDescription()).orElse(book.getDescription()))
                        .genre(Optional.ofNullable(bookPatch.getGenre()).orElse(book.getGenre()))
                        .price(Optional.of(bookPatch.getPrice()).orElse(book.getPrice()))
                        .Isbn(Optional.ofNullable(bookPatch.getIsbn()).orElse(book.getIsbn()))
                        .availableQuantity(Optional.of(bookPatch.getAvailableQuantity()).orElse(book.getAvailableQuantity()))
                        .build())
                .orElseThrow(() -> new HttpClientErrorException(
                        HttpStatus.NOT_FOUND, String.format("The book with ID: %s not found.", bookId)));

        bookRepository.save(updatedBook);
        return updatedBook;
    }
}
