package de.thm.mni.mhpp11.statemachine.states.connection;

import de.thm.mni.mhpp11.control.icon.MoDiagramGroup;
import de.thm.mni.mhpp11.control.icon.handlers.FocusHandler;
import de.thm.mni.mhpp11.shape.Line;
import de.thm.mni.mhpp11.statemachine.states.NoState;
import de.thm.mni.mhpp11.statemachine.states.State;
import de.thm.mni.mhpp11.statemachine.states.diagram.DiagramZoomState;
import de.thm.mni.mhpp11.util.ui.MyMouseEvent;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

/**
 * Created by hobbypunk on 16.11.16.
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = false)
public class ConnectionModifyState extends State<MouseEvent, Line> {
  
  final MoDiagramGroup parent;
  
  public ConnectionModifyState(MoDiagramGroup parent, Line source) {
    super(source);
    this.parent = parent;
  }
  
  @Override
  protected void initTransitions() {
    getTransitions().put(MyMouseEvent.MOUSE_DOUBLE_CLICKED, Arrays.asList(this.getClass()));
    getTransitions().put(MouseEvent.MOUSE_CLICKED, Arrays.asList(NoState.class, this.getClass(), ConnectionCreateState.class));
    getTransitions().put(MouseEvent.MOUSE_PRESSED, Arrays.asList(ConnectionMoveState.class));
    getTransitions().put(ScrollEvent.SCROLL, Arrays.asList(DiagramZoomState.class));
  }
  
  @Override
  public void enter() {
    FocusHandler.getInstance().setFocus(getSource());
    getMachine().getScene().setCursor(Cursor.HAND);
  }
  
  @Override
  public void exit() {
    FocusHandler.getInstance().clearFocus();
  }
  
  @Override
  protected void handleClicked(MouseEvent event) {
    Point2D mousePos = parent.convertScenePointToDiagramPoint(event.getSceneX(), event.getSceneY());
    
    if (event.getClickCount() == 2 || (event.getClickCount() == 1 && event.isShiftDown())) {
      Integer[] poses = findNearLinePos(mousePos, true);
      if (poses != null) {
        getSource().getData().getPoints().add(poses[1], mousePos);
      }
    } else if (event.getClickCount() == 1 && event.isControlDown()) {
      Integer pos = findNearPointPos(mousePos);
      if (pos != null) getSource().getData().getPoints().remove(pos.intValue());
    }
  }
  
  protected Integer findNearPointPos(Point2D point) {
    ObservableList<Point2D> points = getSource().getData().getPoints();
    for (int i = 1, size = points.size() - 1; i < size; i++) {       //first and last point is not moveable, it's bound to the connector!
      Point2D p = points.get(i);
      if (p.distance(point) < 5) {
        return i;
      }
    }
    return null;
  }
  
  protected Integer[] findNearLinePos(Point2D point) {
    return findNearLinePos(point, false);
  }
  
  protected Integer[] findNearLinePos(Point2D point, Boolean withBeginAndEnd) {
    ObservableList<Point2D> points = getSource().getData().getPoints();
    for (int i = (withBeginAndEnd) ? 0 : 1, j = (withBeginAndEnd) ? 1 : 2, size = points.size() - ((withBeginAndEnd) ? 0 : 1); j < size; i++, j++) {       //first and last source is not moveable, it's bound to the connector!
      Point2D p1 = points.get(i);
      Point2D p2 = points.get(j);
      if (Math.abs((p1.distance(point) + p2.distance(point)) - p1.distance(p2)) < 5) return new Integer[]{i, j};
    }
    return null;
  }
}
