package ru.otus.springsecurityacl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springsecurityacl.domain.Book;
import ru.otus.springsecurityacl.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final MutableAclService mutableAclService;

    @PostFilter("hasPermission(filterObject, 'READ')")
    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    @PreAuthorize("#book.id != null ? hasPermission(#book, 'WRITE') : hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void save(Book book) {
        Book savedBook = repository.save(book);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Sid owner = new PrincipalSid(authentication);
        ObjectIdentity oid = new ObjectIdentityImpl(book.getClass(), savedBook.getId());

        final Sid admin = new PrincipalSid("admin");

        MutableAcl acl = mutableAclService.createAcl(oid);
        acl.setOwner(owner);
        acl.insertAce(acl.getEntries().size(), BasePermission.READ, admin, true);

        mutableAclService.updateAcl(acl);
    }

    @PreAuthorize("hasPermission(#id, 'ru.otus.springsecurityacl.domain.Book', 'DELETE')")
    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
