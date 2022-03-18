package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private AtomicInteger size = new AtomicInteger(0);

    private PostStore() {
        posts.put(1, new Post(size.incrementAndGet(), "Junior Java Job", "Description Junior Java Job"));
        posts.put(2, new Post(size.incrementAndGet(), "Middle Java Job", "Description Middle Java Job"));
        posts.put(3, new Post(size.incrementAndGet(), "Senior Java Job", "Description Senior Java Job"));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void add(Post post) {
        posts.put(size.incrementAndGet(), post);
    }

    public void create(Post post) {
        posts.put(post.getId(), post);
    }

    public void update(Post post) {
        create(post);
    }
}