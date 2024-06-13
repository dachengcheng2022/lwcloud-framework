package com.autumn.component;

import com.autumn.exception.custom.CommonException;
import com.autumn.utils.AbiUtil;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Before;
import org.junit.Test;
import org.tron.api.GrpcAPI;
import org.tron.api.WalletGrpc;
import org.tron.api.WalletSolidityGrpc;
import org.tron.common.runtime.vm.DataWord;
import org.tron.common.utils.Base58;
import org.tron.common.utils.Sha256Hash;
import org.tron.protos.Protocol;
import org.tron.protos.contract.SmartContractOuterClass;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.ServerException;

public class Text111 {

    private WalletGrpc.WalletBlockingStub walletBlockingStub;

    private WalletSolidityGrpc.WalletSolidityBlockingStub walletSolidity;

    @Before
    public void init(){
        ManagedChannel fullChannel = buildChannel("");
        walletBlockingStub = WalletGrpc.newBlockingStub(fullChannel);

        ManagedChannel solidityChannel = buildChannel("");
        walletSolidity = WalletSolidityGrpc.newBlockingStub(solidityChannel);

    }



    @Test
    public void tesetGetAmount(){

    }

    private ManagedChannel buildChannel(String target) {
        return ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();
    }

    protected BigDecimal getBalanceTRX(String address) throws ServerException {

        ByteString addressBytes = ByteString.copyFrom(decode58Check(address));
        Protocol.Account account = Protocol.Account.newBuilder().setAddress(addressBytes).build();

        // walletSolidityBlockingStub can get TRX balances but can't get TRC-20 balances (it can't call
        // TriggerContract), so we use walletBlockingStub for TRX balances as well.
        long balance = walletBlockingStub.getAccount(account).getBalance();
        return subunitsToUnits(balance);
    }

    protected BigDecimal getBalanceTokenTRC20(String address, String contractAddress,Integer decimal) throws CommonException, ServerException {

        byte[] data = Hex.decode(AbiUtil.parseMethod("balanceOf(address)", "\"" + address + "\"", false));
        SmartContractOuterClass.TriggerSmartContract trigger = SmartContractOuterClass.TriggerSmartContract.newBuilder()
                .setContractAddress(ByteString.copyFrom(decode58Check(contractAddress)))
                .setData(ByteString.copyFrom(data))
                .build();

        // Do the call
        GrpcAPI.TransactionExtention txe = walletBlockingStub.triggerConstantContract(trigger);
        if (txe.getTransaction().getRetCount() != 1) {
            throw new CommonException("Did not receive any results when fetching token balance for " );
        }

        // Parse the result
        String balanceBigIntStr = new DataWord(txe.getConstantResult(0).toByteArray()).bigIntValue();
        BigInteger balanceBigInt = new BigInteger(balanceBigIntStr);
        return subunitsToUnits(balanceBigInt, decimal);
    }

    public static byte[] decode58Check(String input) {
        byte[] decodeCheck = Base58.decode(input);
        if (decodeCheck.length <= 4) {
            return null;
        }
        byte[] decodeData = new byte[decodeCheck.length - 4];
        System.arraycopy(decodeCheck, 0, decodeData, 0, decodeData.length);
        byte[] hash0 = Sha256Hash.hash(Boolean.TRUE,decodeData);
        byte[] hash1 = Sha256Hash.hash(Boolean.TRUE,hash0);
        if (hash1[0] == decodeCheck[decodeData.length] &&
                hash1[1] == decodeCheck[decodeData.length + 1] &&
                hash1[2] == decodeCheck[decodeData.length + 2] &&
                hash1[3] == decodeCheck[decodeData.length + 3]) {
            return decodeData;
        }
        return null;
    }

    public static BigDecimal subunitsToUnits(long subunits) {
        return BigDecimal.valueOf(subunits).movePointLeft(6);
    }

    public static BigDecimal subunitsToUnits(BigInteger amount, int decimals) {
        return new BigDecimal(amount).movePointLeft(decimals);
    }


}
