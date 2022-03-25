package ru.job4j.dreamjob.persistence;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class PostStore {

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final AtomicInteger size = new AtomicInteger(0);

    private PostStore() {
        posts.put(1, new Post(size.incrementAndGet(), "Junior Java Job", "Description Junior Java Job", LocalDate.now(), new City()));
        posts.put(2, new Post(size.incrementAndGet(), "Middle Java Job", "Description Middle Java Job", LocalDate.now(), new City()));
        posts.put(3, new Post(size.incrementAndGet(), "Senior Java Job", "Description Senior Java Job", LocalDate.now(), new City()));
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void add(Post post) {
        int id = size.incrementAndGet();
        post.setId(id);
        posts.put(id, post);
    }

    public void create(Post post) {
        posts.put(post.getId(), post);
    }

    public void update(Post post) {
        create(post);
    }
}