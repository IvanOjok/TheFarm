package com.softbox.thefarm.model

class Activity {
   var calfID: String  ?= null
   var type : String  ?= null
   var detail : String  ?= null
   var result  : String  ?= null
   var aDate : String  ?= null
   var aTime : String  ?= null
   var createdBy: String  ?= null

    constructor(
        calfID: String?,
        type: String?,
        detail: String?,
        result: String?,
        aDate: String?,
        aTime: String?,
        createdBy: String?
    ) {
        this.calfID = calfID
        this.type = type
        this.detail = detail
        this.result = result
        this.aDate = aDate
        this.aTime = aTime
        this.createdBy = createdBy
    }


}