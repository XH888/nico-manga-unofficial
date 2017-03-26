package com.xh.study.niconico.util;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;
import com.xh.study.niconico.entity.base.ObjectBean;
import com.xh.study.niconico.module.home.episodes.PlayerScreenFragment;

import java.util.List;

/**
 * Created by xh on 1/31/17.
 */

public class DBUtil {

    public static final String DB_HISTORY = "db_history";
    public static final String DB_EPISODES = "db_EPISODEs";

    public static int KeySize(Context context, String key) {
        int size = 0;
        try {
            DB snappydb = DBFactory.open(context, DB_HISTORY);
            if (snappydb.exists(key))
                size = snappydb.findKeys(key).length;
            snappydb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return size;
    }


    /**
     * 保存章节信息
     * episodId:episode
     */
    public static void saveEpisodeList(Context context, List<ObjectBean> episodeList) {
        try {
            DB snappydb = DBFactory.open(context, DB_EPISODES);
            for (ObjectBean episode : episodeList) {
                String key = String.valueOf( episode.getId() );
                snappydb.put(key, episode);
            }
            snappydb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }
    /**
     * 保存阅读记录
     * contentId:episodeId
     */
    public static void saveReadedEpisode(Context context,ObjectBean episode) {
        try {
            DB snappydb = DBFactory.open(context, DB_HISTORY);
            snappydb.put(String.valueOf(episode.getMeta().getContent_id()),String.valueOf( episode.getId()));
            snappydb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }
    /**
     * 查找阅读记录实体
     */
    public static ObjectBean findReadedEpisode(Context context,int contentId) {
        ObjectBean episode = null;
        try {
            DB snappydb = DBFactory.open(context, DB_HISTORY);
            if(snappydb.exists(String.valueOf( contentId))){
                String episodeId = snappydb.get(String.valueOf(contentId));
                snappydb = DBFactory.open(context, DB_EPISODES);
                episode = snappydb.getObject(episodeId,ObjectBean.class);
            }
            snappydb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return episode;
    }
    /**
     * 查找记录实体
     */
    public static ObjectBean findEpisode(Context context, int episodeId) {
        ObjectBean episode = null;
        try {
            DB snappydb = DBFactory.open(context, DB_EPISODES);
            if(snappydb.exists(String.valueOf( episodeId)))
                episode = snappydb .getObject(String.valueOf( episodeId),ObjectBean.class);
            snappydb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return episode;
    }


}
