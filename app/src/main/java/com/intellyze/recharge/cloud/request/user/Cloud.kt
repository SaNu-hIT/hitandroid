package com.intellyze.recharge.cloud.request.user

class Cloud() {

        companion object{
            var mCloudConnector: CloudConnector?=null
            fun getWiSeCloudConnector():CloudConnector{
                return mCloudConnector!!
            }
            fun setWiSeCloudConnector( mCloudConnector: CloudConnector){
                this.mCloudConnector=mCloudConnector
            }

        }

        interface CloudConnector{
            fun getBaseUrl():String
        }

}