package com.wizeline.urlshorten.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import com.wizeline.urlshorten.Application;
import com.wizeline.urlshorten.model.URL;
import com.wizeline.urlshorten.persistent.UrlRepository;
import com.wizeline.urlshorten.service.UrlService;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UrlControllerIntegrationTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private UrlService urlService;
  @Autowired private UrlRepository urlRepository;

  private URL createAnUrl() {
    URL url = new URL(null, "http://" + UUID.randomUUID().toString() + ".com", "abc", null);
    return urlService.save(url);
  }

  @Test
  public void viewHomePage() throws Exception {
    MockHttpServletRequestBuilder getIndex = get("/");
    this.mockMvc
        .perform(get("/"))
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML_VALUE))
        .andExpect(status().isOk())
        .andExpect(xpath("/html/body/div/div[2]/form/div/div[2]/button").nodeCount(1))
        .andExpect(xpath("//*[@id=\"urlFormInput\"]").nodeCount(1));
  }

  @Test
  void viewHomePage_withExistingData() throws Exception {
    createAnUrl();
    createAnUrl();
    MockHttpServletRequestBuilder getIndex = get("/");
    this.mockMvc
        .perform(get("/"))
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML_VALUE))
        .andExpect(status().isOk())
        .andExpect(xpath("/html/body/div/div[4]/table/tbody/tr").nodeCount(2));
  }

  @Test
  void saveUrl() throws Exception {
    this.mockMvc
        .perform(
            post("/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("originUrl", "http://wizeline.com"))
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/"));

    this.mockMvc
        .perform(get("/"))
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML_VALUE))
        .andExpect(status().isOk())
        .andExpect(xpath("/html/body/div/div[4]/table/tbody/tr").nodeCount(1));
  }

  @Test
  void deleteUrl() throws Exception {
    URL createdUrl = this.createAnUrl();
    this.mockMvc
        .perform(get("/delete/" + createdUrl.getId()))
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/"));

    this.mockMvc
        .perform(get("/"))
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML_VALUE))
        .andExpect(status().isOk())
        .andExpect(xpath("/html/body/div/div[4]/table/tbody/tr").nodeCount(0));
  }

  @AfterEach
  public void cleanDB() {
    urlRepository.deleteAll();
  }
}
