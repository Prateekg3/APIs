<?php

const DEVICE_ID = "49b8e79d-bc02-9295-fe09-a4112427490c";
const DEVICE_PASSWORD = "SamsonitePhp1";

class Wallet {

    public function updateCard() {

        $addressArray = Array( 
                "Customer" => "1",
                "Line1" => "69 Ellis Street", 
                "City" => "San Francisco",
                "State" => "CA", 
                "Country" => "USA", 
                "Zip" => "94109");

        $cardholderArray = Array( 
                "FirstName" => "Herb",
                "LastName" => "Caen");

        $cardArray = Array(
                "ID" => "1e700b9f-3e43-4cc0-9a02-884dd4c7e6ee",
                "Tender" => "CreditCard", // "CreditCard" | "ECheck"
                "Customer" => "1", // Customer ID generated by PayFabric
                "CardHolder" => $cardholderArray, 
                "Billto" => $addressArray);

        // Convert the data to JSON.
        $json = json_encode($cardArray, TRUE);

        // Setup the HTTP request.
        $httpUrl = "https://sandbox.payfabric.com/payment/api/wallet/update";
        $httpHeader = Array(
                "Content-Type: application/json",
                "authorization: " . DEVICE_ID . "|" . DEVICE_PASSWORD);        
        $curlOptions = Array(CURLOPT_RETURNTRANSFER => TRUE,
                CURLOPT_VERBOSE => TRUE,
                CURLOPT_POSTFIELDS => $json,
                CURLOPT_HTTPHEADER => $httpHeader);

        // Execute the HTTP request.
        $curlHandle = curl_init($httpUrl);
        curl_setopt_array($curlHandle, $curlOptions);
        $httpResponseBody = curl_exec($curlHandle);
        $httpResponseCode = curl_getinfo($curlHandle, CURLINFO_HTTP_CODE);
        curl_close($curlHandle);

        if ($httpResponseCode >= 300) {
            // Handle errors.
        }          

        // Convert the JSON into a multi-dimensional array.
        $responseArray = json_decode($httpResponseBody, TRUE);

        // Output the results of the request.
        var_dump($httpResponseBody);

        return $responseArray;        

    }

}

/* Example Response
{"Result":"True"}
*/

