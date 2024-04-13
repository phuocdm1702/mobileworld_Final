package mobileworld.model;

import mobileworld.model.*;
import javax.swing.Icon;

public class ModelMenu {

    String menuName;
    Icon icon;

    public ModelMenu(String menuName, Icon icon) {
        this.menuName = menuName;
        this.icon = icon;
    }

    public ModelMenu() {
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

}
