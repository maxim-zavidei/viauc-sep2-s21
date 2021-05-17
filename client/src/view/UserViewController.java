package view;

import common.model.DateTime;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.UserView;
import viewmodel.UserViewModel;

public class UserViewController extends ViewController
{
  /** FXML */
  @FXML private TableView<UserView> usersTable;
  @FXML private TableColumn<UserView, String> firstNameColumn;
  @FXML private TableColumn<UserView, String> lastNameColumn;
  @FXML private TableColumn<UserView, String> emailColumn;
  @FXML private TableColumn<UserView, DateTime> birthdayColumn;
  @FXML private TableColumn<UserView, String> genderColumn;
  @FXML private TableColumn<UserView, String> statusColumn;
  @FXML private Label errorLabel;

  /***/
  private ViewHandler viewHandler;
  private UserViewModel viewModel;
  private ViewState viewState;

  /** Constructor */
  public UserViewController()
  {
  }

  /** Initializer */
  @Override protected void init()
  {
    viewHandler = getViewHandler();
    viewModel = getViewModelFactory().getUserViewModel();
    viewState = getViewState();
    this.usersTable.setItems(viewModel.getList());
    this.firstNameColumn
        .setCellValueFactory(cellData -> cellData.getValue().getFirstName());
    this.lastNameColumn
        .setCellValueFactory(cellData -> cellData.getValue().getLastName());
    this.emailColumn
        .setCellValueFactory(cellData -> cellData.getValue().getEmail());
    this.birthdayColumn
        .setCellValueFactory(cellData -> cellData.getValue().getBirthDate());
    this.genderColumn
        .setCellValueFactory(cellData -> cellData.getValue().getGender());
    this.statusColumn
        .setCellValueFactory(cellData -> cellData.getValue().getStatus());
    this.errorLabel.textProperty().bind(viewModel.getErrorProperty());
  }

  @Override protected void reset()
  {
    usersTable.getSelectionModel().clearSelection();
    errorLabel.setStyle("-fx-text-fill:#000000");
    viewState.setSelectedUser("");
    viewModel.reset();
  }

  /** FXML Methods */
  @FXML private void makeEmployeeButton()
  {
    try
    {
      viewState.setSelectedUser(usersTable.getSelectionModel().getSelectedItem().getEmail().get());
      viewModel.makeEmployee(viewState.getSelectedUser());
    }
    catch (Exception e)
    {
      errorLabel.setText("Please select a user to promote first!");
    }
  }

  @FXML private void removeUser()
  {
    try
    {
      viewState.setSelectedUser(usersTable.getSelectionModel().getSelectedItem().getEmail().get());
      viewModel.deleteUser(viewState.getSelectedUser());
    }
    catch (Exception e)
    {
      errorLabel.setText("Please select a user to remove first!");
    }
  }

  @FXML private void fireEmployeeButton()
  {
    try
    {
      viewState.setSelectedUser(usersTable.getSelectionModel().getSelectedItem().getEmail().get());
      viewModel.fireEmployee(viewState.getSelectedUser());
    }
    catch (Exception e)
    {
      errorLabel.setText("Please select a user to resign first!");
    }
  }

  @FXML private void addEditUser()
  {
    try
    {
      if(usersTable.getSelectionModel().getSelectedItem().getEmail().get()!=null)
      viewState.setSelectedUser(usersTable.getSelectionModel().getSelectedItem().getEmail().get());
      viewModel.addEdit(viewState.getSelectedUser());
    }
    catch (Exception e)
    {
      errorLabel.setText("Something went wrong with modifying of user.");
    }
  }

  @FXML private void backToUsers()
  {
    viewModel.backToUsers();
  }

  @FXML private void deauthenticate()
  {
    //Max's code
    if (!viewModel.deauthenticate())
    {
      viewModel.getErrorProperty().set("!Could not deauthenticate the user.");
      return;
    }
    try
    {
      viewHandler.openView(View.AUTHENTICATION);
    }
    catch (Exception e)
    {
      viewModel.getErrorProperty()
          .set("!Could not logout at this time. Try later.");
    }
  }
}
