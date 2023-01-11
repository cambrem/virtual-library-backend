package com.cam.VirtualLibrary.repository;

import com.cam.VirtualLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
