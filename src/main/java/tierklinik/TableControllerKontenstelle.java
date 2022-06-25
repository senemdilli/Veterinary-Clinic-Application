package tierklinik;

import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableControllerKontenstelle {
    @FXML
    private TableView<Zahlung> table;
    @FXML
    private TableColumn<Zahlung,Integer> col_zustand;
    @FXML
    private TableColumn<Person,String> col_tiername;
    @FXML
    private TableColumn<Person,String> col_hbname;
    @FXML
    private TableColumn<Person,String> col_nachname;
    @FXML
    private TableColumn<Zahlung,String> col_zahlungsart;
    @FXML
    private TableColumn<Zahlung,Double> col_zahlungsbetrag;

    static String query = null;
    static Connection connection = null;
    ResultSet resultSet = null;
    static ResultSet resultSet3 = null;
    static PreparedStatement preparedStatement;
    Zahlung zahlung = null;
    int id = 0;
    ObservableList<Zahlung> oblist = FXCollections.observableArrayList();

    public static int getZahlungid() throws SQLException {
        query = "SELECT * FROM zahlung";
        int zahlungid = 0;
        try {
            connection = FullDB.connect();
            preparedStatement = connection.prepareStatement(query);
            resultSet3 = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (resultSet3.next()) {
            zahlungid = resultSet3.getInt("zahlungid");
        }
        return zahlungid+1;
    }
    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/addZahlung.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void refreshTable() {
        try {
            oblist.clear();
            query = "SELECT * FROM zahlung";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Zahlung zahlung = new Zahlung(resultSet.getString("zahlungsart"), resultSet.getDouble("zahlungsbetrag"),
                        resultSet.getString("nachname"), resultSet.getString("hbname"),
                        resultSet.getString("tiername"));
                Zahlung.setZahlungid(resultSet.getInt("zahlungid"));
                zahlung.setZustand(resultSet.getString("zustand"));
                oblist.add(zahlung);
            }
            table.setItems(oblist);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void removeZahlung() {
        try {
            zahlung = table.getSelectionModel().getSelectedItem();
            id = Zahlung.getZahlungid();
            query = "DELETE FROM 'zahlung' WHERE zahlungid = " + id;
            connection = FullDB.connect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            refreshTable();
        } catch (SQLException e) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void loadDate() {
        try {
            connection = FullDB.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshTable();

        col_zustand.setCellValueFactory(new PropertyValueFactory<>("zustand"));
        col_tiername.setCellValueFactory(new PropertyValueFactory<>("tiername"));
        col_hbname.setCellValueFactory(new PropertyValueFactory<>("hbname"));
        col_nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        col_zahlungsart.setCellValueFactory(new PropertyValueFactory<>("zahlungsart"));
        col_zahlungsbetrag.setCellValueFactory(new PropertyValueFactory<>("zahlungsbetrag"));

        table.setItems(oblist);
    }

    @FXML
    private void makePaid() {
        try {
            zahlung = table.getSelectionModel().getSelectedItem();
            id = Termin.getTerminid();
            query = "UPDATE zahlung SET zustand = 'gezahlt' WHERE zahlungid = " + id;
            connection = FullDB.connect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            refreshTable();
        } catch (SQLException e) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void initialize() {
        loadDate();
    }
}
