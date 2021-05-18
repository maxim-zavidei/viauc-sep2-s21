package model;

import common.model.Product;
import common.model.User;
import common.model.UserList;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model {
    void stop();
    void authenticate(String email, String password) throws Exception;
    boolean deauthenticate();
    void register(String email, String password, String firstName, String lastName, LocalDate birthday, char gender) throws Exception;
    User getAuthenticatedUser() throws Exception;
    UserList getAllRegisteredUsers() throws Exception;
    void updateUser(String oldEmail, String newEmail, String password, String firstName, String lastName, LocalDate birthday, char gender, boolean isEmployee) throws Exception;
    void removeUser(String email) throws Exception;
    ArrayList<Product> getCatalogOfProducts() throws Exception;
    void addProduct(Product product) throws Exception;
    void updateProduct(Product product) throws Exception;
    void removeProduct(Product product) throws Exception;
}
