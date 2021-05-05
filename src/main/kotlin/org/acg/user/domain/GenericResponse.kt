package org.acg.user.domain;

data class GenericResponse (
    private val message: String,
    private val error: String
) {

    constructor(message: String) : this (
        message, ""
    )
}
