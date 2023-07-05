package org.essentialss.api.world.points.home;

import org.essentialss.api.utils.identifier.StringIdentifier;
import org.essentialss.api.world.points.SPoint;

public interface SHome extends SPoint, StringIdentifier {

    SHomeBuilder builder();

}
