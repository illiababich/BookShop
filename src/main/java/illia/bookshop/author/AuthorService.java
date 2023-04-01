package illia.bookshop.author;

import illia.bookshop.book.Book;
import illia.bookshop.book.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public ResponseEntity<String> addBookToAuthor(Long authorId, Long bookId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND));

        if(!author.getBooks().contains(book)) {
            author.getBooks().add(book);
            authorRepository.save(author);
            return new ResponseEntity<>("The book was added successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The author already has that book.", HttpStatus.NOT_FOUND);
        }
    }
}
