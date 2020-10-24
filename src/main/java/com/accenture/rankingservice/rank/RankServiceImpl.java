package com.accenture.rankingservice.rank;

import com.accenture.rankingservice.Category.Category;
import com.accenture.rankingservice.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    RankRepository repository;

    @Autowired
    CategoryRepository categoryRepository;

    public Optional<Rank> findById(String id) {
        return repository.findById(id);
    }

    public List<Rank> findAll() {
        return repository.findAll();
    }

    public List<Rank> findByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        return repository.findByCategory(category.getId());
    }

    @Override
    public Rank save(Rank rank) {
        List<Rank> ranksByCategory = repository.findByCategory(rank.getCategory().getName());
        for (Rank iterativeRank : ranksByCategory) {
            if (iterativeRank.getUsername().equals(rank.getUsername())) {
                rank.setId(iterativeRank.getId());
                return repository.save(rank);
            }
        }
        return repository.save(rank);
    }
}
