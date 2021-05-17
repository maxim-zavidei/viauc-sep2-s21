package viewmodel;

import model.Model;

public class ViewModelFactory {

    private AuthenticationViewModel authenticationViewModel;
    private RegistrationViewModel registrationViewModel;
    private CatalogViewModel catalogViewModel;
    private BasketViewModel basketViewModel;
    private CatalogBasketViewState catalogBasketViewState;
    private ManageProductsViewModel manageProductsViewModel;
    private AddProductPopUpViewModel addProductPopUpViewModel;
    private UserViewModel userViewModel;

    public ViewModelFactory(Model model) {
        this.authenticationViewModel = new AuthenticationViewModel(model);
        this.registrationViewModel = new RegistrationViewModel(model);
        this.catalogViewModel = new CatalogViewModel(model);
        this.basketViewModel = new BasketViewModel(model);
        this.catalogBasketViewState = new CatalogBasketViewState();
        this.userViewModel = new UserViewModel(null);
        this.manageProductsViewModel = new ManageProductsViewModel(model);
        this.addProductPopUpViewModel = new AddProductPopUpViewModel(model);
    }

    public AuthenticationViewModel getAuthenticationViewModel() {
        return authenticationViewModel;
    }

    public RegistrationViewModel getRegistrationViewModel() {
        return registrationViewModel;
    }

    public CatalogViewModel getCatalogViewModel() {
        return catalogViewModel;
    }

    public BasketViewModel getBasketViewModel() {
        return basketViewModel;
    }

    public CatalogBasketViewState getCatalogBasketViewState() {
        return catalogBasketViewState;
    }

    public ManageProductsViewModel getManageProductsViewModel() {
        return manageProductsViewModel;
    }

    public AddProductPopUpViewModel getAddProductPopUpViewModel() {
        return addProductPopUpViewModel;
    }

    public UserViewModel getEmployeeViewModel() {
        return userViewModel;
    }
}
