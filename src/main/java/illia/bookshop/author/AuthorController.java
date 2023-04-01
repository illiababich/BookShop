package illia.bookshop.author;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/author")
public class AuthorController {
    private final AuthorService authorService;

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_EMPLOYEE')")
    @PostMapping(path = "/{authorId}/{bookId}")
    public ResponseEntity<String> addBookToAuthor(@PathVariable Long authorId, @PathVariable Long bookId) {
        return authorService.addBookToAuthor(authorId, bookId);
    }

    @GetMapping
    public Iterable<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping(path = "/{authorId}")
    public Author getAuthorById(@PathVariable Long authorId) {
        return authorService.getAuthorBId(authorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_EMPLOYEE')")
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @DeleteMapping(path = "/{authorId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_EMPLOYEE')")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long authorId) {
        return authorService.deleteAuthorById(authorId);
    }

    @PatchMapping(path = "/{authorId}", consumes = "application/json")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_EMPLOYEE')")
    public Author patchAuthorById(@RequestBody Author author, @PathVariable Long authorId) {
        return authorService.patchAuthorById(authorId, author);
    }
}
