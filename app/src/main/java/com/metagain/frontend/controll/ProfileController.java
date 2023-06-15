package com.metagain.frontend.controll;

import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.InvalidUsernameException;
import com.metagain.frontend.exceptions.LoginException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.UsernameAlreadyExistsException;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.network.controller.implementations.ProfileNetworkControllerImpl;

public interface ProfileController {

    /**
     * gibt die Daten an den Network-Controller, und sorgt dafür, dass in der Datenbank ein neues Profil
     * erstellt wird
     * @param ownProfile Profildaten
     * @throws InvalidEmailException wenn die angegebene email nicht der typischen Email-Form entspricht
     * @throws InvalidUsernameException wenn der username schon vergeben ist
     */
    public void createAccount(OwnProfile ownProfile) throws InvalidEmailException, NetworkErrorException, InvalidUsernameException, UsernameAlreadyExistsException;

    /**
     * gibt die neuen Daten an den Network-Controller und sorgt dafür, dass die Angaben in
     * der Datenbank aktualisiert werden
     * @param ownProfile Profildaten
     * @throws InvalidUsernameException wenn der username schon vergeben ist
     * @throws InvalidEmailException wenn die angegebene email nicht der typischen Email-Form entspricht
     */
    public void editProfile(OwnProfile ownProfile) throws InvalidUsernameException, InvalidEmailException;

    /**
     * gibt die neuen Daten an den Network-Controller und sorgt dafür, dass die Angaben in der Datenbank
     * aktualisiert werden
     * @param username Username
     * @throws InvalidUsernameException wenn der username schon vergeben ist
     * @throws NetworkErrorException wenn man keine Verbindung zum Internet hat um die Daten zu aktualisieren
     * @throws InvalidEmailException wenn die angegebene email nicht der typischen Email-Form entspricht
     */
    public void editUsername(String username) throws InvalidUsernameException, NetworkErrorException, UsernameAlreadyExistsException;

    /**
     * gibt die neuen Daten an den Network-Controller und sorgt dafür, dass die Angaben in der Datenbank
     * aktualisiert werden
     * @param email E-mail
     * @throws InvalidEmailException wenn die angegebene email nicht der typischen Email-Form entspricht
     * @throws NetworkErrorException wenn man keine Verbindung zum Internet hat um die Daten zu aktualisieren
     * @throws InvalidUsernameException wenn der username schon vergeben ist
     */
    public void editEmail(String email) throws InvalidEmailException, NetworkErrorException;

    /**
     * gibt die neuen Daten an den Network-Controller und sorgt dafür, dass die Angaben in der Datenbank
     * aktualisiert werden
     * @param password Passwort
     * @throws InvalidEmailException wenn die angegebene email nicht der typischen Email-Form entspricht
     * @throws InvalidUsernameException wenn der username schon vergeben ist
     * @throws NetworkErrorException wenn man keine Verbindung zum Internet hat um die Daten zu aktualisieren
     */
    public void editPassword(String password) throws NetworkErrorException;

    /**
     * ruft eine get-Fuktion beim Network-Controller auf; alle Profildaten werden geladen
     * @return das eigene Profil
     */
    public OwnProfile getOwnProfile();

    /**
     * ruft die get-Methode des Network-Controllers auf, die eine Authentifizierung verlangt
     * @param username angegeben
     * @param password angegeben
     * @throws LoginException wenn die angegebenen Daten zu keinem Profil in der Datenbank passen
     */
    public void login(String username, String password) throws LoginException, NetworkErrorException;

    /**
     * Logt den User aus und schickt einem zum Login Screen zurück
     */
    public void logout();

    /**
     * Löscht den User und alle dazugehörigen Daten aus der Datenbank
     * @throws NetworkErrorException wenn man keine Verbindung zum Internet hat um die Daten zu aktualisieren
     */
    public void delete() throws NetworkErrorException;

    public void setIncognito(boolean incognito) throws NetworkErrorException;

    /**
     * legt den zu nutzenden Network-Controller fest
     * @param profileNetworkController
     */
    public void insertNetworkController(ProfileNetworkControllerImpl profileNetworkController);

}
