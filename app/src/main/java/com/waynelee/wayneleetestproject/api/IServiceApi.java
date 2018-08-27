package com.waynelee.wayneleetestproject.api;

import com.waynelee.wayneleetestproject.model.Book;
import com.waynelee.wayneleetestproject.model.MovieInfo;
import com.waynelee.wayneleetestproject.model.MovieTop250;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 接口
 */
public interface IServiceApi {
    /**
     * 获取图书信息
     */
    @GET("v2/book/1220562")
    Observable<Book> getBookInfo();


    /**
     * 获取影片信息
     */
    @GET("v2/movie/subject/{id}")
    Observable<MovieInfo> getMovieSubject(@Path("id") String id);

    /**
     * 获取影片信息
     */
    @GET("v2/movie/top250")
    Observable<MovieTop250> getMovieTop250(@Query("start") String start,
                                           @Query("count") String count);
}
