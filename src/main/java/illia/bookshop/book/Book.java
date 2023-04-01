package illia.bookshop.book;

import illia.bookshop.author.Author;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @ManyToOne
    @JoinColumn(name = "author_id")
    @Setter
    private Author author;

    private String description;

    private Genre genre;

    private float price;
    private String Isbn;
    private int availableQuantity;

    public Book(String bookTitle, LocalDate publishDate, int pageNumber, Author author, String description, Genre genre, float price, String isbn, int availableQuantity) {
        this.bookTitle = bookTitle;
        this.publishDate = publishDate;
        this.pageNumber = pageNumber;
        this.author = author;
        this.description = description;
        this.genre = genre;
        this.price = price;
        Isbn = isbn;
        this.availableQuantity = availableQuantity;
    }
}
