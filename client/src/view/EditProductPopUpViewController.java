package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import viewmodel.AddProductPopUpViewModel;

public class EditProductPopUpViewController extends ViewController {
    @FXML
    public Label errorLabel;
    @FXML
    public TextField quantityField;
    @FXML
    public TextField nameField;
    @FXML
    public TextArea descriptionField;
    @FXML
    public TextField priceField;


    private ViewHandler viewHandler;
    private AddProductPopUpViewModel viewModel;


    @Override
    protected void init() {
        this.viewHandler = getViewHandler();
        this.viewModel = getViewModelFactory().getAddProductPopUpViewModel();

        errorLabel.textProperty().bind(viewModel.getErrorProperty());
        quantityField.textProperty().bindBidirectional(viewModel.getQuantityProperty(), new StringConverter<Integer>() {
            @Override
            public String toString(Integer integer) {
                return integer == null ? "" : integer.toString();
            }

            @Override
            public Integer fromString(String s) {
                return viewModel.checkerInteger(s);
            }
        });
        nameField.textProperty().bindBidirectional(viewModel.getNameProperty());
        descriptionField.textProperty().bindBidirectional(viewModel.getDescriptionProperty());
        priceField.textProperty().bindBidirectional(viewModel.getPriceProperty(), new StringConverter<Double>() {
            @Override
            public String toString(Double aDouble) {
                 return aDouble == null ? "" : aDouble.toString();
            }

            @Override
            public Double fromString(String s) {
                return viewModel.checkerDouble(s);
            }
        });

    }

    @Override
    protected void reset() {
        viewModel.reset();

    }

    public void editProduct() {
        try{
            //viewModel();
            viewHandler.openView(View.MANAGEPRODUCTS);
        }catch (Exception e)
        {
            errorLabel.textProperty().set(e.getMessage());
        }
    }

    public void cancelAddProduct() {
        try{
            viewHandler.openView(View.MANAGEPRODUCTS);
        }catch (Exception e)
        {
            errorLabel.textProperty().set(e.getMessage());
        }    }
}
