package org.essentialss.api.world.points.jail;

import org.essentialss.api.utils.identifier.StringIdentifier;
import org.essentialss.api.world.points.SPoint;

public interface SJailSpawnPoint extends SPoint, StringIdentifier {

    SJailSpawnPointBuilder builder();

}
