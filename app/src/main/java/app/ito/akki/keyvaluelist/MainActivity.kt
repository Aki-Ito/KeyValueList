package app.ito.akki.keyvaluelist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import app.ito.akki.keyvaluelist.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    val sharedPref = getSharedPreferences("savedList", Context.MODE_PRIVATE)
    var savedMutableList = mutableListOf<Any>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        createDummyData()
        loadData()
    }

    fun createDummyData(){
        for (i in 0..10) {
            savedMutableList.add(i)
            val jsonArray = JSONArray(savedMutableList)
            sharedPref.edit().putString("savedList", jsonArray.toString())
            sharedPref.edit().apply()
        }
    }

    fun loadData(){
       val contents = sharedPref.getString("savedList","[]")
       val list = mutableListOf<String>()
        if (contents != null) {
            for (content in 0 until contents.length){
                list.add(contents[content].toString())
            }
        }
        setUpRecyclerView(list)
    }

    fun setUpRecyclerView(list: MutableList<String>){
        val recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewAdapter(this, list)
    }
}