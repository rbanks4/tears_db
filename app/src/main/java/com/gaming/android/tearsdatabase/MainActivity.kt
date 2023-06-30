package com.gaming.android.tearsdatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.gaming.android.tearsdatabase.databinding.ActivityMainBinding
import io.realm.kotlin.Realm
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response


class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private var CONNECT_STRING = "mongodb+srv://user:pw@myhobbydb.o8ovpd1.mongodb.net/?retryWrites=true&w=majority"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        // attach an adapter with a layout of 'grid' (3 column)
        bind.mainList.adapter = ItemAdapter(DataSource.loadWeapons())
        bind.mainList.layoutManager = GridLayoutManager(this, 3)
    }

    fun callEndpoint(): Response {
        println("calling endpoint")
        val client = OkHttpClient()
        val mediaType = "application/json".toMediaType()
        val body = "{\r\n    \"dataSource\": \"myhobbydb\",\r\n    \"database\": \"totk\",\r\n    \"collection\": \"weapons\"\r\n}".toRequestBody(mediaType)
        val request = Request.Builder()
            .url("https://us-east-2.aws.data.mongodb-api.com/app/data-bxxyf/endpoint/data/v1/action/findOne")
            .post(body)
            .addHeader("Content-Type", "application/json")
            .addHeader("Access-Control-Request-Headers", "*")
            .addHeader("api-key", "xzEOCq63EkHsw8PIh9CAbDPuERVTOuNKJSQSivxYvZBcv8hmuwk6FfaZhuGXztoJ")
            .build()
        val response = client.newCall(request).execute()
        if(response.isSuccessful)
            println(response.body)
        return response
    }

//    fun callMongoDb() {
//        val connectionString = "mongodb+srv://p3anutbj7:Betelgeuse2@myhobbydb.o8ovpd1.mongodb.net/?retryWrites=true&w=majority"
//        val serverApi = ServerApi.builder()
//            .version(ServerApiVersion.V1)
//            .build()
//        val mongoClientSettings = MongoClientSettings.builder()
//            .applyConnectionString(ConnectionString(connectionString))
//            .serverApi(serverApi)
//            .build()
//        // Create a new client and connect to the server
//        MongoClient.create(mongoClientSettings).use { mongoClient ->
//            val database = mongoClient.getDatabase("admin")
//            runBlocking {
//                database.runCommand(Document("ping", 1))
//            }
//            println("Pinged your deployment. You successfully connected to MongoDB!")
//        }
//    }
}