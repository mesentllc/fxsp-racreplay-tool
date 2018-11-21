package com.fedex.smartpost.rodes.racreplay.utils;

import com.fedex.smartpost.rodes.racreplay.enums.PFEnvironment;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ReplayUtility {
   	public static final String[] WSURL = {"http://pghdspawlsl05.ground.fedex.com:14101",
                                          "http://ipspaa05.ground.fedex.com:16160",
                                          "http://spspaa05.ground.fedex.com:16160",
                                          "http://pghqspawlsl13.ground.fedex.com:16160",
                                          "http://pghpspawlsl14.ground.fedex.com:16160"};

    private static String getFunctionUrl(PFEnvironment pfEnvironment, String racId) {
		StringBuilder returnUrl = new StringBuilder();

		returnUrl.append(WSURL[pfEnvironment.ordinal()])
				 .append("/fxsp-rating-file-transfer-ws/util/util/resend?racid=")
				 .append(racId);
        return returnUrl.toString();
    }

    private static boolean executeWebService(String sUrl) throws Exception {
		URL url;
		URLConnection urlConnection;
		InputStream result;
		BufferedReader reader;
		boolean returnVal = false;

        url = new URL(sUrl);
        urlConnection = url.openConnection();
        result = urlConnection.getInputStream();
        reader = new BufferedReader(new InputStreamReader(result));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("SFTP server")) {
                returnVal = true;
            }
        }
        reader.close();
        result.close();
		return returnVal;
	}
	
	public static boolean replay(PFEnvironment pfEnvironment, String racId) {
		try {
			return executeWebService(getFunctionUrl(pfEnvironment, racId));
		}
		catch (Exception e) {
			return false;
		}
	}
}
