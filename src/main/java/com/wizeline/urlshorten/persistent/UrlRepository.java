package com.wizeline.urlshorten.persistent;

import com.wizeline.urlshorten.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<URL, Long> {}
