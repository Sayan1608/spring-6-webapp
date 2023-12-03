package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author auth1 = new Author();
        auth1.setFirstName("Joseph");
        auth1.setLastName("Murphy");

        Book book1 = new Book();
        book1.setTitle("The Power of Your Subconcious Mind");
        book1.setIsbn("12345");

        Author auth2 = new Author();
        auth2.setFirstName("John");
        auth2.setLastName("Grisham");

        Book book2 = new Book();
        book2.setTitle("A Painted House");
        book2.setIsbn("5678");

        Author savedAuth1 = authorRepository.save(auth1);
        Book savedBook1 = bookRepository.save(book1);

        Author savedAuth2 = authorRepository.save(auth2);
        Book savedBook2 = bookRepository.save(book2);

        auth1.getBooks().add(book1);
        auth2.getBooks().add(book2);
        book1.getAuthors().add(auth1);
        book2.getAuthors().add(auth2);

        authorRepository.save(auth1);
        authorRepository.save(auth2);

        Publisher pub1 = new Publisher();
        pub1.setPublisherName("Penguin House");
        pub1.setAddress("California, US");
        pub1.setCity("California");
        pub1.setState("California State");
        pub1.setZip("000123");

        Publisher savedPublisher = publisherRepository.save(pub1);

        savedBook1.setPublisher(savedPublisher);
        savedBook2.setPublisher(savedPublisher);

        bookRepository.save(savedBook1);
        bookRepository.save(savedBook2);

        System.out.println("Inside Bootstrap Class");
        System.out.println("Author Count : " + authorRepository.count());
        System.out.println("Book Count : "+ bookRepository.count());
        System.out.println("Publisher Count : " + publisherRepository.count());

    }
}
