package illia.bookshop.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "BOOK")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookTitle;

    private LocalDate publishDate;

    private int pageNumber;

    // @ManyToMany
    // private List<Author> authors;

    private String description;

    private Genre genre;

    private float price;

    public Book(String bookTitle, LocalDate publishDate, int pageNumber, String description, Genre genre, float price) {
        this.bookTitle = bookTitle;
        this.publishDate = publishDate;
        this.pageNumber = pageNumber;
        this.description = description;
        this.genre = genre;
        this.price = price;
    }
}
