package ru.job4j.dreamjob.persistence;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.service.CityService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class CandidateStore {

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private final AtomicInteger size = new AtomicInteger(0);

    private final CityService cityService;

    private CandidateStore(CityService cityService) {
        this.cityService = cityService;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public void add(Candidate candidate) {
        int id = size.incrementAndGet();
        candidate.setId(id);
        candidate.setCreated(LocalDate.now());
        candidate.setCity(cityService.findById(candidate.getCity().getId()));
        candidates.put(id, candidate);
    }

    public void create(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }

    public void update(Candidate candidate) {
        candidate.setCreated(LocalDate.now());
        candidate.setCity(cityService.findById(candidate.getCity().getId()));
        candidates.put(candidate.getId(), candidate);
    }
}