package de.thm.mni.mhpp11.modelica.annotations;

import de.thm.mni.mhpp11.modelica.graphics.MoCoordinateSystem;
import de.thm.mni.mhpp11.modelica.graphics.MoGraphic;
import de.thm.mni.mhpp11.parser.OMCompiler;
import de.thm.mni.mhpp11.parser.modelica.AnnotationParser;
import lombok.Builder;

import java.util.List;

/**
 * Created by hobbypunk on 26.10.16.
 */

public class MoDiagram extends MoIcon {
  
  @Builder(builderMethodName = "diagramBuilder")
  private MoDiagram(MoCoordinateSystem moCoordinateSystem, List<MoGraphic> moGraphics) {
    super(moCoordinateSystem, moGraphics);
  }
  
  public static MoIcon parse(OMCompiler omc, AnnotationParser.DiagramContext diagram) {
    MoDiagramBuilder mb = diagramBuilder();
    for (AnnotationParser.IconContentContext icc : diagram.iconContent()) {
      if (icc.coordinateSystem() != null) mb.moCoordinateSystem(MoCoordinateSystem.parse(icc.coordinateSystem()));
      if (icc.graphics() != null) mb.moGraphics(MoGraphic.parse(omc, icc.graphics().element()));
    }
    return mb.build();
  }
  
}