package de.thm.mni.mote.mode.modelica;

/**
 * Created by hobbypunk on 20.10.16.
 */
public class MoConnector extends MoClass {
  MoConnector(ClassInformation classInformation, String name, MoClass parent) {
    super(classInformation, name, parent);
    PREFIX = "actors";
  }
}