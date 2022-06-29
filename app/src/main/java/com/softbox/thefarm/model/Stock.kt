package com.softbox.thefarm.model

class Stock {
    var calfID:String ?= null
    var calfAge:String ?= null
    var calfWeight:String ?= null
    var calfDetails:String ?= null
    var date:String ?= null
    var time:String ?= null
    var owner:String ?= null
    var upload:String ?= null

    constructor(calfID:String,
            calfAge:String,
        calfWeight:String,
                calfDetails:String,
                date:String,
                        time:String,
                        owner:String,
                        upload:String){
        this.calfID = calfID
        this.calfAge = calfAge
        this.calfWeight = calfWeight
        this.calfDetails = calfDetails
        this.date = date
        this.time = time
        this.owner = owner
        this.upload = upload
    }




}