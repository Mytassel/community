package learn.tassel.community.Model;

import lombok.Data;

@Data
public class Question {
    private Long id;//发布信息id
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModifity;
    private Integer creater;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
}
