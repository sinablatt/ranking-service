package com.accenture.rankingservice.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  CategoryService service;

  @PostMapping("/all")
  public ResponseEntity<Void> saveAll(@RequestBody List<Category> categories) {
    service.saveAll(categories);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Category>> getAll() {
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }
}
