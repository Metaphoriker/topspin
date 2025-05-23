package de.sommerfeld.topspin.fx.controller;

import com.google.inject.Inject;
import de.sommerfeld.topspin.fx.view.View;
import de.sommerfeld.topspin.fx.view.ViewProvider;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * Controller for the meta view.
 */
@View
public class TopspinMetaController {

    private final ViewProvider viewProvider;

    @FXML
    private HBox topBarPlaceholder;

    @FXML
    private AnchorPane centerContentPlaceholder;

    @FXML
    private HBox bottomBarPlaceholder;

    @Inject
    public TopspinMetaController(ViewProvider viewProvider) {
        this.viewProvider = viewProvider;
    }

    private static void setAnchor(Parent center) {
        AnchorPane.setTopAnchor(center, 0d);
        AnchorPane.setLeftAnchor(center, 0d);
        AnchorPane.setRightAnchor(center, 0d);
        AnchorPane.setBottomAnchor(center, 0d);
    }

    @FXML
    private void initialize() {
        registerViewListener();
        loadTopBar();
        loadPlanListView();
        loadBottomBar();

        bottomBarPlaceholder.requestFocus(); // to get away from the search field
    }

    private void registerViewListener() {
        viewProvider.registerViewChangeListener(TrainingPlanEditorMetaController.class, p -> loadEditor());
    }

    private void loadTopBar() {
        Parent topBar = viewProvider.requestView(TopBarController.class).parent();
        topBarPlaceholder.getChildren().add(topBar);
    }

    private void loadEditor() {
        loadCenter(TrainingPlanEditorMetaController.class);
    }

    private void loadPlanListView() {
        loadCenter(PlanListViewController.class);
    }

    private void loadBottomBar() {
        Parent bottomBar = viewProvider.requestView(BottomBarController.class).parent();
        bottomBarPlaceholder.getChildren().add(bottomBar);
    }

    private void loadCenter(Class<?> clazz) {
        Parent center = viewProvider.requestView(clazz).parent();
        setAnchor(center);
        centerContentPlaceholder.getChildren().setAll(center);
    }
}
