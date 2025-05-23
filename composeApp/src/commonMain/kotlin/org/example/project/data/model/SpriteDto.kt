package org.example.project.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpriteDto(
    @SerialName(value = "front_default") val frontDefault: String
)