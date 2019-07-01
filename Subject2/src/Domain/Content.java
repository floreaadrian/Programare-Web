package Domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adi
 */
public class Content {
    private String date;
    private String title;
    private String desc;
    private String url;
    private int userid;

    public Content(String date, String title, String desc, String url, int userid) {
        this.date = date;
        this.title = title;
        this.desc = desc;
        this.url = url;
        this.userid = userid;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public int getUserid() {
        return userid;
    }

    @Override
    public String toString() {
        return "Domain.Content{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                ", userid=" + userid +
                '}';
    }

    public Map<String, String> toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("date", this.date);
        map.put("title", this.title);
        map.put("desc", this.desc);
        map.put("url", this.url);
        map.put("userid", String.valueOf(this.userid));
        return map;
    }
}
