package com.wizeline.service;

import com.wizeline.model.URL;
import com.wizeline.persistent.UrlRepository;
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

  public void save(URL url) {
    repo.save(url);
  }

  public URL get(long id) {
    return repo.findById(id).get();
  }

  public void delete(long id) {
    repo.deleteById(id);
  }
}
