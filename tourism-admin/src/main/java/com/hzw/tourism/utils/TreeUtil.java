package com.hzw.tourism.utils;

import com.hzw.tourism.vo.TreeNodeVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaohuang
 * @date 2023/6/26
 */
public class TreeUtil<H extends TreeNodeVo> {
    public static <H extends TreeNodeVo>List<H> bulidTree(List<H> treeNodes,Object root){
        ArrayList<H> trees = new ArrayList<>();
        for (H treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())){
                trees.add(treeNode);
            }
            for (H child : treeNodes) {
                if (child.getParentId().equals(treeNode.getId())){
                    if (treeNode.getChildren()==null){
                        treeNode.setChildren(new ArrayList());
                    }
                    treeNode.add(child);
                }
            }
        }
       return trees;
    }
}
