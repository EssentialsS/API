package org.essentialss.api.world;

import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.spongepowered.api.world.chunk.WorldChunk;
import org.spongepowered.math.vector.Vector3i;

import java.util.Optional;

public interface SPreGenData {

    SWorldData worldData();
    Vector3i center();

    double radius();

    UnmodifiableCollection<WorldChunk> completedChunks();

    double completedRadius();

    default double diameter(){
        return this.radius() * 2;
    }

    default Optional<WorldChunk> centerChunk(){
        return this.worldData().spongeWorld().map(world -> world.chunk(this.center()));
    }

}
