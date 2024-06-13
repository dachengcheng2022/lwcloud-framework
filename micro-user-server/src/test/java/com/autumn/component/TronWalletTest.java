package com.autumn.component;

import com.autumn.exception.custom.CommonException;
import com.autumn.utils.AbiUtil;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;
import org.tron.api.GrpcAPI;
import org.tron.api.WalletGrpc;
import org.tron.api.WalletSolidityGrpc;
import org.tron.common.runtime.vm.DataWord;
import org.tron.common.utils.Base58;
import org.tron.common.utils.Sha256Hash;
import org.tron.protos.Protocol;
import org.tron.protos.contract.SmartContractOuterClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

public class TronWalletTest {

    private WalletGrpc.WalletBlockingStub walletBlockingStub;

    private WalletSolidityGrpc.WalletSolidityBlockingStub walletSolidity;

    @Before
    public void init(){
//        https://developers.tron.network/docs/networks
        ManagedChannel fullChannel = buildChannel("3.225.171.164:50051");
        walletBlockingStub = WalletGrpc.newBlockingStub(fullChannel);

        ManagedChannel solidityChannel = buildChannel("3.225.171.164:50052");
        walletSolidity = WalletSolidityGrpc.newBlockingStub(solidityChannel);

    }



    @Test
    public void teset_TRX_GetAmount() throws ServerException {
        String mainAddress = "TES1PgfoTE8fMRBWeyJgxqmRB2uss74nVm";

        BigDecimal maintokenBalance = getBalanceTRX(mainAddress);

        System.err.println("mainaddress= " + maintokenBalance);

        List<String> strings = way2ByFile();
        BigDecimal all = strings.stream().map(v -> {
            try {
                BigDecimal tokenBalance = getBalanceTRX(v);
                System.err.println("dacheng__  " + v + "  _______ " + tokenBalance);
                return tokenBalance;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        System.err.println("all="+all);

    }


    @Test
    public void teset_TRX20_GetAmount() throws ServerException {
        String mainAddress = "TES1PgfoTE8fMRBWeyJgxqmRB2uss74nVm";
        String contractAddress = "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t";
        Integer decimal = 6;
        BigDecimal maintokenBalance = getBalanceTokenTRC20(mainAddress,contractAddress,decimal);

        System.err.println("mainaddress= " + maintokenBalance);

        List<String> strings = way2ByFile();
        BigDecimal all = strings.stream().map(v -> {
            try {
                BigDecimal tokenBalance = getBalanceTokenTRC20(v,contractAddress,decimal);
                System.err.println("dacheng__  " + v + "  _______ " + tokenBalance);
                return tokenBalance;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        System.err.println("all="+all);

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

    private List<String> way2ByFile() {
        List<String> address = new ArrayList<>();
        try {
            String thisLine = null;
            File file = ResourceUtils.getFile("classpath:address.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((thisLine = bufferedReader.readLine()) != null) {
                address.add(thisLine);
            }
//            System.out.println("方式2 文件内容:" + bufferedReader.readLine());
        } catch (Exception e) {
            System.out.println("方式2 错误:" + e);
        }
        return address;
    }



}
