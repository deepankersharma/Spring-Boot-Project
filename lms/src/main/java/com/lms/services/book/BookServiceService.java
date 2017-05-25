package com.lms.services.book;

import static com.lms.utils.constants.Constant.PAGE_SIZE;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lms.models.Book;
import com.lms.dao.repository.BookRepository;
import com.lms.models.Library;
import com.lms.utils.helper.PaginationHelper;

/**
 * Created by bhushan on 17/4/17.
 */
@Service
public class BookServiceService implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public Book get(Long id) {
        return bookRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
       return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    @Override
    @Transactional
    public void update(Book books) {
         bookRepository.save(books);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> getPageRequest(Library library, Integer pageNumber) {
        return bookRepository.findByLibrary(library, PaginationHelper.getPageRequest(pageNumber));
    }

    @Override
    @Transactional(readOnly = true)
    public Long countBYLibraryAndIsbn(Library library, String isbn) {
        return bookRepository.countByLibraryAndIsbn(library, isbn);
    }

    @Override
    @Transactional(readOnly = true)
    public Book findByUuid(String uuid) {
        return bookRepository.findByUuid(uuid);
    }

    @Override
    public List<Book> findByLibraryAndIsbnIn(Library library, Set<String> isbns) {
        return bookRepository.findByLibraryAndIsbnIn(library, isbns);
    }
}
