package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        Publisher penguin = new Publisher("Penguin");

        publisherRepository.save(penguin);

//        System.out.println("Publisher count " + publisherRepository.count());

        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design","123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(penguin);
        penguin.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
//        publisherRepository.save(penguin);


//        publisherRepository.save(penguin);

        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Development without EJB","9429849");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        penguin.getBooks().add(noEJB);
        noEJB.setPublisher(penguin);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
//        publisherRepository.save(penguin);


        System.out.println("Started in bootstrap");
        System.out.println(authorRepository.count());
        System.out.println(bookRepository.count());
        System.out.println(publisherRepository.count());
//        authorRepository.findAll().forEach(System.out::println);
//        bookRepository.findAll().forEach(System.out::println);
//        publisherRepository.findAll().forEach(System.out::println);
    }
}
