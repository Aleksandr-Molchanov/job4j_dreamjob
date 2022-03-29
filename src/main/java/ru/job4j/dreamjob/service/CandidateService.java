package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.persistence.CandidateDBStore;

import java.util.Collection;

@ThreadSafe
@Service
public class CandidateService {

    private final CandidateDBStore store;

    public CandidateService(CandidateDBStore store) {
        this.store = store;
    }

    public Collection<Candidate> findAll() {
        return store.findAll();
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }

    public void add(Candidate candidate) {
        store.add(candidate);
    }

    public void create(Candidate candidate) {
        store.create(candidate);
    }

    public void update(Candidate candidate) {
        store.update(candidate);
    }
}
