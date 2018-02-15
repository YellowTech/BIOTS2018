//old utility for async tasks, not needed anymore

package com.yellowtech.web3jproject;

import android.os.AsyncTask;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * Created by Oliver on 15.02.18.
 */



public class NetworkThread extends AsyncTask<SimpleForward, Void, TransactionReceipt> {

    @Override
    protected TransactionReceipt doInBackground(SimpleForward... myContracts) {
        SimpleForward myContract = myContracts[0];
        TransactionReceipt receipt = null;
        try {
            receipt = myContract.createNewParticipant("0x1EfEDB49CfeDF954A50129646F8b931F6abe7339").send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receipt;
    }

    protected void onPostExecute(TransactionReceipt result) {

    }
}
