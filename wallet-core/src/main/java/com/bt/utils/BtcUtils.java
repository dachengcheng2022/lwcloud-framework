package com.bt.utils;


import static com.bt.utils.Base58.decodeChecked;

public class BtcUtils {

    public static int getVersionfromAddress(String encoded) throws Exception {
        int version = -1;
        byte versionByte = 0;
        try {
            byte[] versionAndDataBytes = decodeChecked(encoded);
            versionByte = versionAndDataBytes[0];
        } catch (Exception e) {
        }
        version = versionByte & 0xFF;
        return version;
    }

    public static String getBtcSeriesAddressFromPHAndV(String pubkeyHash,int version){
        byte[] bytes = Utils.hexStringToByteArray(pubkeyHash);
        byte[] addressBytes = new byte[1 + bytes.length + 4];
        addressBytes[0] = (byte) version;
        System.arraycopy(bytes, 0, addressBytes, 1, bytes.length);
        byte[] checksum = Sha256Hash.hashTwice(addressBytes, 0, bytes.length + 1);
        System.arraycopy(checksum, 0, addressBytes, bytes.length + 1, 4);
        String address = Base58.encode(addressBytes);
        return address;
    }
}
