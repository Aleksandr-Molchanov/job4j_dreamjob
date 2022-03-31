package ru.job4j.dreamjob.persistence;

import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CandidateDBStoreTest {
    @Test
    public void whenCreateCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Alexandr", "Description");
        store.create(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
        assertThat(candidateInDb.getDesc(), is(candidate.getDesc()));
    }

    @Test
    public void whenFindAllCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate1 = new Candidate(0, "Alexandr", "Description 1");
        Candidate candidate2 = new Candidate(1, "Olga", "Description 2");
        store.create(candidate1);
        store.create(candidate2);
        List<Candidate> candidateInDb = store.findAll();
        assertThat(candidateInDb.get(0).getName(), is(candidate1.getName()));
        assertThat(candidateInDb.get(1).getName(), is(candidate2.getName()));
        assertThat(candidateInDb.get(0).getDesc(), is(candidate1.getDesc()));
        assertThat(candidateInDb.get(1).getDesc(), is(candidate2.getDesc()));
    }

    @Test
    public void whenAddCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Alexandr", "Description");
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
        assertThat(candidateInDb.getDesc(), is(candidate.getDesc()));
    }

    @Test
    public void whenUpdateCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Alexandr", "Description");
        store.add(candidate);
        Candidate updateCandidate = new Candidate(candidate.getId(), "Olga", "New Description");
        store.update(updateCandidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is("Olga"));
        assertThat(candidateInDb.getDesc(), is("New Description"));
    }
}