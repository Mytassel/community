package learn.tassel.community.Dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装问题信息和页面
 */

@Data
public class PageQuestionDTO {
        private List<QuestionDTO> question;
        //显示当前页数信息
        private List<Integer> pages;
        //回到第一页
        private Boolean FirstPage;//回到第一页
        private Boolean EndPage;//回到最后一页
        private Boolean nextPage;//下一页
        private Boolean PreviousPage;// 上一页
        private Integer currPage;//当前页，高亮显示
        private Integer totalPage;

    //当总页数为0时
    public void setPageData(Integer page, Integer size, Integer countNum) {
        pages = new ArrayList<Integer>();
        Integer endPage = 1;
        if(countNum%size == 0){
            endPage = countNum/size;
        }else{
            endPage = countNum/size +1;
        }
        pages.add(page);
        for(int i=1;i<=3;i++){
            //当前页的前三页
            if(page-i > 0){
                pages.add(0,page-i);
            }
            //当前页的后三页
            if((page+i)<=endPage){
                pages.add(page+i);
            }
        }
        this.pages = pages;
        this.totalPage=endPage;
        this.currPage= page;
        this.PreviousPage = page > 1 ? true : false;
        this.FirstPage = page == 1 ? false : true;
        this.nextPage = page < endPage ? true : false;
        this.EndPage = page == endPage ? false : true;
    }
}
