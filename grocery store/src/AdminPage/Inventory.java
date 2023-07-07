package AdminPage;

import javafx.beans.property.SimpleStringProperty;

public class Inventory {
    private String name;
    private String manager;
    private String address;
    private boolean protein;
    private boolean snack;
    private boolean drink;
    private boolean dairy;

    public Inventory(String name, String manager, String address, boolean protein, boolean snack, boolean drink,
            boolean dairy) {
        this.name = name;
        this.manager = manager;
        this.address = address;
        this.protein = protein;
        this.snack = snack;
        this.drink = drink;
        this.dairy = dairy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProtein(boolean protein) {
        this.protein = protein;
    }

    public void setSnack(boolean snack) {
        this.snack = snack;
    }

    public void setDrink(boolean drink) {
        this.drink = drink;
    }

    public void setDairy(boolean dairy) {
        this.dairy = dairy;
    }

    public String getName() {
        return name;
    }

    public String getManager() {
        return manager;
    }

    public String getAddress() {
        return address;
    }

    public boolean getProtein() {
        return protein;
    }

    public boolean getSnack() {
        return snack;
    }

    public boolean getDrink() {
        return drink;
    }

    public boolean getDairy() {
        return dairy;
    }
}