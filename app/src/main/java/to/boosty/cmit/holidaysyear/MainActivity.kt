package to.boosty.cmit.holidaysyear

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.net.URL
import java.util.*



class MainActivity : AppCompatActivity() {
    var namesHolidays = mutableListOf<String>()
    var datesHolidays = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager

        val key = "f41f97baea1624c7a059a048e3185c9b0e7c08a0"
        val year = 2022
        val url = "https://calendarific.com/api/v2/holidays?api_key=$key&country=RU&year=$year"
        CoroutineScope(Dispatchers.IO).launch {
            kotlin.runCatching {
                getData(url)
            }.onSuccess { result ->
                withContext(Dispatchers.Main) {
                    val resJSON = JSONObject(result.toString())
                    val responseJSON = resJSON.getJSONObject("response")
                    val array = responseJSON.getJSONArray("holidays")
                    val length = array.length()
                    namesHolidays = mutableListOf()
                    datesHolidays = mutableListOf()
                    for (i in 0 until length) {
                        val obj: JSONObject = array.getJSONObject(i)
                        val name: String = obj.getString("name")
                        val obj_data: JSONObject = obj.getJSONObject("date")
                        val data_iso = obj_data.getString("iso")
                        namesHolidays.add(name)
                        datesHolidays.add(data_iso)
                        Log.d("TAG", "$name $data_iso")
                    }
                    val holidaysAdapter = ListHolidaysAdapter(namesHolidays, datesHolidays, this@MainActivity)
                    recyclerView.adapter = holidaysAdapter

                }
            }.onFailure { t->
                Log.d("TAG", "2")
            }
        }
    }


    fun getData(output: String): Any {
        try {
            val apiResponse = URL(output).readText()
            return apiResponse
            } catch (ex: JSONException) {
            Log.d("TAG", "3")
            return ""
        }
    }

}

