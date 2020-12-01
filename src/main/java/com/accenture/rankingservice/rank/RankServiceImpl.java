package com.accenture.rankingservice.rank;

import com.accenture.rankingservice.Category.Category;
import com.accenture.rankingservice.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RankServiceImpl implements RankService {

  @Autowired
  RankRepository repository;

  @Autowired
  CategoryRepository categoryRepository;

  public Optional<Rank> findById(String id) {
    return repository.findById(id);
  }

  @Override
  public boolean isAlreadyInUse(String username) {
    return repository.existsByUsername(username);
  }

  public List<Rank> findAll() {
    return repository.findAll();
  }

  public List<Rank> findByCategory(String categoryName) {
    Category category = categoryRepository.findByName(categoryName);
    List<Rank> ranks = repository.findByCategory(category.getId());
    Collections.sort(ranks, Comparator.comparingInt(Rank::getScore));
    Collections.reverse(ranks);
    return ranks;
  }

  @Override
  public Rank save(Rank rank) {
    List<Rank> ranksByCategory = repository.findByCategory(rank.getCategory().getId());
    for (Rank iterativeRank : ranksByCategory) {
      if (iterativeRank.getUsername().equals(rank.getUsername())) {
        rank.setId(iterativeRank.getId());
        rank.setScore(rank.getScore() + iterativeRank.getScore());
        return repository.save(rank);
      }
    }
    return repository.save(rank);
  }
}
