package edu.stanford.crypto.cs251.transactions;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Utils;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;

import java.io.File;
import java.math.BigInteger;
import java.net.UnknownHostException;

import static org.bitcoinj.script.ScriptOpCodes.*;

/**
 * Created by bbuenz on 24.09.15.
 */
public class LinearEquationTransaction extends ScriptTransaction {
    // TODO: Problem 2
    public LinearEquationTransaction(NetworkParameters parameters, File file, String password) {
        super(parameters, file, password);
    }

    @Override
    public Script createInputScript() {
        
        ScriptBuilder builder = new ScriptBuilder();
        BigInteger firstHalf,secondHalf;
        firstHalf=new BigInteger("58");
        secondHalf=new BigInteger("11");
        builder.op(OP_2DUP);
        builder.op(OP_ADD);
        builder.data(encode(firstHalf));
        builder.op(OP_EQUALVERIFY);
        builder.op(OP_SUB);
        builder.op(OP_ABS);
        builder.data(encode(secondHalf));
        builder.op(OP_EQUAL);
        return builder.build();
    }

    @Override
    public Script createRedemptionScript(Transaction unsignedScript) {
        // TODO: Create a spending script
        ScriptBuilder builder = new ScriptBuilder();
        BigInteger x,y;
        x=new BigInteger("58");
        y=new BigInteger("11");
        builder.data(encode(x));
        builder.data(encode(y));
        return builder.build();
    }

    private byte[] encode(BigInteger bigInteger) {
        return Utils.reverseBytes(Utils.encodeMPI(bigInteger, false));
    }
}
