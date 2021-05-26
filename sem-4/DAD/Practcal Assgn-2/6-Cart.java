 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      6-Cart.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.util.Collections;
import java.util.List;

class CartItem {
    final Product product;
    final int qty;

    CartItem(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }
}

public class Cart {
    private List<CartItem> _items;

    public Cart(List<CartItem> items) {
        this._items = items;
    }

    public List<CartItem> items() {
        return Collections.unmodifiableList(_items);
    }
}