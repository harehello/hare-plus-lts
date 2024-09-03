package org.hare.core.sys.vo;

import lombok.Getter;
import lombok.Setter;
import org.hare.common.domain.LabelValueVO;

import java.util.List;

/**
 * @author wang cheng
 */
@Getter
@Setter
public class SysDictVO {


    /** 名称 **/
    private String name;
    /** 编码 **/
    private String code;

    private List<LabelValueVO> items;
}
