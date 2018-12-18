package com.bookshelf.repository;

import com.bookshelf.entity.Book;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BookSpecification implements Specification<Book> {

    private String SearchQueryByName;

    public BookSpecification(String searchQueryByName) {
        SearchQueryByName = searchQueryByName;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        if (SearchQueryByName != null && !SearchQueryByName.isEmpty()) {
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), SearchQueryByName.toLowerCase());
        }

        return null;
    }
}
