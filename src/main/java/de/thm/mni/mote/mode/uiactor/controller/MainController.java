package de.thm.mni.mote.mode.uiactor.controller;

import de.thm.mni.mhpp11.jActor.actors.messagebus.MessageBus;
import de.thm.mni.mhpp11.jActor.actors.ui.interfaces.ActorController;
import de.thm.mni.mhpp11.jActor.actors.ui.messages.StartUIMessage;
import de.thm.mni.mhpp11.jActor.actors.ui.splash.messages.SplashShowMessage;
import de.thm.mni.mhpp11.jActor.messages.ExitMessage;
import de.thm.mni.mhpp11.jActor.messages.interfaces.Message;
import de.thm.mni.mote.mode.config.Settings;
import de.thm.mni.mote.mode.config.model.MainWindow;
import de.thm.mni.mote.mode.config.model.Project;
import de.thm.mni.mote.mode.modelica.MoContainer;
import de.thm.mni.mote.mode.modelica.MoRoot;
import de.thm.mni.mote.mode.modelica.Saver;
import de.thm.mni.mote.mode.omcactor.messages.GetDataOMCMessage;
import de.thm.mni.mote.mode.omcactor.messages.OMCLoadStatusUIMessage;
import de.thm.mni.mote.mode.omcactor.messages.UpdateClassOMCMessage;
import de.thm.mni.mote.mode.parser.ParserException;
import de.thm.mni.mote.mode.uiactor.control.DragResizer;
import de.thm.mni.mote.mode.uiactor.control.MainTabControl;
import de.thm.mni.mote.mode.uiactor.control.MoTreeCell;
import de.thm.mni.mote.mode.uiactor.handlers.LibraryHandler;
import de.thm.mni.mote.mode.uiactor.messages.OMCDataUIMessage;
import de.thm.mni.mote.mode.uiactor.statemachine.StateMachine;
import de.thm.mni.mote.mode.uiactor.utilities.TreeViewWithItemsWrapper;
import de.thm.mni.mote.mode.util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import javax.management.ReflectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Created by hobbypunk on 15.09.16.
 */
public class MainController extends NotifyController {
  
  private Project project;
  
  public static void configure(List<Object> params) throws ReflectionException, InvocationTargetException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException {
    UUID group = (UUID) params.get(0);
    ActorController controller = configure(group, Utilities.getView("Main"), Utilities.getBundle("Main"), params);
    controller.start();
  }
  
  @FXML private TextField tfLibFilter;
  
  @FXML private HBox hbLeft;
  @FXML private Separator sLeft;
  
  @FXML private HBox hbRight;
  @FXML private Separator sRight;
  
  @FXML private TabPane tabPane;
  
  @FXML private TreeView<MoContainer> tvLibrary;
  @FXML private TreeViewWithItemsWrapper<MoContainer> tvwiwLibrary;
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    super.initialize(location, resources);
    tvwiwLibrary = new TreeViewWithItemsWrapper<>(tvLibrary);
    DragResizer.makeResizable(sLeft, hbLeft, DragResizer.LTR);
    DragResizer.makeResizable(sRight, hbRight, DragResizer.RTL);
  }
  
  
  public void lateInitialize() throws InvocationTargetException, NoSuchMethodException, ReflectionException, InstantiationException, IllegalAccessException {
    super.lateInitialize();
    this.project = (Project) getParams().get(1);
    StateMachine.getInstance(this.getScene());
    initTreeView();
  }
  
  @Override
  public void deinitialize() {
    super.deinitialize();
  
    MainWindow mw = getSettings().getMainwindow();
    mw.setPos(getStage().getX(), getStage().getY());
    mw.setSize(getStage().getWidth(), getStage().getHeight());
    mw.setWidthLeftPane((int) hbLeft.getPrefWidth());
    mw.setWidthRightPane((int) hbRight.getPrefWidth());
  }
  
  @Override
  public void show() {
    getActor().send(new GetDataOMCMessage(getGroup()));
    MainWindow mw = getSettings().getMainwindow();
  
    getStage().setX(mw.getPos().getX());
    getStage().setY(mw.getPos().getY());
    getStage().setWidth(mw.getSize().getX());
    getStage().setHeight(mw.getSize().getY());
    
    hbLeft.setPrefWidth(mw.getWidthLeftPane());
    hbRight.setPrefWidth(mw.getWidthRightPane());
    getStage().setTitle(String.format("%1$s - %2$s %3$s", this.project.getName(), Settings.TITLE, Settings.VERSION));
    getStage().setResizable(true);
    getStage().show();
    getActor().send(new SplashShowMessage(false));
  }
  
  @Override
  public void start() {
    getActor().send(new SplashShowMessage(true));
  }
  
  private void initTreeView() {
    tvLibrary.setRoot(new TreeItem<>());
    tvLibrary.setShowRoot(false);
    
    tvLibrary.setOnMouseClicked(event -> {
      TreeItem<MoContainer> item = tvLibrary.getSelectionModel().getSelectedItem();
      if (item == null || !item.getValue().getElement().hasDiagram()) return;//TODO
      
      for (Tab t : tabPane.getTabs()) {
        if (t instanceof MainTabControl && ((MainTabControl) t).getData().equals(item.getValue())) {
          tabPane.getSelectionModel().select(t);
          return;
        }
      }
  
      if (event.getClickCount() == 2) {
        try {
          if (tabPane.getSelectionModel().getSelectedItem() != null && ((MainTabControl) tabPane.getSelectionModel().getSelectedItem()).isDiagram() && item.getValue().getElement().hasIcon())
            LibraryHandler.getInstance().handleMenu(tabPane, item.getValue(), "add_to_diagram");
          else
            LibraryHandler.getInstance().handleMenu(tabPane, item.getValue(), "open_as_diagram");
        } catch (ParserException e) {
          e.printStackTrace(); //TODO: send msg;
        }
      }
    });
    tvwiwLibrary.setTreeItemExpandListener(parent -> getActor().send(new UpdateClassOMCMessage(getGroup(), parent)));
    tvwiwLibrary.setTreeItemConfigurer((treeItem, value) -> {
      if (value instanceof MoRoot) {
        treeItem.setExpanded(true);
      }
    });
    tvLibrary.setCellFactory(param -> new MoTreeCell(tabPane));
  }
  
  @FXML
  private void handleSave() {
    if (!tabPane.getSelectionModel().isEmpty()) {
      handleSave(((MainTabControl) tabPane.getSelectionModel().getSelectedItem()).getData());
    }
    
  }
  
  @FXML
  private void handleSaveAll() {
    tabPane.getTabs().forEach(tab -> handleSave(((MainTabControl) tab).getData()));
  }
  
  private void handleSave(MoContainer container) {
    try {
      Saver.save(container);
    } catch (ParserException e) {
      e.printStackTrace(); //TODO: send msg
    }
  }
  
  @FXML
  private void handleCloseProject() {
    MessageBus.getInstance().send(new StartUIMessage(WelcomeController.class, getGroup()));
  }
  
  @FXML
  private void handleClose() {
    MessageBus.getInstance().send(new ExitMessage(getGroup()));
  }
  
  private static class MainActor extends NotifyActor<MainController, Message> {
    
    private MainActor(MainController controller) {
      super(controller);
    }
    
    @Override
    public void executeUI(Message msg) {
      super.executeUI(msg);
      if (msg instanceof OMCDataUIMessage) {
        getController().tvwiwLibrary.setItems(((OMCDataUIMessage) msg).getData());
      } else if (msg instanceof OMCLoadStatusUIMessage) {
        if (((OMCLoadStatusUIMessage) msg).getStatus() == OMCLoadStatusUIMessage.STATUS.COMPLETE) {
          getController().show();
        }
      }
    }
  }
}

