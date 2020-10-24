package com.accenture.rankingservice.Category;

import java.util.List;

public interface CategoryService {
  void saveAll(List<Category> categories);

  Category findByName(String name);

  List<Category> findAll();
}
