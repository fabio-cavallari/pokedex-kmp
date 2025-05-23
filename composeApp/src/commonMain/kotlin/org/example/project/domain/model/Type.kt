package org.example.project.domain.model

import org.example.project.data.model.TypeContainerDto

data class Type(
    val name: String,
    val url: String,
)

fun TypeContainerDto.asDomainModel() = Type(type.name, type.url)

fun List<TypeContainerDto>.asDomainModel() = map { it.asDomainModel() }