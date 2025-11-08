package com.plcoding.bookpedia.core.presentation

import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.data_error_disk_full
import cmp_bookpedia.composeapp.generated.resources.data_error_no_internet
import cmp_bookpedia.composeapp.generated.resources.data_error_request_timeout
import cmp_bookpedia.composeapp.generated.resources.data_error_serialization
import cmp_bookpedia.composeapp.generated.resources.data_error_server
import cmp_bookpedia.composeapp.generated.resources.data_error_too_many_request
import cmp_bookpedia.composeapp.generated.resources.data_error_unknown
import com.plcoding.bookpedia.core.domain.DataError

fun DataError.toUiText(): UiText {
    val stringResource = when (this) {
        DataError.Local.DISK_FULL -> Res.string.data_error_disk_full
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.data_error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.data_error_too_many_request
        DataError.Remote.NO_INTERNET -> Res.string.data_error_no_internet
        DataError.Remote.SERVER -> Res.string.data_error_server
        DataError.Remote.SERIALIZATION -> Res.string.data_error_serialization
        DataError.Local.UNKNOWN, DataError.Remote.UNKNOWN -> Res.string.data_error_unknown
    }
    return UiText.StringResourceId(stringResource)
}