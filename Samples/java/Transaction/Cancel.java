package com.nodus.payfabric.samples.transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.nodus.payfabric.samples.misc.Token;

/**
 * This sample is to demo how to cancel a transaction before it is settled
 * */
public class Cancel {

	/**
	 * Only unsettled transaction can be cancelled.
	 * 
	 * @param originalKey
	 *            Orignial transaction key
	 * */
	public void cancelTransaction(String originalKey) {
		
		try {

			String url = "https://sandbox.payfabric.com/v2/rest/api/reference" + "/" + originalKey + "?trxtype=Void";
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type",
					"application/json; charset=utf-8");
			// Replace with your own device id and device password
			con.setRequestProperty("authorization",
					"0ad64468-f4bc-0c99-4e31-bd08dd862c43|123456abc");
			con.setDoOutput(true);

			InputStream stream;
			int responseCode = con.getResponseCode();
			if (responseCode >= 400) {
				stream = con.getErrorStream();
			} else {
				stream = con.getInputStream();
			}
			BufferedReader streamReader = new BufferedReader(
					new InputStreamReader(stream));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = streamReader.readLine()) != null) {
				response.append(inputLine);
			}
			streamReader.close();
			con.disconnect();
			System.out.println(response.toString());

			if (responseCode >= 400) {

				// Handling exception from PayFabric

			}

			//
            // Sample response - a transaction response object
            // ------------------------------------------------------
            //{
            // "AVSAddressResponse":"Y",
            // "AVSZipResponse":"Y",
            // "AuthCode":"010010",
            // "CVV2Response":"Y",
            // "IAVSAddressResponse":"Y",
            // "Message":"APPROVED",
            // "OriginationID":"987220999",
            // "RespTrxTag":"",
            // "ResultCode":"0",
            // "Status":"Approved",
            // "TrxDate":"",
            // "TrxKey":"140500229002"
            //}
            // ------------------------------------------------------

		} catch (IOException e) {
			System.out.println(e.getMessage());

			// Handling exception
		}
	}
}
