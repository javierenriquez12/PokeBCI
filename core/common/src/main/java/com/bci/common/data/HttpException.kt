package com.bci.common.data

class HttpException: Throwable() {
    override val message: String
        get() = "There was a problem with the server, please try again later."
}