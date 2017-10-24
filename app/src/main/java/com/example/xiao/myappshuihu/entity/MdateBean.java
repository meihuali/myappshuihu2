package com.example.xiao.myappshuihu.entity;

/**
 * 项目名：MyHorizontaRecycleView
 * 包名：com.example.administrator.myhorizontarecycleview
 * 文件名：MdateBean
 * 创建者 ：$梅华黎
 * 创建时间： 2017/8/16 18:15
 * 描述：TODO
 */
public class MdateBean {
    int imge;
    String name;

    public int getImge() {
        return imge;
    }

    public void setImge(int imge) {
        this.imge = imge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MdateBean{" +
                "imge=" + imge +
                ", name='" + name + '\'' +
                '}';
    }


}
