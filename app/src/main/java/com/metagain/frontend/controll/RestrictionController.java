package com.metagain.frontend.controll;

import com.metagain.frontend.network.controller.RestrictionNetworkController;

import java.util.UUID;

public interface RestrictionController {

    /**
     * ruft eine post-Funktion beim Network-Controller auf, um das angegebene
     * Profil für den user zu blockieren und entsprechende Änderungen in der Datenbank
     * vorzunehmen
     * @param profileID zu blockierendes Profil
     */
    public void block(UUID profileID);

    /**
     * ruft eine post-Funktion beim Network-Controller auf, um das angegebene
     * Profil für den user stumm zu schalten
     * @param profileID das stumm zu schaltende Profil
     */
    public void mute(UUID profileID);

    /**
     * ruft eine delete-Funktion beim Network-Controller auf, um die angegebene Restriction
     * aufzuheben
     * @param restrictionID der aufzuhebenden Restriction
     */
    public void delete(UUID restrictionID);

    /**
     * legt den zu nutzenden Network-Controller fest
     * @param restrictionNetworkController
     */
    public void insertNetworkController(RestrictionNetworkController restrictionNetworkController);
}
