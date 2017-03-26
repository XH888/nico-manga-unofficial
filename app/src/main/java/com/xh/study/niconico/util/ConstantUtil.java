package com.xh.study.niconico.util;

/**
 * Created by xh on 1/12/17.
 */

public class ConstantUtil {



//    <string name="rank_1_all">総合</string>
//    <string name="rank_2_shonen">少年マンガ</string>
//    <string name="rank_3_seinen">青年マンガ</string>
//    <string name="rank_4_shojo">少女マンガ</string>
//    <string name="rank_5_yonkoma">４コママンガ</string>
//    <string name="rank_6_other">その他</string>
//    <string name="rank_7_fan">ファンコミック</string>


    public enum CATEGORY{
        all,shonen,seinen,shojo,yonkoma,other,fan
    }


//    <item>毎時</item>
//    <item>デイリー</item>
//    <item>週間</item>
//    <item>月間</item>
//    <item>合計</item>

    public enum SPAN{
        hourly,daily,weekly,monthly,total
    }


    public final static String RANK_ID = "rank_id";
    public final static String RANK_ID_POSITION = "rank_id_position";
    public final static String RANK_TITLE_LIST = "rank_titles";

    public final static String RANK_SPAN_POSITION = "rank_span_position";
    public final static String RANK_CATEGORY_POSITION = "rank_category_position";

    public final static String EPISODE_ID = "EPISODE_ID";
    public final static String EPISODE_ARRAY ="EPISODE_ARRAY" ;
    public static final String EPISODE_BEAN = "EPISODE_BEAN";

    //漫画详细 内容传递信息
    public final static String OBJECT_BEAN_ITEM = "episode_object_bean";
    public final static String FRAME_BEAN_LIST = "frame_bean_list";



//    User-Agent: NicoManga-Android/1.3.5 (SDK:22; DEVICE:Xiaomi Redmi 3; UUID:172199384612550fea114fd79b84f79b1451528f;)
//    Cache-Control: no-cache
//    Host: ssl.seiga.nicovideo.jp
//    Connection: Keep-Alive
//    Accept-Encoding: gzip
//    Pragma: no-cache



    public final static String USER_AGENT = "NicoManga-Android/1.3.5 (SDK:22; DEVICE:Xiaomi Redmi 3; UUID:172199384612550fea114fd79b84f79b1451528f;)";
    public final static String CACHE_CONTROL = "no-cache";




}
