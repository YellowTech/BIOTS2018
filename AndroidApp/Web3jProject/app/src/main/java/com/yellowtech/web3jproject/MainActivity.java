package com.yellowtech.web3jproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button buttonTransaction;
    private Button buttonBalance;
    private Button buttonGetTokens;
    private EditText editTextTransactionAmount;
    private EditText editTextTransactionAddress;
    private TextView textViewBalance;
    private TextView textViewTop;
    private TextView textViewID;
    private ProgressBar ProgressBarGetTokens;
    private File file;
    private Web3j web3j;
    private Credentials cred;
    private Handler mHandler;
    private boolean mHandlerIsRunning;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init
        web3j = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/JZce9Dwckm7OUMf7j9xZ"));
        final Context context = getApplicationContext();

        //UI
        buttonTransaction = (Button) findViewById(R.id.button);
        buttonBalance = (Button) findViewById(R.id.buttonBalance);
        buttonGetTokens = (Button) findViewById(R.id.buttonGetTokens);
        editTextTransactionAmount = (EditText) findViewById(R.id.editText);
        editTextTransactionAddress = (EditText) findViewById(R.id.editText2);
        textViewTop = (TextView) findViewById(R.id.textView);
        textViewBalance = (TextView) findViewById(R.id.textViewBalance);
        textViewID = (TextView) findViewById(R.id.textView2);
        ProgressBarGetTokens = (ProgressBar) findViewById(R.id.progressBar2);

        //Stuff
        mHandler = new Handler();
        mHandlerIsRunning = false;

        //Wallet Creation does not work :(
//        File Walletfile = new File(getApplicationContext().getFilesDir(), "TokenWallet");
//        if(!Walletfile.exists()){
//            String out = "";
//            try {
//                //String walletFileName = WalletUtils.generateFullNewWalletFile("secure", new File("TokenWallet"));
//                //WalletUtils.generateNewWalletFile("secure", Walletfile, false);
//                //out = WalletUtils.generateLightNewWalletFile("secure", Walletfile);
//                out = WalletUtils.generateNewWalletFile("secure", Walletfile, true);
//
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//            Toast.makeText(this, "Tokenwallet created with ad: "+out, Toast.LENGTH_SHORT).show();
//            //Walletfile.renameTo("TokenWallet");
//        }


        //Hardcoded Wallet
        //Password: JKKIMGZJUHDUJCAI
        //Private Key: 06a6a1e660f32d24b8feb9f03c236f10c947825924c664b07dbb34a88eb403b8

        /* JSON:
        {"version":3,"id":"50bc8504-4092-402a-b612-802b85c88995","address":"aedc00aa660b40e763b3237225366eff7190b452","Crypto":{"ciphertext":"a9fae5ffad0f3a561624b659160a569c0f2cba1016ed92988829e8b8e91344a5","cipherparams":{"iv":"406621c63644cb436c12f25276ffa8f8"},"cipher":"aes-128-ctr","kdf":"scrypt","kdfparams":{"dklen":32,"salt":"f4cc0b8155d62ee087905e52f00a0ce9fcbe133b5edca12148ce5381b9a69b18","n":8192,"r":8,"p":1},"mac":"1e1d7ee2f4a32520c2de42ba0c13eb312d89f50d037208836c62c68a818564b0"}}
         */

        file = new File(getApplicationContext().getFilesDir(), "JSON");
        if(file.exists()){
            file.delete();
        }

        String data = "{\"version\":3,\"id\":\"50bc8504-4092-402a-b612-802b85c88995\",\"address\":\"aedc00aa660b40e763b3237225366eff7190b452\",\"Crypto\":{\"ciphertext\":\"a9fae5ffad0f3a561624b659160a569c0f2cba1016ed92988829e8b8e91344a5\",\"cipherparams\":{\"iv\":\"406621c63644cb436c12f25276ffa8f8\"},\"cipher\":\"aes-128-ctr\",\"kdf\":\"scrypt\",\"kdfparams\":{\"dklen\":32,\"salt\":\"f4cc0b8155d62ee087905e52f00a0ce9fcbe133b5edca12148ce5381b9a69b18\",\"n\":8192,\"r\":8,\"p\":1},\"mac\":\"1e1d7ee2f4a32520c2de42ba0c13eb312d89f50d037208836c62c68a818564b0\"}}";
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("JSON", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        try {
            cred = WalletUtils.loadCredentials("JKKIMGZJUHDUJCAI", file);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Load Contract
        //final SimpleForward myContract = SimpleForward.load("0x0c5752dD7C44185d0ffE198691BFE59888b0FbE5", web3j, cred, new BigInteger("22000000000"), new BigInteger("250000"));
        final Token ContractSimple = Token.load("0xe42c4c8226ffb9e3626786da1cd231683cfe9c47", web3j, cred, new BigInteger("22000000000"), new BigInteger("250000"));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        //Load ID
        SharedPreferences settings = getPreferences(0);
        id = settings.getLong("id", -1);

        //if ID never initialized, do it
        if(id==-1){
            try {
                id = Long.parseLong(ContractSimple.availableId().send().toString());
                TransactionReceipt receipt = null;

                receipt = ContractSimple.register().send();

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                throw new RuntimeException("Should not happen");
            }


            SharedPreferences.Editor editor = settings.edit();
            editor.putLong("id", id);

            // Commit the edits!
            editor.commit();
        }
        textViewID.setText("ID="+id);


        //Button for transaction of tokens to other ids
        buttonTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHandlerIsRunning){
                    Toast.makeText(context, "Please wait a bit longer", Toast.LENGTH_SHORT).show();
                } else {
                    ContractSimple.sendTokens(new BigInteger(id+""),
                            new BigInteger("0"+editTextTransactionAddress.getText().toString()),
                            new BigInteger("0"+editTextTransactionAmount.getText().toString())).sendAsync();

                    Toast.makeText(context, "Tokens Sending", Toast.LENGTH_SHORT).show();
                    startRepeatingTask();
                }

                //Old code for a normal ethereum transaction

                /*BigDecimal decAmount = new BigDecimal("0" + editTextTransactionAmount.getText().toString());
                decAmount = decAmount.multiply(new BigDecimal("1000000000000000000")); //conver to wei
                BigInteger amount = decAmount.toBigInteger();

                EthGetTransactionCount ethGetTransactionCount = null;
                try {
                    ethGetTransactionCount = web3j.ethGetTransactionCount(
                            "0xAEdc00aA660b40E763b3237225366Eff7190B452", DefaultBlockParameterName.LATEST).sendAsync().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                BigInteger nonce = ethGetTransactionCount.getTransactionCount();

                RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
                        nonce, new BigInteger("10000000000"), new BigInteger("250000"), editTextTransactionAddress.getText().toString(), amount);


                byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, cred);
                String hexValue = Numeric.toHexString(signedMessage);

                EthSendTransaction ethSendTransaction = null;
                try {
                    ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                String transactionHash = ethSendTransaction.getTransactionHash();

                textViewTop.setText(((ethSendTransaction.hasError())? ethSendTransaction.getError().getMessage() : "No Error :)") + " | " +transactionHash);
                // poll for transaction response via org.web3j.protocol.Web3j.ethGetTransactionReceipt(<txHash>)
                */
            }
        });

        //Button for getting and displaying the current amount of token
        buttonBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String out = "";

                try {
                    out = ContractSimple.getBalance(new BigInteger(id+"")).send().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                textViewBalance.setText(out + " Tokens");
            }
        });

        //Button for getting a pseudorandom amount of tokens
        buttonGetTokens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHandlerIsRunning){
                    Toast.makeText(context, "Please wait a bit longer", Toast.LENGTH_SHORT).show();
                } else {
                    ContractSimple.getTokens(new BigInteger(id+""), BigInteger.ZERO).sendAsync();
                    startRepeatingTask();

                    textViewTop.setText("Requested more Tokens, please wait");
                }
            }
        });

        buttonBalance.performClick();

    }


    //Utility for the StatusBar updates
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                ProgressBarGetTokens.setProgress(ProgressBarGetTokens.getProgress()+1);
            } finally {
                mHandler.postDelayed(mStatusChecker, 300);
            }
            if(ProgressBarGetTokens.getProgress() == 100){
                ProgressBarGetTokens.setProgress(0);
                stopRepeatingTask();
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
        mHandlerIsRunning = true;
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
        mHandlerIsRunning = false;
        buttonBalance.performClick();
        textViewTop.setText("");
    }
}
