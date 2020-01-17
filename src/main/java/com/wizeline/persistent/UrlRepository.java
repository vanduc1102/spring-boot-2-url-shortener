package com.wizeline.persistent;

import com.wizeline.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<URL, Long> {}
