package com.metagain.frontend.controll;

import com.metagain.frontend.exceptions.CoordinatesFormatException;
import com.metagain.frontend.network.MeetingNetworkController;

import java.util.UUID;

public interface MeetingController {

    /**
     * setzt eine neue Location für das Treffen, in dem eine put-Funktion
     * beim Network-Controller aufgerufen wird
     * @param location der neue Treffpunkt in Form eines double Arrays, der Koordinaten enthält
     * @throws CoordinatesFormatException falls das double-Array nicht der Koordinatenform entspricht
     */
    public void updateMeetingPoint(double[] location) throws CoordinatesFormatException;

    /**
     * löscht das Meeting aus der Datenbank, indem eine delete-Funktion beim Network-Controller aufgerufen wird
     * @param meetingID des zu löschenden Meetings
     */
    public void deleteMeeting(UUID meetingID);

    /**
     * legt den zu Nutzenden
     * @param meetingNetworkController
     */
    public void insertNetworkController(MeetingNetworkController meetingNetworkController);

}
