package com.metagain.frontend.network;

import com.metagain.frontend.exceptions.NetworkErrorException;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Response;

public class CallExecutor {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public <T> Response<T> execute(Call<T> call) throws NetworkErrorException {
        Callable<Response<T>> executeTask = () -> {
            Response<T> response;
            try {
                response = call.execute();
                System.out.println(response.code());
                return response;
            } catch (IOException e) {
                response = null;
            } catch (Throwable t) {
                response = null;
                //TODO
            }
            return response;
        };

        Future<Response<T>> futureResponse = executorService.submit(executeTask);
        try {
            Response<T> response = futureResponse.get();
            return response;
        } catch (ExecutionException e) {
            //TODO
        } catch (InterruptedException e) {
            //TODO
        }
        return null;
    }

}
