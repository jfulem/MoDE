package de.thm.mni.mote.mode.uiactor.control.modelica;

import de.thm.mni.mote.mode.modelica.MoVariable;
import de.thm.mni.mote.mode.modelica.graphics.MoSimpleExtent;
import de.thm.mni.mote.mode.modelica.graphics.MoText;
import de.thm.mni.mote.mode.modelica.graphics.MoTransformation;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hobbypunk on 19.09.16.
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MoConnectorIconGroup extends MoGroup {
  Translate origin = new Translate();
  Rotate rotation = new Rotate();
  Affine transformation = new Affine();
  
  
  @Getter @NonNull private final MoVariableIconGroup moParent;
  @Getter @NonNull private final MoVariable variable;
  
  public MoConnectorIconGroup(MoVariableIconGroup moParent, MoVariable variable) {
    super(variable.getType());
    this.moParent = moParent;
    this.variable = variable;
    init();
  }
  
  protected void initImage() {
    this.getMoClass().getIcon().getMoGraphics().stream().filter(mg -> !(mg instanceof MoText)).forEach(this::initImage);
    initTransformation();
  }
  
  private void initTransformation() {
    MoTransformation mt;
    mt = this.variable.getPlacement().getIconTransformation();
    if (mt == null) mt = this.variable.getPlacement().getDiagramTransformation();
    if (mt == null) return;
    
    mt.getExtent().setIconExtent((MoSimpleExtent) this.getMoClass().getIconCoordinateSystem().getExtent());
    
    final ObjectProperty<Point2D> origin = mt.getOrigin();
    
    this.origin.setX(origin.get().getX());
    this.origin.setY(origin.get().getY());
    
    this.scaleToFactor(mt.getExtent().getScaleX(), mt.getExtent().getScaleY(), true);
    
    this.getTransforms().addAll(this.origin, this.rotation, this.transformation);
    
    this.transformation.append(Transform.translate(Math.min(mt.getExtent().getP1().getX(), mt.getExtent().getP2().getX()), Math.min(mt.getExtent().getP1().getY(), mt.getExtent().getP2().getY())));
    
    if (mt.getExtent().isFlipX()) {
      this.transformation.append(Transform.translate(mt.getExtent().getWidth(), 0));
      this.transformation.append(Transform.scale(-1, 1));
    }
    
    this.transformation.append(Transform.translate(0, mt.getExtent().getHeight()));
    this.transformation.append(Transform.scale(1, -1));
    
    this.rotation.setAngle(mt.getRotation().get());
  }
  
  public List<MoVariable> getVariables() {
    List<MoVariable> list = new ArrayList<>();
    list.add(this.variable);
    return getVariables(this.moParent, list);
  }
  
  private List<MoVariable> getVariables(Node that, List<MoVariable> list) {
    if (that instanceof MoDiagramGroup) return list;
    if (that instanceof MoVariableIconGroup) list.add(0, ((MoVariableIconGroup) that).getVariable());
    return getVariables(that.getParent(), list);
  }
}
