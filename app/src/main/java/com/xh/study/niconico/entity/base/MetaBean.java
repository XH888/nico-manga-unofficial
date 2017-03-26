package com.xh.study.niconico.entity.base;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.graphics.Palette;

import java.util.List;

/**
 * Created by xh on 12/26/16.
 */

public class MetaBean implements Parcelable{

    private String thumbnail_url;

    //top feature/attention
    private String display_author_name ;
    private String title ;
    private String description;
    private String main_image_url;

    //ranking
    private CounterBean counter;
    private long created_at;
    private long updated_at;
    private String serial_status;
    private String player_type;
    private String category;
    private String content_type;
    private List<AuthorBean> authors;
    private OfficialBean official;
    private RatingBean rating;
    private ExpressionsBean expressions;

    //official category
    private String name ;
    private String short_name ;
    private long last_content_updated_at;
    private ColorBean background_color;

    //officials
    private String square_thumbnail_url;
    private String vertical_thumbnail_url;

    //episodes
    private int content_id;

    //frames
    private int duration;
    private int width;
    private int height;
    private boolean is_spread;
    private String source_url;
    private String drm_hash;
    private String link_url;

    //comment
    private int frame_id;
    private int frame_index;
    private String text;
    private String command;
    private float delay;

    public MetaBean() {
    }

    //-------Parcelable Start---------
    protected MetaBean(Parcel in) {
        thumbnail_url = in.readString();
        display_author_name = in.readString();
        title = in.readString();
        description = in.readString();
        main_image_url = in.readString();
        counter = in.readParcelable(CounterBean.class.getClassLoader());
        created_at = in.readLong();
        updated_at = in.readLong();
        serial_status = in.readString();
        player_type = in.readString();
        category = in.readString();
        content_type = in.readString();
        authors = in.createTypedArrayList(AuthorBean.CREATOR);
        official = in.readParcelable(OfficialBean.class.getClassLoader());
        rating = in.readParcelable(RatingBean.class.getClassLoader());
        expressions = in.readParcelable(ExpressionsBean.class.getClassLoader());
        name = in.readString();
        short_name = in.readString();
        last_content_updated_at = in.readLong();
        square_thumbnail_url = in.readString();
        vertical_thumbnail_url = in.readString();
        content_id = in.readInt();
        duration = in.readInt();
        width = in.readInt();
        height = in.readInt();
        is_spread = in.readByte() != 0;
        source_url = in.readString();
        drm_hash = in.readString();
        link_url = in.readString();
        frame_id = in.readInt();
        frame_index = in.readInt();
        text = in.readString();
        command = in.readString();
        delay = in.readFloat();
    }

    public static final Creator<MetaBean> CREATOR = new Creator<MetaBean>() {
        @Override
        public MetaBean createFromParcel(Parcel in) {
            return new MetaBean(in);
        }

        @Override
        public MetaBean[] newArray(int size) {
            return new MetaBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(thumbnail_url);
        dest.writeString(display_author_name);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(main_image_url);
        dest.writeParcelable(counter, flags);
        dest.writeLong(created_at);
        dest.writeLong(updated_at);
        dest.writeString(serial_status);
        dest.writeString(player_type);
        dest.writeString(category);
        dest.writeString(content_type);
        dest.writeTypedList(authors);
        dest.writeParcelable(official, flags);
        dest.writeParcelable(rating, flags);
        dest.writeParcelable(expressions, flags);
        dest.writeString(name);
        dest.writeString(short_name);
        dest.writeLong(last_content_updated_at);
        dest.writeString(square_thumbnail_url);
        dest.writeString(vertical_thumbnail_url);
        dest.writeInt(content_id);
        dest.writeInt(duration);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeByte((byte) (is_spread ? 1 : 0));
        dest.writeString(source_url);
        dest.writeString(drm_hash);
        dest.writeString(link_url);
        dest.writeInt(frame_id);
        dest.writeInt(frame_index);
        dest.writeString(text);
        dest.writeString(command);
        dest.writeFloat(delay);
    }
    //-------Parcelable End---------

    public String getSquare_thumbnail_url() {
        return square_thumbnail_url;
    }

    public void setSquare_thumbnail_url(String square_thumbnail_url) {
        this.square_thumbnail_url = square_thumbnail_url;
    }

    public String getVertical_thumbnail_url() {
        return vertical_thumbnail_url;
    }

    public void setVertical_thumbnail_url(String vertical_thumbnail_url) {
        this.vertical_thumbnail_url = vertical_thumbnail_url;
    }

    public OfficialBean getOfficial() {
        return official;
    }

    public void setOfficial(OfficialBean official) {
        this.official = official;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public long getLast_content_updated_at() {
        return last_content_updated_at;
    }

    public void setLast_content_updated_at(long last_content_updated_at) {
        this.last_content_updated_at = last_content_updated_at;
    }

    public ColorBean getBackground_color() {
        return background_color;
    }

    public void setBackground_color(ColorBean background_color) {
        this.background_color = background_color;
    }

    public String getDisplay_author_name() {
        return display_author_name;
    }

    public void setDisplay_author_name(String display_author_name) {
        this.display_author_name = display_author_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public CounterBean getCounter() {
        return counter;
    }

    public void setCounter(CounterBean counter) {
        this.counter = counter;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public String getSerial_status() {
        return serial_status;
    }

    public void setSerial_status(String serial_status) {
        this.serial_status = serial_status;
    }

    public String getPlayer_type() {
        return player_type;
    }

    public void setPlayer_type(String player_type) {
        this.player_type = player_type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getMain_image_url() {
        return main_image_url;
    }

    public void setMain_image_url(String main_image_url) {
        this.main_image_url = main_image_url;
    }

    public List<AuthorBean> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorBean> authors) {
        this.authors = authors;
    }

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public ExpressionsBean getExpressions() {
        return expressions;
    }

    public void setExpressions(ExpressionsBean expressions) {
        this.expressions = expressions;
    }

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean is_spread() {
        return is_spread;
    }

    public void setIs_spread(boolean is_spread) {
        this.is_spread = is_spread;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public int getFrame_id() {
        return frame_id;
    }

    public void setFrame_id(int frame_id) {
        this.frame_id = frame_id;
    }

    public int getFrame_index() {
        return frame_index;
    }

    public void setFrame_index(int frame_index) {
        this.frame_index = frame_index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public float getDelay() {
        return delay;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }

    public String getDrm_hash() {
        return drm_hash;
    }

    public void setDrm_hash(String drm_hash) {
        this.drm_hash = drm_hash;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public static class CounterBean implements Parcelable{
        private int view;
        private int comment;
        private int favorite;
        private int episode;
        //episode
        private int frame;  //页数

        public CounterBean() {
        }

        //----------- Parcelable Start ---------------
        protected CounterBean(Parcel in) {
            view = in.readInt();
            comment = in.readInt();
            favorite = in.readInt();
            episode = in.readInt();
            frame = in.readInt();
        }

        public static final Creator<CounterBean> CREATOR = new Creator<CounterBean>() {
            @Override
            public CounterBean createFromParcel(Parcel in) {
                return new CounterBean(in);
            }

            @Override
            public CounterBean[] newArray(int size) {
                return new CounterBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(view);
            dest.writeInt(comment);
            dest.writeInt(favorite);
            dest.writeInt(episode);
            dest.writeInt(frame);
        }
        //----------- Parcelable End ---------------

        public int getView() {
            return view;
        }

        public void setView(int view) {
            this.view = view;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public int getFavorite() {
            return favorite;
        }

        public void setFavorite(int favorite) {
            this.favorite = favorite;
        }

        public int getEpisode() {
            return episode;
        }

        public void setEpisode(int episode) {
            this.episode = episode;
        }

        public int getFrame() {
            return frame;
        }

        public void setFrame(int frame) {
            this.frame = frame;
        }
    }

    public static class AuthorBean implements Parcelable{
        private int nico_id;
        private String name;
        private String comment;
        private String thumbnail_url;

        //----------- Parcelable Start ---------------
        protected AuthorBean(Parcel in) {
            nico_id = in.readInt();
            name = in.readString();
            comment = in.readString();
            thumbnail_url = in.readString();
        }

        public static final Creator<AuthorBean> CREATOR = new Creator<AuthorBean>() {
            @Override
            public AuthorBean createFromParcel(Parcel in) {
                return new AuthorBean(in);
            }

            @Override
            public AuthorBean[] newArray(int size) {
                return new AuthorBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(nico_id);
            dest.writeString(name);
            dest.writeString(comment);
            dest.writeString(thumbnail_url);
        }
        //----------- Parcelable End ---------------


        public int getNico_id() {
            return nico_id;
        }

        public void setNico_id(int nico_id) {
            this.nico_id = nico_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getThumbnail_url() {
            return thumbnail_url;
        }

        public void setThumbnail_url(String thumbnail_url) {
            this.thumbnail_url = thumbnail_url;
        }


    }

    public static class OfficialBean implements Parcelable{
        private int id;
        private MetaBean meta ;

        //----------- Parcelable Start ---------------
        protected OfficialBean(Parcel in) {
            id = in.readInt();
            meta = in.readParcelable(MetaBean.class.getClassLoader());
        }

        public static final Creator<OfficialBean> CREATOR = new Creator<OfficialBean>() {
            @Override
            public OfficialBean createFromParcel(Parcel in) {
                return new OfficialBean(in);
            }

            @Override
            public OfficialBean[] newArray(int size) {
                return new OfficialBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeParcelable(meta, flags);
        }
        //----------- Parcelable End ---------------


        public MetaBean getMeta() {
            return meta;
        }

        public void setMeta(MetaBean meta) {
            this.meta = meta;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class RatingBean implements Parcelable{
        private String violence;
        private String adult;

        //-------Parcelable Start---------
        protected RatingBean(Parcel in) {
            violence = in.readString();
            adult = in.readString();
        }

        public static final Creator<RatingBean> CREATOR = new Creator<RatingBean>() {
            @Override
            public RatingBean createFromParcel(Parcel in) {
                return new RatingBean(in);
            }

            @Override
            public RatingBean[] newArray(int size) {
                return new RatingBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(violence);
            dest.writeString(adult);
        }
        //-------Parcelable End---------


        public String getViolence() {
            return violence;
        }

        public void setViolence(String violence) {
            this.violence = violence;
        }

        public String getAdult() {
            return adult;
        }

        public void setAdult(String adult) {
            this.adult = adult;
        }
    }

    public static class ExpressionsBean implements Parcelable{
        private boolean boys_love;


        //-------Parcelable Start---------
        protected ExpressionsBean(Parcel in) {
            boys_love = in.readByte() != 0;
        }

        public static final Creator<ExpressionsBean> CREATOR = new Creator<ExpressionsBean>() {
            @Override
            public ExpressionsBean createFromParcel(Parcel in) {
                return new ExpressionsBean(in);
            }

            @Override
            public ExpressionsBean[] newArray(int size) {
                return new ExpressionsBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte((byte) (boys_love ? 1 : 0));
        }
        //-------Parcelable End---------

        public boolean isBoys_love() {
            return boys_love;
        }

        public void setBoys_love(boolean boys_love) {
            this.boys_love = boys_love;
        }
    }

}
