package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Product book1 = new Book(1, "book1", 1, "author1");
    private Product book2 = new Book(2, "book2", 2, "author2");
    private Product smartphone1 = new Smartphone(3, "smartphone1", 100, "brand1");
    private Product smartphone2 = new Smartphone(4, "smartphone2", 200, "brand2");

    @BeforeEach
    public void repositoryInit() {
        repository.add(book1);
        repository.add(book2);
        repository.add(smartphone1);
        repository.add(smartphone2);
    }

    @Test
    void shouldGetAll() {
        Product[] expected = {book1, book2, smartphone1, smartphone2};
        Product[] actual = repository.getItems();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddProduct() {
        Product smartphone3 = new Smartphone(5, "smartphone3", 300, "brand3");
        this.repository.add(smartphone3);
        Product[] expected = {book1, book2, smartphone1, smartphone2, smartphone3};
        Product[] actual = repository.getItems();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindExistItemById() {
        int expected = 2;
        int actual = this.repository.findIndexById(3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindNonexistItemById() {
        int expected = -1;
        int actual = this.repository.findIndexById(10);
        assertEquals(expected, actual);
    }

    @Test
    void ShouldRemoveFirstItemById() {
        this.repository.removeById(1);
        Product[] actual = this.repository.getItems();
        Product[] expected = {book2, smartphone1, smartphone2};
        assertArrayEquals(expected, actual);
    }

    @Test
    void ShouldRemoveMidItemById() {
        this.repository.removeById(3);
        Product[] actual = this.repository.getItems();
        Product[] expected = {book1, book2, smartphone2};
        assertArrayEquals(expected, actual);
    }

    @Test
    void ShouldRemoveLastItemById() {
        this.repository.removeById(4);
        Product[] actual = this.repository.getItems();
        Product[] expected = {book1, book2, smartphone1};
        assertArrayEquals(expected, actual);
    }

    @Test
    void ShouldRemoveNonexistItemById() {
        this.repository.removeById(10);
        Product[] actual = this.repository.getItems();
        Product[] expected = {book1, book2, smartphone1, smartphone2};
        assertArrayEquals(expected, actual);
    }
}