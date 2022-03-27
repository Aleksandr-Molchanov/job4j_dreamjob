package ru.job4j.dreamjob.persistence;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;

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

    private final CityService cityService;

    private PostStore(CityService cityService) {
        this.cityService = cityService;
        posts.put(1, new Post(size.incrementAndGet(), "Junior Java Job", "Description Junior Java Job", LocalDate.now(), cityService.findById(1)));
        posts.put(2, new Post(size.incrementAndGet(), "Middle Java Job", "Description Middle Java Job", LocalDate.now(), cityService.findById(2)));
        posts.put(3, new Post(size.incrementAndGet(), "Senior Java Job", "Description Senior Java Job", LocalDate.now(), cityService.findById(3)));
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
        post.setCreated(LocalDate.now());
        post.setCity(cityService.findById(post.getCity().getId()));
        posts.put(id, post);
    }

    public void create(Post post) {
        posts.put(post.getId(), post);
    }

    public void update(Post post) {
        post.setCreated(LocalDate.now());
        post.setCity(cityService.findById(post.getCity().getId()));
        posts.put(post.getId(), post);
    }
}