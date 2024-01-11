package com.gac.nioapp.test.extension


fun Double?.toValue(): String {
    if (this == null) {
        return "0"
    }
    if (this.toInt().toDouble() == this) {
        return this.toInt().toString()
    }
    return this.toString()
}