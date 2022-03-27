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
        candidates.put(1, new Candidate(size.incrementAndGet(), "Александр", "Description Александр", LocalDate.now(), cityService.findById(1)));
        candidates.put(2, new Candidate(size.incrementAndGet(), "Алексей", "Description Алексей", LocalDate.now(), cityService.findById(2)));
        candidates.put(3, new Candidate(size.incrementAndGet(), "Ольга", "Description Ольга", LocalDate.now(), cityService.findById(3)));
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