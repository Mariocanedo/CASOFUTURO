package com.example.casofuturo

import com.example.casofuturo.Modelo.remote.RetrofitClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.converter.gson.GsonConverterFactory



@RunWith(JUnit4::class)
class RestrofitInstanceTest {


    private lateinit var mockWebServer: MockWebServer

       @Before
       fun setup(){
           mockWebServer= MockWebServer()
       }

       @After
        fun tearDown(){
            mockWebServer.shutdown()
        }

       @Test
       fun testRetrofit(){
           val expectedBaseUrl = mockWebServer.url("/").toString()
           val retrofit = Retrofit.Builder()
               .baseUrl(expectedBaseUrl)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
             RetrofitClient.retrofitInstance = retrofit
             val retrofitInstance = RetrofitClient.retrofitInstance
           assertEquals( expectedBaseUrl, retrofitInstance.baseUrl().toString())
       }









}