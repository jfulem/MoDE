package de.thm.mni.mote.mode.config.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.simpleframework.xml.Element;

/**
 * Created by hobbypunk on 14.09.16.
 */
@Getter
@Setter
@ToString
public class Notification {
  @Element
  @NonNull Integer seconds = 5;
}
