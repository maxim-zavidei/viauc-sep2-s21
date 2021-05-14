package mediator;

import common.model.Product;
import common.network.RemoteClientInterface;
import common.network.RemoteServerInterface;
import model.Model;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client implements Model, RemoteClientInterface {

    private RemoteServerInterface server;

    public Client() throws Exception {
        server = (RemoteServerInterface) Naming.lookup("rmi://127.0.0.1:1099/access");
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public void stop() {
        try {
            server.deauthenticate(this);
            UnicastRemoteObject.unexportObject(this, true);
        } catch (Exception e) {
            // Do nothing.
        }
    }

    @Override
    public void authenticate(String email, String password) throws Exception {
        server = server.authenticate(this, email, password);
    }

    @Override
    public boolean deauthenticate() {
        try {
            server.deauthenticate(this);
        } catch (RemoteException e) {
            // Do nothing.
        }
        try {
            server = (RemoteServerInterface) Naming.lookup("rmi://127.0.0.1:1099/access");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<Product> getCatalogOfProducts() throws Exception {
        return server.getCatalogOfProducts();
    }


}