package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private AtomicInteger size = new AtomicInteger(3);

    private CandidateStore() {
        candidates.put(1, new Candidate(1, "Александр", "Description Александр"));
        candidates.put(2, new Candidate(2, "Алексей", "Description Алексей"));
        candidates.put(3, new Candidate(3, "Ольга", "Description Ольга"));
    }

    public static CandidateStore instOf() {
        return INST;
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