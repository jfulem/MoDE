package de.thm.mni.mhpp11.controller;

import de.thm.mni.mhpp11.control.DragResizer;
import de.thm.mni.mhpp11.control.TreeViewWithItems;
import de.thm.mni.mhpp11.control.icon.MoDiagramPane;
import de.thm.mni.mhpp11.control.icon.MoIconPane;
import de.thm.mni.mhpp11.control.icon.MoPane;
import de.thm.mni.mhpp11.util.config.Settings;
import de.thm.mni.mhpp11.util.config.model.MainWindow;
import de.thm.mni.mhpp11.util.config.model.Project;
import de.thm.mni.mhpp11.util.parser.OMCompiler;
import de.thm.mni.mhpp11.util.parser.PackageParser;
import de.thm.mni.mhpp11.util.parser.models.MoClass;
import de.thm.mni.mhpp11.util.parser.models.MoRoot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hobbypunk on 15.09.16.
 */
public class MainController extends NotifyController {
  
  private Project project;
  
  @FXML private TextField tfLibFilter;
  
  @FXML private HBox hbLeft;
  @FXML private Separator sLeft;
  
  @FXML private HBox hbRight;
  @FXML private Separator sRight;
  
  @FXML private TreeViewWithItems<MoClass> tvLibrary;
  
  @FXML private AnchorPane main;
  
  private ObservableList<MoClass> data = FXCollections.observableArrayList();
  private MoRoot mrSystemLibraries = new MoRoot("System Libraries");
  private MoRoot mrProjectLibraries = new MoRoot("Project Libraries");
  private MoRoot mrProject = new MoRoot("Project");
  
  public MainController() {
    data.addAll(mrSystemLibraries, mrProjectLibraries, mrProject);
  }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    super.initialize(location, resources);
    
    DragResizer.makeResizable(sLeft, hbLeft, DragResizer.LTR);
    DragResizer.makeResizable(sRight, hbRight, DragResizer.RTL);
  }
  
  public void lateInitialize(Stage stage, Scene scene, Project project) {
    super.lateInitialize(stage, scene);
    this.project = project;
    initTreeView();
  }
  
  @Override
  public void deinitialize() {
    super.deinitialize();
    
    MainWindow mw = settings.getMainwindow();
    mw.setPos((int) stage.getX(), (int) stage.getY());
    mw.setSize((int) stage.getWidth(), (int) stage.getHeight());
    mw.setWidthLeftPane((int) hbLeft.getPrefWidth());
    mw.setWidthRightPane((int) hbRight.getPrefWidth());
  }
  
  @Override
  public void show() {
    stage.hide();
    MainWindow mw = settings.getMainwindow();
    
    stage.setX(mw.getPos().getX());
    stage.setY(mw.getPos().getY());
    stage.setWidth(mw.getSize().getX());
    stage.setHeight(mw.getSize().getY());
    
    hbLeft.setPrefWidth(mw.getWidthLeftPane());
    hbRight.setPrefWidth(mw.getWidthRightPane());
    
    stage.setScene(scene);
    stage.setTitle(String.format("%1$s - %2$s %3$s", project.getName(), Settings.NAME, Settings.VERSION));
    stage.setResizable(true);
    stage.show();
  }
  
  private void initTreeView() {
    tvLibrary.setRoot(new TreeItem<>());
    tvLibrary.setShowRoot(false);
    tvLibrary.setOnMouseClicked(event -> {
      TreeItem<MoClass> item = tvLibrary.getSelectionModel().getSelectedItem();
      if (item == null) return;
      MoPane mp = null;
    
      if (event.getClickCount() == 2)
        mp = new MoDiagramPane(item.getValue());
      if (event.getClickCount() == 3)
        mp = new MoIconPane(item.getValue(), false);
      if (mp == null || event.getClickCount() <= 1) return;
    
      main.getChildren().removeAll(main.getChildren());
    
      mp.scaleTo(800., 800.);
      mp.setLayoutX(20.);
      mp.setLayoutY(20.);
      mp.setInternalStyle("-fx-background-color: gainsboro;");
      main.getChildren().add(mp);
    
    });
    tvLibrary.setTreeItemExpandListener(parent -> parent.update(OMCompiler.getInstance()));
    tvLibrary.setTreeItemConfigurer((treeItem, value) -> {
      if (value instanceof MoRoot) {
        treeItem.setExpanded(true);
      }
    });
    tvLibrary.setItems(data);

    tvLibrary.setCellFactory(new Callback<TreeView<MoClass>, TreeCell<MoClass>>() {
      @Override
      public TreeCell<MoClass> call(TreeView<MoClass> param) {
        return new TreeCell<MoClass>() {
          @Override
          protected void updateItem(MoClass item, boolean empty) {
            super.updateItem(item, empty);
            setDisable(false);
            setStyle(null);
            if (empty) {
              setText(null);
              setGraphic(null);
            } else {
              setText(item.getSimpleName());
              if (item.hasConnectors()) setStyle("-fx-font-weight: bold");
              if (item instanceof MoRoot) {
                setDisable(true);
                setStyle("-fx-background-color: gainsboro;-fx-font-weight: bold; -fx-font-size: 90%; -fx-padding: 2 -15;");
              } else {
                setGraphic(new MoIconPane(item).scaleTo(25., 25.));
              }
            }
          }
        };
      }
    });
    initLibs();
    initProject();
  }
  
  private void initLibs() {
    PackageParser.collectSystemLibs(OMCompiler.getInstance(), mrSystemLibraries);
  }
  
  private void initProject() {
    PackageParser.collectProjectLibs(OMCompiler.getInstance(), mrProjectLibraries, project.getFile());
    PackageParser.collectProject(OMCompiler.getInstance(), mrProject, project.getFile());
  }
}
