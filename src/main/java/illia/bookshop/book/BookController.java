package illia.bookshop.book;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/book")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/{bookId}")
    public Book getBookById(@PathVariable Long bookId) {
        return bookService.getBookBId(bookId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping(path = "/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        return bookService.deleteBookById(bookId);
    }

    @PatchMapping(path = "/{bookId}", consumes = "application/json")
    public Book patchBookById(@RequestBody Book book, @PathVariable Long bookId) {
        return bookService.patchBookById(bookId, book);
    }
}
