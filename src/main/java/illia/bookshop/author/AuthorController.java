package illia.bookshop.author;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/author")
public class AuthorController {
    private final AuthorService authorService;

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping(path = "/{authorId}/{bookId}")
    public ResponseEntity<String> addBookToAuthor(@PathVariable Long authorId, @PathVariable Long bookId) {
        return authorService.addBookToAuthor(authorId, bookId);
    }
}
