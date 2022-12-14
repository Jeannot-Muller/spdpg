package de.willy72.spdpg.views;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import de.willy72.spdpg.components.appnav.AppNav;
import de.willy72.spdpg.components.appnav.AppNavItem;
import de.willy72.spdpg.icons.Teccicons;
import de.willy72.spdpg.views.generator.GeneratorView;
import de.willy72.spdpg.views.hilfe.HilfeView;
import de.willy72.spdpg.views.impressum.ImpressumView;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassNames("view-toggle");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("view-title");

        Header header = new Header(toggle, viewTitle);
        header.addClassNames("view-header");
        return header;
    }

    private Component createDrawerContent() {
        HorizontalLayout appName = new HorizontalLayout();
        Icon spdLogo = Teccicons.SPD.create();
        appName.add(spdLogo);
        H6 spdlogoHeadline = new H6("PASSWORT GENERATOR");
        spdlogoHeadline.addClassNames("spd-logo");
        appName.add(spdlogoHeadline);
        appName.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        appName.addClassNames("app-name");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
                createNavigation(), createFooter());
        section.addClassNames("drawer-section");
        return section;
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();
        nav.addClassNames("app-nav");

        nav.addItem(new AppNavItem("Generator", GeneratorView.class, Teccicons.GENERATOR.create()));
        nav.addItem(new AppNavItem("Hilfe", HilfeView.class, Teccicons.HILFE.create()));
        nav.addItem(new AppNavItem("Impressum", ImpressumView.class, Teccicons.IMPRESSUM.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("app-nav-footer");

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
