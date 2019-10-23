package cn.lovingliu.mmall.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author：LovingLiu
 * @Description: PmsBrand的表单验证
 * @Date：Created in 2019-10-23
 */
@Data
public class PmsBrandDto {

    @NotEmpty(message = "名称必须填写")
    private String name;
    private String firstLetter;
    private Integer sort;
    private Integer factoryStatus;
    private Integer showStatus;
    private Integer productCount;
    private Integer productCommentCount;
    private String logo;
    private String bigPic;
    private String brandStory;
}
