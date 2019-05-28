//package com.sachin.hooq.DB;
//
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.OnConflictStrategy;
//import android.arch.persistence.room.Query;
//import android.arch.persistence.room.Update;
//
//import com.sachin.hooq.Model.MovieResponseModel;
//
//import java.util.List;
//
//@Dao
//public interface mDao {
//
//    @Query("select * from MovieResponseModelTable where _id = :keyv")
//    public MovieResponseModel getMovieDetails(String keyv);
//
//    @Query("select * from MovieResponseModelTable")
//    public List<MovieResponseModel> getMovieAllDetails();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    public void addMoviemodel(MovieResponseModel movieResponseModel);
//}
