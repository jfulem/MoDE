package de.thm.mni.mote.mode.uiactor.controller.dialogs.newproject.fragments;

import de.thm.mni.mhpp11.jActor.actors.logging.messages.WarnMessage;
import de.thm.mni.mhpp11.jActor.actors.messagebus.MessageBus;
import de.thm.mni.mote.mode.config.Settings;
import de.thm.mni.mote.mode.config.model.Project;
import de.thm.mni.mote.mode.parser.PackageParser;
import de.thm.mni.mote.mode.uiactor.control.NewProject;
import de.thm.mni.mote.mode.util.Utilities;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.UUID;

import static de.thm.mni.mote.mode.util.Translator.tr;

/**
 * Created by hobbypunk on 26.01.17.
 */

public class ProjectFromSourceController extends GridPane implements NewProject, Initializable {
  private ResourceBundle i18n = null;
  
  private final BooleanProperty isNameValidProperty = new SimpleBooleanProperty(false);
  private final BooleanProperty isPathValidProperty = new SimpleBooleanProperty(false);
  @Getter private final BooleanProperty isValidProperty = new SimpleBooleanProperty(false);
  @Setter private Project.ProjectBuilder projectBuilder = Project.builder();
  
  @FXML private TextField tfName;
  @FXML private TextField tfPath;
  @FXML private Button btnPath;
  private UUID group;
  
  
  public ProjectFromSourceController(UUID group) {
    super();
    this.group = group;
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Utilities.getFragmentView("ProjectFromSource"));
    try {
      i18n = Utilities.getBundle("MoDE");
      loader.setResources(i18n);
    } catch (MissingResourceException e) {
      MessageBus.getInstance().send(new WarnMessage(this.getClass(), "Cant load ResourceBundle"));
    }
    loader.setRoot(this);
    loader.setController(this);
    
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ChangeListener<Boolean> listener = (observable, oldValue, newValue) -> isValidProperty.set(isNameValidProperty.get() && isPathValidProperty.get());
    
    isNameValidProperty.addListener(listener);
    isPathValidProperty.addListener(listener);
    isValidProperty.addListener((observable, oldValue, newValue) -> {
      if (!newValue) return;
      updateBuilder();
    });
    
    
    tfName.textProperty().addListener((observable, oldValue, newValue) -> {
      //todo check if already exists!
      if (newValue.isEmpty()) {
        this.projectBuilder.name(null);
        isNameValidProperty.set(false);
      } else {
        isNameValidProperty.set(true);
        updateBuilder();
      }
    });
    
    tfPath.textProperty().addListener((observable, oldValue, newValue) -> {
      isPathValidProperty.set(false);
      this.projectBuilder.moFile(null);
      Path oldPath = Paths.get(oldValue);
      Path newPath = Paths.get(newValue);
      
      if (Files.exists(newPath)) {
        isPathValidProperty.set(true);
        updateBuilder();
  
        if (oldPath.toString().endsWith("package.mo")) oldPath = oldPath.getParent();
        if (newPath.toString().endsWith("package.mo")) newPath = newPath.getParent();

        String oldName = oldPath.getFileName().toString().replaceAll("\\.mo$", "");
        if (tfName.getText().isEmpty() || tfName.getText().equals(oldName)) {
          tfName.setText(newPath.getFileName().toString().replaceAll("\\.mo$", ""));
          tfPath.positionCaret(tfName.getText().length() + 1);
        }
      }
    });
  }
  
  private void updateBuilder() {
    Path path = Paths.get(tfPath.getText()).getParent();
    this.projectBuilder.isNewProject(false)
        .moFile(Paths.get("./" + Paths.get(tfPath.getText()).getFileName()))
        .name(tfName.getText())
        .projectPath(path.resolve(tfName.getText() + ".mp"));
  }
  
  
  @FXML
  private void onChoosePathClick() {
    FileChooser fc = new FileChooser();
    fc.setTitle(tr(i18n, "project.choose.mo.file"));
    fc.setInitialDirectory(Settings.load().getRecent().getLastPath().toFile());
    fc.setInitialFileName("package.mo");
    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(tr(i18n, "file.modelica"), "*.mo"));
    File f = fc.showOpenDialog(this.getScene().getWindow());
    if (f == null || !f.exists()) return;
    Path path = PackageParser.findBasePackage(group, f.toPath().toAbsolutePath().normalize());
    tfPath.setText(path.toString());
    tfPath.positionCaret(tfPath.getText().length() + 1);
  }
}
