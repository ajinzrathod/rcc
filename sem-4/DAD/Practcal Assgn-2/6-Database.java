 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      6-Database.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.sql.*;
import java.util.*;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;

public class Database {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String USER = "root";
    static final String PASSWORD = "";

    static Product fetchProduct(int id, PrintWriter pw) {
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = con.createStatement()) {
            Class.forName(JDBC_DRIVER);
            ResultSet rs = stmt.executeQuery("SELECT * from products WHERE id=" + id);
            if (rs.next())
                return new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
                        rs.getDouble("price"));
        } catch (Exception e) {
            pw.println("Error: " + e.getMessage());
            return null;
        }
        return null;
    }

    static List<Product> fetchProducts(PrintWriter pw) {
        List<Product> products = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = connection.createStatement()) {
            products = new ArrayList<>();
            String sql = "SELECT * FROM products ORDER BY id";
            Class.forName(JDBC_DRIVER);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
                        rs.getDouble("price")));
            }
        } catch (Exception e) {
            pw.print("Error: " + e.getMessage());
            return null;
        }
        return products;
    }

    static void addToCart(String[] ids, PrintWriter pw) {
        Arrays.stream(ids).forEach(id -> {
            String sql = "INSERT INTO shopcart VALUES(" + Integer.parseInt(id) + ")";
            try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                    Statement stmt = con.createStatement()) {
                stmt.executeUpdate(sql);
            } catch (Exception e) {
                pw.print("Error: " + e.getMessage());
            }
        });
        pw.println("Added to Cart!");
    }

    static void removeFromCart(String[] ids, PrintWriter pw) {
        Arrays.stream(ids).forEach(id -> {
            String sql = "DELETE FROM shopcart WHERE productId=" + Integer.parseInt(id);
            try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                    Statement stmt = con.createStatement()) {
                stmt.executeUpdate(sql);
            } catch (Exception e) {
                pw.print("Error: " + e.getMessage());
            }
        });
        pw.println("Removed From Cart!");
    }

    static Cart fetchCart(PrintWriter pw) {
        List<CartItem> items = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = con.createStatement()) {
            Class.forName(JDBC_DRIVER);
            ResultSet rs = stmt
                    .executeQuery("SELECT productId, COUNT(productId) AS count FROM shopcart GROUP BY productId");
            while (rs.next()) {
                int id = rs.getInt("productId");
                int qty = rs.getInt("count");

                items.add(new CartItem(fetchProduct(id, pw), qty));
            }
        } catch (Exception e) {
            pw.println("Error: " + e.getMessage());
            return null;
        }

        return new Cart(items);
    }

    static double getTotalPrice(HttpSession session, PrintWriter pw) {
        double total = 0;

        Enumeration<String> attrs = session.getAttributeNames();

        while (attrs.hasMoreElements()) {
            String attr = attrs.nextElement();
            int count = Integer.parseInt(session.getAttribute(attr).toString());
            total += fetchProduct(Integer.parseInt(attr), pw).price * count;
        }

        return total;
    }
}