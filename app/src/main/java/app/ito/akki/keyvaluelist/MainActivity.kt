package app.ito.akki.keyvaluelist

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import app.ito.akki.keyvaluelist.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    //Activityを開くときにNUllエラーが発生するため、今回はlateinitで宣言している。
    private lateinit var sharedPref: SharedPreferences
    private var savedMutableList = mutableListOf<Any>()
    private lateinit var binding: ActivityMainBinding
    //キー：sharedPreferencesで保存する際に必要となる。
    private val key: String = "key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sharedPref = getSharedPreferences("savedList", Context.MODE_PRIVATE)
        createDummyData(key = key)
        loadData(key = key)
    }

    //ダミーデータを生成して保存している。
    private fun createDummyData(key: String){
        for (i in 0..10) {
            savedMutableList.add(i)
            val jsonArray = JSONArray(savedMutableList)
            sharedPref.edit().putString(key, jsonArray.toString()).apply()
        }
    }

    //保存したデータを読み出すようにしている。
    private fun loadData(key: String){
        val contents = JSONArray(sharedPref.getString(key,"[]"))
        val list = mutableListOf<String>()
        if (contents != null) {
            for (content in 0 until contents.length()){
                list.add(contents[content].toString())
            }
        }
        setUpRecyclerView(list)
    }

    //リサイクラービューのセットアップ
    fun setUpRecyclerView(list: MutableList<String>){
        val recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewAdapter(this, list)
    }
}