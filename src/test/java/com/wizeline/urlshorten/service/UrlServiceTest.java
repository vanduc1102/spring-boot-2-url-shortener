package com.wizeline.urlshorten.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.wizeline.urlshorten.model.URL;
import com.wizeline.urlshorten.persistent.UrlRepository;
import java.util.NoSuchElementException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UrlServiceTest {

  @Autowired private UrlRepository repository;
  @Autowired private UrlService urlService;

  @Test
  public void listAll() {
    URL url = new URL();
    URL url2 = new URL();
    repository.save(url);
    repository.save(url2);
    assertEquals(2, urlService.listAll().size());
  }

  @Test
  public void save() {
    URL url = new URL();
    url.setOriginUrl("http://haivl.com");
    URL savedUrl = urlService.save(url);
    assertEquals(url.getOriginUrl(), urlService.get(savedUrl.getId()).getOriginUrl());
  }

  @Test
  public void get() {
    URL url = new URL();
    URL savedUrl = repository.save(url);
    assertEquals(savedUrl.getId(), urlService.get(savedUrl.getId()).getId());
  }

  @Test(expected = NoSuchElementException.class)
  public void delete() {
    URL url = new URL();
    URL savedUrl = repository.save(url);
    urlService.delete(savedUrl.getId());
    assertNull(urlService.get(savedUrl.getId()));
  }
}
