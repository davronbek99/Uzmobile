package com.example.uzmobile.models

import java.io.Serializable

class Uzmobile : Serializable {
    var id: Int? = null
    var name: String? = null
    var desc: String? = null
    var imgname: Int? = null
    var ussd: String? = null

    constructor()

    constructor(id: Int?, name: String?, desc: String?, imgname: Int?, ussd: String?) {
        this.id = id
        this.name = name
        this.desc = desc
        this.imgname = imgname
        this.ussd = ussd
    }


}