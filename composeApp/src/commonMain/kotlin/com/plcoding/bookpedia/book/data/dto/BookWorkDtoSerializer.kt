package com.plcoding.bookpedia.book.data.dto

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

object BookWorkDtoSerializer : KSerializer<BookWorkDto> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
        BookWorkDto::class.simpleName!!
    ) {
        //json field element
        // decodeElementIndex -> 0
        element<String?>("description")
        // decodeElementIndex -> 1
//        element<String?>("title")
    }

    override fun serialize(
        encoder: Encoder,
        value: BookWorkDto
    ) {
        TODO("Not yet implemented")
    }

    override fun deserialize(decoder: Decoder): BookWorkDto = decoder.decodeStructure(descriptor) {
        var description: String? = null

        while (true) {
            when (val index = decodeElementIndex(descriptor)) {
                0 -> {
                    val jsonDecoder = decoder as? JsonDecoder
                        ?: throw SerializationException("this decoder only works with json")
                    val element = jsonDecoder.decodeJsonElement()
                    description = if (element is JsonObject) {
                        decoder.json.decodeFromJsonElement<DescriptionDto>(
                            element = element,
                            deserializer = DescriptionDto.serializer()
                        ).value
                    } else if (element is JsonPrimitive && element.isString) {
                        element.content
                    } else null
                }

                CompositeDecoder.DECODE_DONE -> break
                else -> throw SerializationException("Unexpected index $index")
            }
        }

        return@decodeStructure BookWorkDto(description)
    }
}