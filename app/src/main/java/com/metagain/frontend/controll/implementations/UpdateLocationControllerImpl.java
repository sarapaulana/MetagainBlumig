package com.metagain.frontend.controll.implementations;

import com.metagain.frontend.controll.UpdateLocationController;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.controller.LocationNetworkController;

public class UpdateLocationControllerImpl implements UpdateLocationController {

    LocationNetworkController locationNetworkController = new LocationNetworkController(NetworkConstants.AUTHORIZATION);

    @Override
    public void updateLocation(double[] coordinates) {
        try {
            locationNetworkController.put(coordinates);
        } catch (NetworkErrorException e) {
            //TODO
        }
    }

    @Override
    public void updateLocationOnDestroy(double[] coordinates) {
        try {
            locationNetworkController.put(coordinates);
        } catch (NetworkErrorException e) {
            //TODO
        }
    }


}