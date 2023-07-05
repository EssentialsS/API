package org.essentialss.api.group;

import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.spongepowered.api.service.permission.Subject;

import java.util.Optional;

public interface GroupManager {

    Group defaultGroup();

    Group group(Subject subject);

    default Optional<Group> group(String name) {
        return this.groups().stream().filter(group -> group.groupName().equalsIgnoreCase(name)).findAny();
    }

    UnmodifiableCollection<Group> groups();

    Group register(String groupName);

    void unregister(String groupName);

}
