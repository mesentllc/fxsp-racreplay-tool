package com.fedex.smartpost.rodes.racreplay.utils;

import com.fedex.smartpost.rodes.racreplay.SystemParameter;
import com.fedex.smartpost.rodes.racreplay.enums.PFEnvironment;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SystemParameterUtilities {
   	public static final String[] WSURL = {"http://pghdspawlsl05.ground.fedex.com:14101",
                                          "http://ipspaa05.ground.fedex.com:16150",
                                          "http://spspaa05.ground.fedex.com:16150",
                                          "http://pghqspawlsl13.ground.fedex.com:16150",
                                          "http://pghpspawlsl14.ground.fedex.com:16150"};

    private static String getFunctionUrl(PFEnvironment pfEnvironment) {
		StringBuilder returnUrl = new StringBuilder();

		returnUrl.append(WSURL[pfEnvironment.ordinal()]).append("/rodes-scheduler/serviceInitiator/setParameter?");
        return returnUrl.toString();
    }
    
    private static String getValuesUrl(String category, String subCategory, String name, String value, String type, String cache, String comment) {
        StringBuilder returnUrl = new StringBuilder();
        returnUrl.append("name=").append(name.trim()).append("&")
				 .append("category=").append(category).append("&")
                 .append("subcategory=").append(subCategory).append("&")
                 .append("comment=").append(safeEncoder(comment)).append("&")
                 .append("value=").append(safeEncoder(value)).append("&")
                 .append("type=").append(type).append("&")
                 .append("cacheValue=").append(cache).append("&")
                 .append("password=ChangeIsG00d");
		return returnUrl.toString();
	}

	private static String safeEncoder(String string) {
        if (string != null) {
            try {
                return URLEncoder.encode(string,"UTF-8");
            } 
            catch (UnsupportedEncodingException ex) {
            }
        }
        return null;
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
            if (line.contains("Password valid.")) {
                returnVal = true;
            }
        }
        reader.close();
        result.close();
		return returnVal;
	}

    public static int update(PFEnvironment pfEnvironment, SystemParameter systemParameter) {
        return update(pfEnvironment, systemParameter.getCategory(), systemParameter.getSubCategory(), systemParameter.getName(),
                      systemParameter.getValue(), systemParameter.getType(), systemParameter.getCache(), systemParameter.getComment());
    }
    
    public static int update(PFEnvironment pfEnvironment, String category, String subCategory, 
                             String name, String value, String type, String cache, String comment) {
        String url = getFunctionUrl(pfEnvironment);
        url += getValuesUrl(category, subCategory, name, value, type, cache, comment);
        try {
            if ((url == null) || (!executeWebService(url))) {
                return -1;
            }
        }
        catch (Exception e) {
            return -1;
        }
        return 0;
    }
}
