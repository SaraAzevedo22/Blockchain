package Blockchain;

import java.security.MessageDigest;

public class Config {

    public static int difficulty = 5;
    public static int reward = 1;
    public static final double min_reputation = 0.7;
    public static String bootstrapNode = "000003a0f61af479f1fb7c3a54dc3dfb9b5699086cc98132a376da936460898d";
    final int ID_LENGTH = 160;

    //Calculate SHA256
    public static String calculateSHA256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}