package com.softbox.thefarm.model

class Employee {
    var firstName:String ?= null
    var lastName:String ?= null
    var empID:String ?= null
    var type:String ?= null
    constructor(
                firstName:String,
                lastName:String,
                empID:String,
                type:String,
               ){

        this.firstName=firstName
        this.lastName=lastName
        this.empID=empID
        this.type = type

    }

    constructor()
}