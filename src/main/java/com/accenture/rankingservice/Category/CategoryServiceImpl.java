package com.accenture.rankingservice.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository repository;

    public Category findByName(String name){
        return repository.findByName(name);
    }

    public void saveAll(List<Category> categories){
        repository.saveAll(categories);
    }

    public List<Category> findAll(){
        return repository.findAll();
    }
}
