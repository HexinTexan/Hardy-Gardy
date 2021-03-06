package Test.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book{
    @Id
    @GeneratedValue
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "page_count", nullable = false)
    private Integer pageCount;

    @Column(name = "published_date", nullable = false)
    private LocalDate publishedDate;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "author_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_books_authors_id"
            )
    )
    private Author author;
}
