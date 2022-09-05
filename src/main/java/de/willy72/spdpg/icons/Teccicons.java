package de.willy72.spdpg.icons;

import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.icon.IconFactory;
import java.util.Locale;

@JsModule("./icons/teccicons.js")
public enum Teccicons implements IconFactory {
    GENERATOR, HILFE, IMPRESSUM;

    public Icon create() {
        return new Icon(this.name().toLowerCase(Locale.ENGLISH).replace('_', '-').replaceAll("^-", ""));
    }

    public static final class Icon extends com.vaadin.flow.component.icon.Icon {
        Icon(String icon) {
            super("teccicons", icon);
        }
    }
}