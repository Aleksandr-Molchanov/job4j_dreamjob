package ru.job4j.dreamjob.persistence;

import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Post;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostDBStoreTest {
    @Test
    public void whenCreatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "Description");
        store.create(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
        assertThat(postInDb.getDescription(), is(post.getDescription()));
    }

    @Test
    public void whenFindAllPost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post1 = new Post(0, "Java Job 1", "Description 1");
        Post post2 = new Post(1, "Java Job 2", "Description 2");
        store.create(post1);
        store.create(post2);
        List<Post> postInDb = store.findAll();
        assertThat(postInDb.get(0).getName(), is(post1.getName()));
        assertThat(postInDb.get(1).getName(), is(post2.getName()));
        assertThat(postInDb.get(0).getDescription(), is(post1.getDescription()));
        assertThat(postInDb.get(1).getDescription(), is(post2.getDescription()));
    }

    @Test
    public void whenAddPost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "Description");
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
        assertThat(postInDb.getDescription(), is(post.getDescription()));
    }

    @Test
    public void whenUpdatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "Description");
        store.add(post);
        Post updatePost = new Post(post.getId(), "New Java Job", "New Description");
        store.update(updatePost);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is("New Java Job"));
        assertThat(postInDb.getDescription(), is("New Description"));
    }
}