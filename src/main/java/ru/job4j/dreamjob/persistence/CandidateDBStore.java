package ru.job4j.dreamjob.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CandidateDBStore {

    private final BasicDataSource pool;

    public CandidateDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "SELECT c.id, c.name, c.description, c.created, c.visible, ci.name, c.photo AS city_name FROM candidates AS c "
                             + "JOIN city ci ON ci.id = c.city_id "
                             + "ORDER BY c.id")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getTimestamp("created").toLocalDateTime().toLocalDate(),
                            it.getBoolean("visible"),
                            new City(it.getString("city_name")),
                            it.getBytes("photo"))
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }


    public Candidate add(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO candidates (name, description, created, visible, city_id, photo) VALUES (?, ?, ?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDesc());
            ps.setTimestamp(3, Timestamp.valueOf(candidate.getCreated().atTime(LocalTime.MIDNIGHT)));
            ps.setBoolean(4, candidate.isVisible());
            ps.setInt(5, candidate.getCity().getId());
            ps.setBytes(6, candidate.getPhoto());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidate;
    }

    public void create(Candidate candidate) {
        add(candidate);
    }

    public void update(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("UPDATE candidates SET name = ?, description = ?, created = ?, city_id = ?, photo = ? WHERE id = ?")
        ) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDesc());
            ps.setTimestamp(3, Timestamp.valueOf(candidate.getCreated().atTime(LocalTime.MIDNIGHT)));
            ps.setInt(4, candidate.getCity().getId());
            ps.setBytes(5, candidate.getPhoto());
            ps.setInt(6, candidate.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Candidate findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(
                     "SELECT c.id, c.name, c.description, c.created, c.visible, ci.name, c.photo AS city_name FROM candidates AS c "
                             + "JOIN city ci ON ci.id = c.city_id "
                             + "WHERE c.id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("desc"),
                            it.getTimestamp("created").toLocalDateTime().toLocalDate(),
                            it.getBoolean("visible"),
                            new City(it.getString("city_name")),
                            it.getBytes("photo"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}