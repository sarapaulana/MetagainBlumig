package com.metagain.frontend.controll.implementations;

import com.metagain.frontend.controll.MeetingController;
import com.metagain.frontend.exceptions.CoordinatesFormatException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.Meeting;
import com.metagain.frontend.network.MeetingNetworkController;
import com.metagain.frontend.network.controller.MeetingNetworkControllerImpl;

import java.util.List;
import java.util.UUID;

public class MeetingControllerImpl implements MeetingController {

    private final MeetingNetworkController meetingNetworkController = new MeetingNetworkControllerImpl();

    @Override
    public void updateMeetingPoint(Meeting meeting) throws CoordinatesFormatException, NetworkErrorException {
        meetingNetworkController.put(meeting);
    }

    @Override
    public void deleteMeeting(UUID meetingID) throws NetworkErrorException {
        meetingNetworkController.delete(meetingID);
    }

    @Override
    public List<Meeting> getMeetings() throws NetworkErrorException {
        return meetingNetworkController.get();
    }

    @Override
    public void insertNetworkController(MeetingNetworkController meetingNetworkController) {

    }
}
