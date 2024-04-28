package com.autumn.web3j;


import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public final class SimilarErc20 {

    public static final Event TRANSFER_EVENT = new Event("Transfer",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));

    public static final Event INVITER_EVENT = new Event("Invite",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {},new TypeReference<Address>(true) {}));

    public static final Event DEPOSIT_EVENT = new Event("Deposit",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {},new TypeReference<Uint256>(true) {},new TypeReference<Uint256>() {}));

    public static final Event WITHDRAWAPPLY_EVENT = new Event("WithdrawApply",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {},new TypeReference<Uint256>(true) {},new TypeReference<Uint256>() {},new TypeReference<Uint256>() {}));


    public static final Event CLAIM_EVENT = new Event("Claim",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {},new TypeReference<Uint256>() {},new TypeReference<Uint256>() {}));

    public static final Event WITHDRAW_EVENT = new Event("Withdraw",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {},new TypeReference<Uint256>(true) {},new TypeReference<Uint256>() {},new TypeReference<Uint256>() {}));


    public static final Event SWAP_EVENT = new Event("Swap",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {},new TypeReference<Uint256>() {},new TypeReference<Uint256>() {},new TypeReference<Uint256>() {},new TypeReference<Uint256>() {},new TypeReference<Address>(true) {}));

    public static final Event DEPOSITINVALID_EVENT = new Event("DepositInvalid",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {},new TypeReference<Uint256>(true) {},new TypeReference<Uint256>() {}));


    // PRC20 'balanceOf' function
    public static Function balanceOf(String address) {
        return new Function("balanceOf", Arrays.<Type>asList(new Address(address)), Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
        }));
    }


    // PRC20 'transfer' function
    // token transfer dest address is toAddress
    public static Function transfer(String toAddress, BigInteger quantity) {
        return new Function("transfer", Arrays.<Type>asList(new Address(toAddress), new Uint256(quantity)), Collections.<TypeReference<?>>emptyList());
    }

    // PRC20 'approve' function
    public static Function approve(String spender, BigInteger quantity) {
        return new Function("approve", Arrays.<Type>asList(new Address(spender), new Uint256(quantity)), Collections.<TypeReference<?>>emptyList());
    }



}
