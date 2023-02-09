package org.essentialss.api.world.points.spawn;

import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.essentialss.api.world.points.SPoint;

public interface SSpawnPoint extends SPoint {

    UnmodifiableCollection<SSpawnType> types();

}
