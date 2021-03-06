package Test.Controller;


import Test.Entity.Author;
import Test.Entity.Book;
import Test.Repository.AuthorRepository;
import Test.Repository.BookRepository;
import Test.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BookResource {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    @GetMapping("/authors/{authorId}/books")
    public Page<Book> getAllBooksByAuthorId(
            @PathVariable(value = "authorId") Long authorId,
            Pageable pageable
    ) {
        return bookRepository.findByAuthorId(authorId, pageable);
    }

    @PostMapping("/authors/{authorId}/books")
    public ResponseEntity<Book> createBook(
            @PathVariable(value = "authorId") Long authorId,
            @Valid @RequestBody Book bookRequest
    ) {
        return authorRepository.findById(authorId)
                .map(author -> {
                    bookRequest.setAuthor(author);
                    return bookRepository.save(bookRequest);
                })
                .map(
                        book -> ResponseEntity.created(ResponseUtil.resourceUri(book.getAuthor()))
                                .body(book)
                ).orElseThrow(
                        () -> new ResourceNotFoundException(
                                "AuthorId " + authorId + " not found"
                        )
                );
    }

    @PutMapping("/authors/{authorId}/books/{bookId}")
    public ResponseEntity<Book> updateBook(
            @PathVariable(value = "authorId") Long authorId,
            @PathVariable(value = "bookId") Long bookId,
            @Valid @RequestBody Book bookRequest
    ) {

        Author author = authorRepository
                .findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "authorId " + authorId + " not found")
                );

        return bookRepository.findById(bookId)
                .map(book -> {
                    book.setIsbn(bookRequest.getIsbn());
                    book.setPageCount(bookRequest.getPageCount());
                    book.setPublishedDate(bookRequest.getPublishedDate());
                    book.setTitle(bookRequest.getTitle());
                    book.setAuthor(author);
                    return bookRepository.save(book);
                })
                .map(book -> ResponseEntity
                        .ok().location(
                                ResponseUtil.resourceUri(book.getTitle()
                                )
                        )
                        .body(book)
                )
                .orElseThrow(() -> new ResourceNotFoundException("BookId " + bookId + "not found"));
    }

    @DeleteMapping("/authors/{authorId}/books/{bookId}")
    public ResponseEntity<?> deleteBook(
            @PathVariable(value = "authorId") Long authorId,
            @PathVariable(value = "bookId") Long bookId
    ) {
        return bookRepository.findByIdAndAuthorId(bookId, authorId)
                .map(book -> {
                            bookRepository.delete(book);
                            return ResponseEntity.ok().build();
                        }
                ).orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Book not found with id "
                                        + bookId +
                                        " and authorId "
                                        + authorId
                        )
                );
    }
}
