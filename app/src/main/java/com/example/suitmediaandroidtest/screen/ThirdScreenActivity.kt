package com.example.suitmediaandroidtest.screen
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.suitmediaandroidtest.databinding.ActivityThirdscreenBinding
import com.example.suitmediaandroidtest.api.AdapterUser
import com.example.suitmediaandroidtest.api.Data
import com.example.suitmediaandroidtest.api.ResponseUser
import com.example.suitmediaandroidtest.api.RetrofitClient
import com.example.suitmediaandroidtest.api.UserAPI
import com.example.suitmediaandroidtest.api.UserOnClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdScreenActivity : AppCompatActivity(), UserOnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: ActivityThirdscreenBinding
    private val list = ArrayList<Data>()
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: AdapterUser
    private var page = 1
    private var totalPage = 1
    private var isLoading = false
    private var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = intent.getStringExtra("name") ?: ""

        layoutManager = LinearLayoutManager(this)
        binding.swipeRefresh.setOnRefreshListener(this)
        setupRecyclerView()
        getUsers(false)

        binding.rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val total = adapter.itemCount
                if (!isLoading && page < totalPage) {
                    if (visibleItemCount + pastVisibleItem >= total) {
                        page++
                        getUsers(false)
                    }
                }
            }
        })

        btnBackHandler()
    }

    private fun getUsers(isOnRefresh: Boolean) {
        isLoading = true
        if (!isOnRefresh) binding.progressBar.visibility = View.VISIBLE

        val retro = RetrofitClient.getRetroData().create(UserAPI::class.java)
        retro.getUsers(page).enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    totalPage = response.body()?.total_pages ?: 1
                    val listResponse = response.body()?.data
                    if (listResponse != null) {
                        if (isOnRefresh) {
                            adapter.addList(listResponse)
                        } else {
                            adapter.addList(listResponse)
                        }
                    }
                } else {
                    Toast.makeText(this@ThirdScreenActivity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
                binding.progressBar.visibility = View.INVISIBLE
                isLoading = false
                binding.swipeRefresh.isRefreshing = false
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Toast.makeText(this@ThirdScreenActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.INVISIBLE
                isLoading = false
                binding.swipeRefresh.isRefreshing = false
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.layoutManager = layoutManager
        adapter = AdapterUser(list, this@ThirdScreenActivity)
        binding.rvUsers.adapter = adapter
    }

    override fun onRefresh() {
        adapter.clear()
        page = 1
        getUsers(true)
    }

    private fun btnBackHandler() {
        // Create intent
        val intent = Intent(this, SecondScreenActivity::class.java)
        intent.putExtra("name", name)

        // Move activity when btn_back clicked
        binding.btnBack.setOnClickListener {
            startActivity(intent)
        }

    }

    override fun onUserItemClicked(position: Int) {
        val intent = Intent(this, SecondScreenActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("username", "${list[position].first_name} ${list[position].last_name}")
        startActivity(intent)
    }
}
