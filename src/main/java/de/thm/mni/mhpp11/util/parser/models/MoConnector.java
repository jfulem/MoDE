package de.thm.mni.mhpp11.util.parser.models;

import de.thm.mni.mhpp11.util.parser.ClassInformation;

/**
 * Created by hobbypunk on 20.10.16.
 */
public class MoConnector extends MoClass {
  MoConnector(ClassInformation classInformation, String name, MoClass parent) {
    super(classInformation, name, parent);
    PREFIX = "t";
  }
}
