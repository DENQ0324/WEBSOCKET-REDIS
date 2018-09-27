package com.lensyn.ispbs.entity.home;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author  DENQ
 * @date 创建时间：2018年9月26日 上午10:14:50  
 * @version 1.0 
 */
@Getter
@Setter
@ToString
public class EconomicOperationIndex {

    private String powerStationId;//电站ID
    private String typeCode;//状态：01：当期，02：去年同期
    private String appliedTime;//日期
    private Double data;//值
}

