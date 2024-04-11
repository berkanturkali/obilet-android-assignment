package com.obilet.android.assignment.core.network.datasource.implementation

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

abstract class BaseDataSourceImplTest<D : Any, T : D> {

    protected lateinit var mockWebServer: MockWebServer
    protected lateinit var dataSource: D

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = setDispatcher()
        mockWebServer.start()
        dataSource = initializeDataSource()

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    abstract fun setDispatcher(): Dispatcher

    abstract fun initializeDataSource(): T
}