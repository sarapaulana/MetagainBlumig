package com.metagain.frontend.controll;

import com.metagain.frontend.model.Friends;
import com.metagain.frontend.network.FriendsNetworkController;

import java.util.List;
import java.util.UUID;

public interface FriendsController {

    /**
     * ruft eine get-Funktion beim Network-Controller auf, um alle Friends des users zu laden
     * @return Liste aller Friends
     */
    public List<Friends> getFriends();

    /**
     * löscht die angegebene Freundschaftsbeziehung aus der Datenbank,
     * indem eine delete-Funktion beim Network-Controller aufgerufen wird
     * @param friendsID id der Freundschaftsbeziehung, die gelöscht werden soll
     */
    public void deleteFriend(UUID friendsID);

    /**
     * gibt an, ob eine Freundschaftsbeziehung zwischen dem user und dem angegebenen Profil in der
     * Datenbank besteht
     * @param profileID des fremden Profils
     * @return ob die Freundschaftsbeziehung existiert
     */
    public boolean follows(UUID profileID);

    /**
     * legt den zu nutzenden Network-Controller fest
     * @param friendsNetworkController
     */
    public void insertNetworkController(FriendsNetworkController friendsNetworkController);
}
