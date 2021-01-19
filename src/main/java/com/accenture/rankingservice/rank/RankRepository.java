package com.accenture.rankingservice.rank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<Rank, String> {

  @Query(value = "SELECT * FROM rank WHERE category_id= ?1", nativeQuery = true)
  List<Rank> findByCategory(String categoryId);

  boolean existsByUsername(String username);

}
