package com.wizeline.urlshorten.service;

import com.wizeline.urlshorten.model.URL;
import com.wizeline.urlshorten.persistent.UrlRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UrlService {

  @Autowired private UrlRepository repo;

  public List<URL> listAll() {
    return repo.findAll();
  }

  public URL save(URL url) {
    return repo.save(url);
  }

  public URL get(long id) {
    return repo.findById(id).get();
  }

  public void delete(long id) {
    repo.deleteById(id);
  }
}
