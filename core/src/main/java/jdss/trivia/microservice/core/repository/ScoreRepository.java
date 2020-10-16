package jdss.trivia.microservice.core.repository;

import jdss.trivia.microservice.core.entities.Score;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ScoreRepository extends PagingAndSortingRepository<Score, Integer> {
    public Iterable<Score> findByName(String name);
}
