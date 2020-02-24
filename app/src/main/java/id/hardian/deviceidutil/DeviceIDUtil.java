package id.hardian.deviceidutil;

import android.media.MediaDrm;
import android.media.UnsupportedSchemeException;
import android.os.Build;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class DeviceIDUtil {
    private static final String TAG = "DeviceIDUtil";

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String getUniqueID() {
        UUID widevine_uuid = new UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L);
        MediaDrm wvDrm = null;

        try{
            wvDrm = new MediaDrm(widevine_uuid);
            byte[] mivevineId = wvDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(mivevineId);

            return bytesToHex(md.digest());
        } catch (UnsupportedSchemeException e) {
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        } finally {
            if(isAndroidTargetPieAndHigher()) {
                wvDrm.close();
            }else{
                wvDrm.release();
            }
        }
    }

    private static boolean isAndroidTargetPieAndHigher(){
        boolean retval = false;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            retval = true;
        }
        return retval;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
