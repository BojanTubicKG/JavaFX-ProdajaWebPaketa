package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

public class Packagee_Delete {

    private final StringProperty id = new SimpleStringProperty(this, "id", "");

    public Packagee_Delete() {
    }

    public Packagee_Delete(String id) {
        this.id.set(id);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return id;
    }

    @Override
    public String toString() {
        return "Id: " + id.get() + "\n";
    }

    private final ObjectProperty<ArrayList<String>> errorList = new SimpleObjectProperty<>(this, "errorList", new ArrayList<>());

    public ObjectProperty<ArrayList<String>> errorsProperty() {
        return errorList;
    }

    public boolean isValid() {

        boolean isValid = true;

        if (id.get() != null && id.get().equals("")) {
            errorList.getValue().add("Id can't be empty!\n");
            isValid = false;
        }
        return isValid;
    }

    public boolean delete() throws ClassNotFoundException {

        if (isValid()) {

            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/internet_paketi", "root", "Bt15.01.86");) {

                Statement st = conn.createStatement();
                st.execute("delete from paketi where id='" + Integer.valueOf(this.getId()) + "'");

            } catch (Exception exc) {
                System.out.println("Not a god conection :\n" + exc.getMessage());
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Package is remouved");
            alert.setHeaderText(null);
            alert.setContentText("PACKAGE WITH: \n" + this + "\nREMOVED!\n");
            alert.showAndWait();

            System.out.println("PACKAGE WITH: \n" + this + "REMOVED!\n");
            return true;
        }
        return false;
    }

}
