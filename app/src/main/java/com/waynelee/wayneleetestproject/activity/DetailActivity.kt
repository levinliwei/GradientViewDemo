package com.waynelee.wayneleetestproject.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.teamar.electronicstickets.helper.XRecyclerViewHelper
import com.waynelee.wayneleetestproject.R
import com.waynelee.wayneleetestproject.adapter.DetailAdapter
import com.waynelee.wayneleetestproject.api.BaseApi
import com.waynelee.wayneleetestproject.api.IServiceApi
import com.waynelee.wayneleetestproject.model.MovieTop250
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * @author      levin
 * @Summary     DetailActivity
 * @email       levin.li@teamar.cn
 * @data        2018/7/24
 * @org         Aurora Team
 */
class DetailActivity : AppCompatActivity(),XRecyclerView.LoadingListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initView()
        requestData()
    }

    private fun initView() {
        rvBookList.layoutManager = LinearLayoutManager(this)
        XRecyclerViewHelper(this).setXRecyclerViewStyle(rvBookList, true)
    }

    private fun requestData() {
        BaseApi.request(BaseApi.createApi(IServiceApi::class.java).getMovieTop250("0", "10"),
                object : BaseApi.IResponseListener<MovieTop250> {
                    override fun onSuccess(data: MovieTop250) {
                        setData(data)
                    }

                    override fun onFail() {

                    }
                })
    }

    private fun setData(movieTop250: MovieTop250) {
        tvMovieTop250Title.text = movieTop250.title
        rvBookList.adapter = DetailAdapter(this, movieTop250.subjects!!)
        rvBookList.refreshComplete()
    }

    override fun onRefresh() {
        requestData()
    }

    override fun onLoadMore() {

    }
}