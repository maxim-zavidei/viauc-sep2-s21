package mediator;

import common.model.Product;
import common.model.User;
import common.model.UserList;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ClientTarget {
    void stop();
    void authenticate(String email, String password) throws Exception;
    boolean deauthenticate();
    void register(String email, String password, String firstName, String lastName, LocalDate birthday, char gender) throws Exception;
    User getAuthenticatedUser() throws Exception;
    ArrayList<Product> getCatalogOfProducts() throws Exception;
    void addProduct(Product product) throws Exception;
    UserList getUsers() throws Exception;
}
