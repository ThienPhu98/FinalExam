package process.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import process.management.model.City;
import process.management.repository.CityRepository;

import java.util.Optional;

@Service
public class CityServiceImpl implements ICityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public void save(City t) {
        cityRepository.save(t);
    }

    @Override
    public void remove(Long id) {
        cityRepository.deleteById(id);
    }
}
