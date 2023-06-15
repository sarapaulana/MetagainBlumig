package com.metagain.frontend.network.controller;

import com.metagain.frontend.exceptions.NetworkErrorException;

public interface LocationNetworkController {

    public void put(double[] location) throws NetworkErrorException;

    public void putOnDestroy(double[] location) throws NetworkErrorException;

}
