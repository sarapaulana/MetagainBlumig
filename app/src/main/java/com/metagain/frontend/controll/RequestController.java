package com.metagain.frontend.controll;

import com.metagain.frontend.exceptions.NotFriendsException;
import com.metagain.frontend.model.Request;

import java.util.UUID;

public interface RequestController {

    /**
     * sendet über den Network-Controller eine Anfrage ans Backend
     * @param request die Anfrage
     * @throws NotFriendsException falls der Request-Type MEET oder RADIUS und der user
     * dem angegebenen anderen user nicht folgt
     */
    public void sendRequest(Request request) throws NotFriendsException;

    /**
     * führt eine patch-Funktion beim Network-Controller aus,
     * die dafür sorgt, dass entsprechende Änderungen in der Datenbank durchgeführt werden
     * @param requestID id der akzeptierten Anfrage
     */
    public void acceptRequest(UUID requestID);

    /**
     * ruft eine delete-Funktion beim Network-Controller auf,
     * die dafür sorgt, dass die angegebene Request aus der Datenbank gelöscht wird
     * @param requestID id der zu löschenden Anfrage
     */
    public void deleteRequest(UUID requestID);

}
