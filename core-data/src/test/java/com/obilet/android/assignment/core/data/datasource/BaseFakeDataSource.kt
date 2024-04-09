package com.obilet.android.assignment.core.data.datasource

import java.io.IOException

abstract class BaseFakeDataSource {

    protected var throwException: Boolean = false
        private set

    protected var exception: Exception = IOException()
        private set

    protected var returnError: Boolean = false
        private set

    protected var returnSuccessResponseButNull: Boolean = false
        private set
    protected var returnSuccessResponseWithBaseResponseButNullData: Boolean = false
        private set

    val errorResponse =
        "{\"Message\":\" An error has occurred.\",\"ExceptionMessage\":\" Invalid length for a Base-64 char array or string.\",\"ExceptionType\":\"System.FormatException\"}"


    internal fun setThrowException(throwException: Boolean) {
        this.throwException = throwException
    }

    internal fun setReturnSuccessResponseButNull(returnSuccessButNull: Boolean) {
        returnSuccessResponseButNull = returnSuccessButNull
    }

    internal fun setReturnError(returnError: Boolean) {
        this.returnError = returnError
    }

    internal fun setException(exception: java.lang.Exception) {
        this.exception = exception
    }

    internal fun setReturnSuccessResponseWithBaseResponseButNullData(
        returnSuccessResponseWithBaseResponseButNullData: Boolean
    ) {
        this.returnSuccessResponseWithBaseResponseButNullData =
            returnSuccessResponseWithBaseResponseButNullData
    }

}