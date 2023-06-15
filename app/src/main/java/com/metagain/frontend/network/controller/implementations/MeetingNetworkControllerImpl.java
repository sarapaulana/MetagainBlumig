package com.metagain.frontend.network.controller.implementations;

import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.Meeting;
import com.metagain.frontend.network.CallExecutor;
import com.metagain.frontend.network.controller.MeetingNetworkController;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.services.MeetingNetworkService;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;

public class MeetingNetworkControllerImpl implements MeetingNetworkController {


    private MeetingNetworkService meetingNetworkService = NetworkConstants.createMeetingNetworkService();

    private CallExecutor callExecutor = new CallExecutor();

    @Override
    public List<Meeting> get() throws NetworkErrorException {
        Call<List<Meeting>> call = meetingNetworkService.get(NetworkConstants.AUTHORIZATION);
        Response<List<Meeting>> response =callExecutor.execute(call);
        if (response == null) {
            throw new NetworkErrorException();
        }

        return (List<Meeting>) response.body();
    }

    @Override
    public void delete(UUID meetingID) throws NetworkErrorException {
        Call<Void> call = meetingNetworkService.delete(NetworkConstants.AUTHORIZATION, meetingID);
        Response<Void> response = callExecutor.execute(call); //TODO ?
        if (response == null) {
            throw new NetworkErrorException();
        }

    }

    @Override
    public void put(Meeting meeting) throws NetworkErrorException {
        Call<Void> call = meetingNetworkService.put(NetworkConstants.AUTHORIZATION, meeting);
        Response<Void> response = callExecutor.execute(call); //TODO ?
        if (response == null) {
            throw new NetworkErrorException();
        }
    }

}
