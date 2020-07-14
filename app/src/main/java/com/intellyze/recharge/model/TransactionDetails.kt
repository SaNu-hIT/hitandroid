package com.intellyze.recharge.model

class TransactionDetails {

//    Body
//    {
//        "transactionId": 282571,
//        "operatorTransactionId": null,
//        "customIdentifier": "my-custom-identifier-for-internal-usage",
//        "recipientPhone": "50936377111",
//        "senderPhone": "13059547862",
//        "countryCode": "HT",
//        "operatorId": 174,
//        "operatorName": "Natcom Haiti",
//        "discount": 1.4,
//        "discountCurrencyCode": "USD",
//        "requestedAmount": 10,
//        "requestedAmountCurrencyCode": "USD",
//        "deliveredAmount": 930.00,
//        "deliveredAmountCurrencyCode": "HTG",
//        "transactionDate": "2020-01-31 07:43:36"
//        "pinDetail": null
//    }

    var transactionId : Long? = null
        get() = field
        set(value) {
            field = value

        }

    var operatorId : String? = null
        get() = field
        set(value) {
            field = value

        }
    var operatorName : String? = null
        get() = field
        set(value) {
            field = value
        }
    var deliveredAmount : String? = null
        get() = field
        set(value) {
            field = value
        }
    var transactionDate : String? = null
        get() = field
        set(value) {
            field = value
        }


}