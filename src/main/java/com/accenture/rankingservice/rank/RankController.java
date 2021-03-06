package com.accenture.rankingservice.rank;

import com.accenture.rankingservice.Category.Category;
import com.accenture.rankingservice.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("/rank")
public class RankController {

  @Autowired
  RankService rankService;

  @Autowired
  CategoryService categoryService;

  @GetMapping({"/{id}", "/{id}/"})
  public ResponseEntity<Optional<Rank>> findById(@PathVariable("id") String id) {
    return new ResponseEntity<>(rankService.findById(id), HttpStatus.OK);
  }

  @GetMapping({"/", ""})
  public ResponseEntity<List<Rank>> findAll() {
    return new ResponseEntity<>(rankService.findAll(), HttpStatus.OK);
  }

  @GetMapping({"/category/{categoryName}", "/category/{categoryName}/"})
  public ResponseEntity<List<Rank>> findByCategory(@PathVariable("categoryName") String categoryName) {
    return new ResponseEntity<>(rankService.findByCategory(categoryName), HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<Rank> save(@RequestBody Rank rank) {
    String categoryName = rank.getCategory().getName();
    Category category = categoryService.findByName(categoryName);
    rank.setCategory(category);
    return new ResponseEntity<>(rankService.save(rank), HttpStatus.CREATED);
  }

  @GetMapping({"/isinuse/{username}", "/isinuse/{username}/"})
  public ResponseEntity<Boolean> isAlreadyInUse(@PathVariable("username") String username) {
    return new ResponseEntity<>(rankService.isAlreadyInUse(username), HttpStatus.OK);
  }
}
