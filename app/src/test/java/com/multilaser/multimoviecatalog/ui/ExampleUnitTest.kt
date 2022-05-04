package com.multilaser.multimoviecatalog.ui

import android.content.Context
import android.provider.Settings.Global.getString
import com.multilaser.multimoviecatalog.R
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {}

  /*  private val FAKE_STRING = "HELLO WORLD"

    @Mock
    private lateinit var mockContext: Context

    @Test
    fun readStringFromContext_LocalizedString() {
        // Given a mocked Context injected into the object under test...
        val mockContext = mock<Context> {
            on { getString(R.string.app_name) } doReturn "FAKE_STRING"
        }

        val myObjectUnderTest = ClassUnderTest(mockContext)

        // ...when the string is returned from the object under test...
        val result: String = myObjectUnderTest.getName()

        // ...then the result should be the expected one.
        assertEquals(result, FAKE_STRING)
    }
}*/
