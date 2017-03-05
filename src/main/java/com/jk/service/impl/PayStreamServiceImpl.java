package com.jk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.mapper.PayStreamMapper;
import com.jk.model.PayStream;
import com.jk.service.PayStreamService;

/**
 * Created by JK on 2017/1/19.
 */
@Transactional
@Service
public class PayStreamServiceImpl extends BaseServiceImpl<PayStream> implements PayStreamService{

    @Autowired
    private PayStreamMapper paystreamMapper;
    
    @Transactional(readOnly=true)
    @Override
    public PageInfo<PayStream> findPage(Integer pageNum ,Integer pageSize ,String findproject, String finduser, 
            String findtimestart, String findtimeend,String ordertype,String ordervalue){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("findproject", findproject);
        map.put("finduser", finduser);
        map.put("findtimestart", findtimestart);
        map.put("findtimeend", findtimeend);
        
        if("0".equals(ordertype)){
            map.put("ordertype", " id ");
        }else if("1".equals(ordertype)){
            map.put("ordertype", " project_id ");
        }else if("2".equals(ordertype)){
            map.put("ordertype", " user_id ");
        }else if("3".equals(ordertype)){
            map.put("ordertype", " create_time ");
        }

        if("0".equals(ordervalue)){
            map.put("ordervalue" , " asc ");
        }else if("1".equals(ordervalue)){
            map.put("ordervalue" , " desc ");
        }
        
        PageHelper.startPage(pageNum, pageSize,true);
        List<PayStream> paystreamlist=paystreamMapper.paystreamList(map);
        return new PageInfo<PayStream>(paystreamlist);
    }

}
