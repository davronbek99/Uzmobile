package com.example.uzmobile.models

import java.io.Serializable

class Xizmatlar : Serializable {

    var id: Int? = null
    var name: String? = null
    var desc: String? = null
    var ussd: String? = null

    constructor()
    constructor(id: Int?, name: String?, desc: String?, ussd: String?) {
        this.id = id
        this.name = name
        this.desc = desc
        this.ussd = ussd
    }


}