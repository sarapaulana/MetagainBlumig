package com.metagain.frontend.network.controller;

import com.metagain.frontend.exceptions.InvalidUsernameException;
import com.metagain.frontend.exceptions.LoginException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.UsernameAlreadyExistsException;
import com.metagain.frontend.model.OwnProfile;

public interface ProfileNetworkController {

    public void post(OwnProfile ownProfile) throws NetworkErrorException, UsernameAlreadyExistsException;

    public OwnProfile get() throws NetworkErrorException, LoginException;

    public void delete() throws  NetworkErrorException;

    public void put(OwnProfile ownProfile) throws UsernameAlreadyExistsException, NetworkErrorException;

}
