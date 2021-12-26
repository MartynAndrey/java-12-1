package ru.netology.repository;
import ru.netology.domain.Product;

public class ProductRepository {
    private Product[] items = new Product[0];

    public void add(Product item) {
        Product[] tmp = new Product[this.items.length + 1];
        System.arraycopy(this.items, 0, tmp, 0, this.items.length);
        tmp[tmp.length - 1] = item;
        this.items = tmp;
    }

    public Product[] getItems() {
        return this.items;
    }

    public int findIndexById(int id) {
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void removeById(int id) {
        int itemIndex = this.findIndexById(id);

        if (itemIndex >= 0) {
            Product[] tmp = new Product[this.items.length - 1];
            System.arraycopy(this.items, 0, tmp, 0, itemIndex);
            System.arraycopy(this.items, itemIndex + 1, tmp, itemIndex, this.items.length - itemIndex - 1);
            this.items = tmp;
        }
    }
}