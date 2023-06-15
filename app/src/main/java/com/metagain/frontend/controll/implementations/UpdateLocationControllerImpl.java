package com.metagain.frontend.controll.implementations;

import com.metagain.frontend.controll.UpdateLocationController;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.controller.implementations.LocationNetworkControllerImpl;

public class UpdateLocationControllerImpl implements UpdateLocationController {

    LocationNetworkControllerImpl locationNetworkControllerImpl = new LocationNetworkControllerImpl(NetworkConstants.AUTHORIZATION);

    @Override
    public void updateLocation(double[] coordinates) {
        try {
            locationNetworkControllerImpl.put(coordinates);
        } catch (NetworkErrorException e) {
            //TODO
        }
    }

    @Override
    public void updateLocationOnDestroy(double[] coordinates) {
        try {
            locationNetworkControllerImpl.put(coordinates);
        } catch (NetworkErrorException e) {
            //TODO
        }
    }


}
