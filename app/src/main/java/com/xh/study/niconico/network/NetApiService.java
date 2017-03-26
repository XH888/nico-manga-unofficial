package com.xh.study.niconico.network;

import com.xh.study.niconico.entity.AttentionInfo;
import com.xh.study.niconico.entity.CommentsInfo;
import com.xh.study.niconico.entity.EpisodesInfo;
import com.xh.study.niconico.entity.FramesInfo;
import com.xh.study.niconico.entity.OfficialDetailInfo;
import com.xh.study.niconico.entity.OfficialInfo;
import com.xh.study.niconico.entity.RankingInfo;
import com.xh.study.niconico.entity.RankingSummaryInfo;
import com.xh.study.niconico.entity.TopInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xh on 12/22/16.
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/contents/ranking?limit=20&offset=20&span=daily&category=all 
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/contents/ranking?limit=20&offset=0&span=hourly&category=shonen 
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/contents/ranking?limit=20&offset=0&span=weekly&category=shonen 
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/contents/ranking?limit=20&offset=0&span=monthly&category=other 
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/contents/ranking?limit=20&offset=0&span=total&category=yonkoma 
 *
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/contents/26397/episodes
 *
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/episodes/221730/frames
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/episodes/221730/comments
 *
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/officials/38/contents?serial_status=all&limit=5
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/contents/25594/recommend?limit=5
 *
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/contents?sort=-contents_updated&limit=20&offset=0&category=all
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/aggregate/homescreen
 *
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/officials
 * https://ssl.seiga.nicovideo.jp/api/v1/app/manga/aggregate/officials/43
 */

public interface NetApiService {

    String ENDPOINT = "https://ssl.seiga.nicovideo.jp/api/v1/app/manga/";

    @GET("aggregate/homescreen")
    Observable<TopInfo> getTopData();

    @GET("contents/attention")
    Observable<AttentionInfo> getAttentionInfo(@Query("limit") int limit);

    @GET("contents/ranking/summary")
    Observable<RankingSummaryInfo> getRankingSummaryInfo(@Query("limit") int limit, @Query("span") String span);

    @GET("contents/ranking")
    Observable<RankingInfo> getRankingInfo(@Query("limit") int limit, @Query("offset") int offset, @Query("span") String span, @Query("category") String category);

    @GET("officials")
    Observable<OfficialInfo> getOfficalData();

    @GET("aggregate/officials/{id}")
    Observable<OfficialDetailInfo> getOfficalDataDetail(@Path("id") int id);

    @GET("contents/{id}/episodes")
    Observable<EpisodesInfo> getEpisodesInfo(@Path("id") int id);







    @GET("episodes/{id}/frames")
    Observable<FramesInfo> getFramesInfo(@Path("id") int id);

    @GET("episodes/{id}/comments")
    Observable<CommentsInfo> getCommentsInfo(@Path("id") int id);

}
