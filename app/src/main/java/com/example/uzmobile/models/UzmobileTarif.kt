package com.example.uzmobile.models

import java.io.Serializable

class UzmobileTarif : Serializable {
    var id: Int? = null
    var nameT: String? = null
    var imgUrl: String? = null
    var desc: String? = null
    var ussdT: String? = null
    var batafsil:String?=null

    constructor()
    constructor(
        id: Int?,
        nameT: String?,
        imgUrl: String?,
        desc: String?,
        ussdT: String?,
        batafsil: String?
    ) {
        this.id = id
        this.nameT = nameT
        this.imgUrl = imgUrl
        this.desc = desc
        this.ussdT = ussdT
        this.batafsil = batafsil
    }


}