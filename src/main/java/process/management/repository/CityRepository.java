package process.management.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import process.management.model.City;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Long> {
}
