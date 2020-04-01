package learn.tassel.community.Model;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Long parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModifity;
    private Long likeCount;
    private String content;

}
