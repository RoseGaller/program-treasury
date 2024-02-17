package com.lct.study.dao;

import com.lct.study.bean.NodeInfo;

public interface NodeInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NodeInfo record);

    int insertSelective(NodeInfo record);

    NodeInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NodeInfo record);

    int updateByPrimaryKey(NodeInfo record);
}