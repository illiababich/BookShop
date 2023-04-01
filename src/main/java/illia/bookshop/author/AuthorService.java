package illia.bookshop.author;

import illia.bookshop.book.Book;
import illia.bookshop.book.BookRepository;
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
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public ResponseEntity<String> addBookToAuthor(Long authorId, Long bookId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND));

        if (!author.getBooks().contains(book)) {
            author.getBooks().add(book);
            authorRepository.save(author);
            return new ResponseEntity<>("The book was added successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The author already has that book.", HttpStatus.NOT_FOUND);
        }
    }

    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorBId(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND));
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public ResponseEntity<String> deleteAuthorById(Long authorId) {
        try {
            authorRepository.deleteById(authorId);
            return new ResponseEntity<>(String.format("The author with id: %s was deleted.", authorId), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(String.format("The author with id: %s was not found.", authorId), HttpStatus.NOT_FOUND);
        }
    }

    public Author patchAuthorById(Long authorId, Author authorPatch) {
        Author updatedAuthor = authorRepository.findById(authorId).map((author) -> author.toBuilder()
                .name(Optional.ofNullable(authorPatch.getName()).orElse(author.getName()))
                .books(Optional.ofNullable(authorPatch.getBooks()).orElse(author.getBooks()))
                .build()
        ).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "The author with provided ID not found.")
        );

        return authorRepository.save(updatedAuthor);
    }
}
