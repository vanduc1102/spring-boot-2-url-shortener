package com.wizeline.urlshorten.controller;

import com.wizeline.urlshorten.model.URL;
import com.wizeline.urlshorten.service.UrlService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class UrlController {

  @Autowired private UrlService service;

  @RequestMapping("/")
  public String viewHomePage(Model model) {
    List<URL> listURLS = service.listAll();
    URL URL = new URL();
    model.addAttribute("listUrls", listURLS);
    model.addAttribute("url", URL);
    return "index";
  }

  @PostMapping("/save")
  public String saveProduct(@ModelAttribute("url") URL url) {
    service.save(url);
    log.debug("save: {}", url);
    return "redirect:/";
  }

  @GetMapping("/delete/{id}")
  public String deleteProduct(@PathVariable(name = "id") Long id) {
    service.delete(id);
    return "redirect:/";
  }
}
