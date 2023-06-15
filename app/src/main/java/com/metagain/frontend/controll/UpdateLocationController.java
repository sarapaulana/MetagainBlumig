package com.metagain.frontend.controll;

public interface UpdateLocationController {

    /**
     * Updatet die Location des Users
     * @param coordinates
     */
    public void updateLocation(double[] coordinates);

    public void updateLocationOnDestroy(double[] coordinates);

}
