package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductManager productManager;
    private Product book1 = new Book(1, "book1", 1, "author1");
    private Product book2 = new Book(2, "book2", 2, "author2");
    private Product smartphone1 = new Smartphone(3, "smartphone1", 100, "brand1");
    private Product smartphone2 = new Smartphone(4, "smartphone2", 200, "brand2");
    private Product gold = new Product(5, "gold", 1000);

    @BeforeEach
    public void managerInit() {
        ProductRepository repository = new ProductRepository();
        repository.add(book1);
        repository.add(book2);
        repository.add(smartphone1);
        repository.add(smartphone2);
        repository.add(gold);
        this.productManager = new ProductManager(repository);
    }

    @Test
    void shouldAddProduct() {
        Product product = new Product(5, "pen", 1);
        this.productManager.add(product);
        Product[] expected = {book1, book2, smartphone1, smartphone2, gold, product};
        Product[] actual = this.productManager.getRepository().getItems();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddBook() {
        Book book3 = new Book(5, "book3", 3, "author3");
        this.productManager.add(book3);
        Product[] expected = {book1, book2, smartphone1, smartphone2, gold, book3};
        Product[] actual = this.productManager.getRepository().getItems();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddSmartphone() {
        Smartphone smartphone3 = new Smartphone(5, "smartphone33", 3, "brand3");
        this.productManager.add(smartphone3);
        Product[] expected = {book1, book2, smartphone1, smartphone2, gold, smartphone3};
        Product[] actual = this.productManager.getRepository().getItems();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchValidTextByBookName() {
        Product[] expected = {book1, book2};
        Product[] actual = this.productManager.searchBy("ok");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchValidTextByBookAuthor() {
        Product[] expected = {book1, book2};
        Product[] actual = this.productManager.searchBy("thor");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchValidTextBySmartphoneName() {
        Product[] expected = {smartphone1, smartphone2};
        Product[] actual = this.productManager.searchBy("art");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchValidTextBySmartphoneBrand() {
        Product[] expected = {smartphone1, smartphone2};
        Product[] actual = this.productManager.searchBy("and");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchInvalidTextBy() {
        Product[] expected = new Product[0];
        Product[] actual = this.productManager.searchBy("apple");
        assertArrayEquals(expected, actual);
    }
}