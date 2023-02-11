package org.essentialss.api.world.points.warp;

import org.essentialss.api.utils.identifier.StringIdentifier;
import org.essentialss.api.world.points.SPoint;

public interface SWarp extends SPoint, StringIdentifier {

    SWarpBuilder builder();

}
