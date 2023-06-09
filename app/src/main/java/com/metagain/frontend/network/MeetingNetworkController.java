package com.metagain.frontend.network;

import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.Meeting;

import java.util.List;
import java.util.UUID;

public interface MeetingNetworkController {


    public List<Meeting> get() throws NetworkErrorException;

    public void delete(UUID meetingID) throws NetworkErrorException;

}
