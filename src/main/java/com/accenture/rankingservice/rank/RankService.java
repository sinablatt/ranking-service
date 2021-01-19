package com.accenture.rankingservice.rank;

import java.util.List;
import java.util.Optional;

public interface RankService {
  Optional<Rank> findById(String id);

  boolean isAlreadyInUse(String username);

  List<Rank> findAll();

  List<Rank> findByCategory(String categoryName);

  Rank save(Rank rank);

  void deleteAtMidnight();
}
