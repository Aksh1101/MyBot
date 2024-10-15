package com.example.mybot.data

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ResponseStoppedException
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext




object ChatData {

    val api_key = "Enter the api key"
    // api key is to be protected so it is hided 

    suspend fun getResponse(prompt: String): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash", apiKey = api_key
        )

        try {
            val response = withContext(Dispatchers.IO){
                generativeModel.generateContent(prompt)
            }
            return Chat(
                prompt = response.text ?: "Error",
                bitmap = null,
                isFromUser = false
            )
        }catch (e:Exception){
            return Chat(
                prompt = e.message ?: "Error",
                bitmap = null,
                isFromUser = false
            )
        }
    }


    suspend fun getResponseWithImage(prompt: String, bitmap: Bitmap): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash", apiKey = api_key
        )

        try {
            val inputContent = content {
                image(bitmap)
                text(prompt)
            }
            val response = withContext(Dispatchers.IO){
                generativeModel.generateContent(inputContent)
            }
            return Chat(
                prompt = response.text ?: "Error",
                bitmap = null,
                isFromUser = false
            )
        }catch (e:Exception){
            return Chat(
                prompt = e.message ?: "Error",
                bitmap = null,
                isFromUser = false
            )
        }
    }

}
