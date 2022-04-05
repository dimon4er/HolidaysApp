package to.boosty.cmit.holidaysyear

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import to.boosty.cmit.holidaysyear.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import org.json.JSONException
import org.json.JSONObject
import java.net.MalformedURLException
import java.net.URL
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var listOfHolidays: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        listOfHolidays = binding.ListOfHolidays
        //CoroutineScope(Dispatchers.IO).launch { onCon() }
        try {
            Log.d("TAG", "1")
            val url = "https://calendarific.com/api/v2/holidays?api_key=f41f97baea1624c7a059a048e3185c9b0e7c08a0&country=RU&year=2022"
            suspend fun r () = coroutineScope {
                val res = async {getData(url)}
                val rt = res.await()
                Log.d("TAG", "1 + $rt")
            }
            Log.d("TAG", "1+1")
            //listOfHolidays.text = r.toString()

        } catch (ex: MalformedURLException) {
            ex.printStackTrace()
        }



    }

    fun getData(output: String): String {
        try {
            Log.d("TAG", "$output")
            val apiResponse = URL(output).readText()
            val resJSON = JSONObject(apiResponse)
            Log.d("TAG", "1 + $apiResponse")
            return apiResponse
            //listOfHolidays.text = apiResponse
            } catch (ex: JSONException) {
            Log.d("TAG", "3")
            return ""
        }

    }

}

