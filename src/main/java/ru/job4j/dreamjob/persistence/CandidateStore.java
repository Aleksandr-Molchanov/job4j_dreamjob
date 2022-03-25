package ru.job4j.dreamjob.persistence;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;

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

    private CandidateStore() {
        candidates.put(1, new Candidate(size.incrementAndGet(), "Александр", "Description Александр", LocalDate.now(), new City()));
        candidates.put(2, new Candidate(size.incrementAndGet(), "Алексей", "Description Алексей", LocalDate.now(), new City()));
        candidates.put(3, new Candidate(size.incrementAndGet(), "Ольга", "Description Ольга", LocalDate.now(), new City()));
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public void add(Candidate candidate) {
        candidates.put(size.incrementAndGet(), candidate);
    }

    public void create(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }

    public void update(Candidate candidate) {
        create(candidate);
    }
}