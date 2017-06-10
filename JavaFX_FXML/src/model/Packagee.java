package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

public class Packagee {

    private final StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
    private final StringProperty address = new SimpleStringProperty(this, "address", "");
    private final StringProperty speed = new SimpleStringProperty(this, "speed", "");
    private final StringProperty flow = new SimpleStringProperty(this, "flow", "");
    private final StringProperty agreement = new SimpleStringProperty(this, "agreement", "");

    public Packagee() {
    }

    public Packagee(String firstName, String lastName) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
    }

    public Packagee(String firstName, String lastName, String address, String speed, String flow, String agreement) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.address.set(address);
        this.speed.set(speed);
        this.flow.set(flow);
        this.agreement.set(agreement);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getSpeed() {
        return speed.get();
    }

    public void setSpeed(String speed) {
        this.speed.set(speed);
    }

    public StringProperty speedProperty() {
        return speed;
    }

    public String getFlow() {
        return flow.get();
    }

    public void setFlow(String flow) {
        this.flow.set(flow);
    }

    public StringProperty flowProperty() {
        return flow;
    }

    public String getAgreement() {
        return agreement.get();
    }

    public void setAgreement(String agreement) {
        this.agreement.set(agreement);
    }

    public StringProperty agreementProperty() {
        return agreement;
    }

    private final ObjectProperty<ArrayList<String>> errorList = new SimpleObjectProperty<>(this, "errorList", new ArrayList<>());

    public ObjectProperty<ArrayList<String>> errorsProperty() {
        return errorList;
    }

    public boolean isValid() {

        boolean isValid = true;

        if (firstName.get() != null && firstName.get().equals("")) {
            errorList.getValue().add("First name can't be empty!\n");
            isValid = false;
        }
        if (lastName.get().equals("")) {
            errorList.getValue().add("Last name can't be empty!\n");
            isValid = false;
        }
        if (address.get().equals("")) {
            errorList.getValue().add("Address can't be empty!\n");
            isValid = false;
        }
        if (speed.get().equals("")) {
            errorList.getValue().add("Speed can't be empty!\n");
            isValid = false;
        }
        if (flow.get().equals("")) {
            errorList.getValue().add("Flow can't be empty!\n");
            isValid = false;
        }
        if (agreement.get().equals("")) {
            errorList.getValue().add("Agreement can't be empty!\n");
            isValid = false;
        }

        return isValid;
    }

    public boolean save() throws ClassNotFoundException {

        if (isValid()) {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/internet_paketi", "root", "Bt15.01.86");) {

                Statement st = conn.createStatement();
                st.execute("insert into paketi (firstname,lastname,address,speed,flow,agreement)value('" + this.getFirstName() + "','" + this.getLastName() + "','" + this.getAddress() + "','" + this.getSpeed() + "','" + this.getFlow() + "','" + this.getAgreement() + "')");

            } catch (Exception exc) {
                System.out.println("Not a god conection :\n" + exc.getMessage());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Package is saved");
            alert.setHeaderText(null);
            alert.setContentText("PACKAGE WITH: \n" + this + "\nSAVED!\n");
            alert.showAndWait();

            System.out.println("PACKAGE WITH: \n" + this + "\nSAVED!\n");
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "First name: " + firstName.get() + "\n" + "Last name: " + lastName.get() + "\n" + "Address: " + address.get() + "\n" + "Speed: " + speed.get() + "\n" + "Flow: " + flow.get() + "\n" + "Agreement: " + agreement.get();
    }

    public static String listaPaketa() throws ClassNotFoundException {
        StringBuilder sviPaketi = new StringBuilder();
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/internet_paketi", "root", "Bt15.01.86");) {

            Statement st = conn.createStatement();
            st.executeQuery("select id,firstname,lastname,address,speed,flow,agreement,time from paketi");
            ResultSet rs = st.getResultSet();

            while (rs.next()) {
                sviPaketi.append(rs.getString("id"));
                sviPaketi.append(";    ");
                sviPaketi.append(rs.getString("firstname"));
                sviPaketi.append(";    ");
                sviPaketi.append(rs.getString("lastname"));
                sviPaketi.append(";    ");
                sviPaketi.append(rs.getString("address"));
                sviPaketi.append(";    ");
                sviPaketi.append(rs.getString("speed"));
                sviPaketi.append(";    ");
                sviPaketi.append(rs.getString("flow"));
                sviPaketi.append(";    ");
                sviPaketi.append(rs.getString("agreement"));
                sviPaketi.append(";    ");
                sviPaketi.append(rs.getString("time"));
                sviPaketi.append("\n");
            }

        } catch (Exception exc) {
            System.out.println("Not a god conection :\n" + exc.getMessage());
        }
        return sviPaketi.toString();
    }
}
