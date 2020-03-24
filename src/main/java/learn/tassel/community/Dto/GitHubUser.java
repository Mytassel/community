package learn.tassel.community.Dto;

//"id": 35387512,
//"name": "Mytassel",
//"bio": null

import lombok.Data;

@Data
public class GitHubUser {
    private String name;
    private  Long id;
    private String bio;
    private String avatar_url;
}
