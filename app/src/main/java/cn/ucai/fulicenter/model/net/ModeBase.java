package cn.ucai.fulicenter.model.net;

import cn.ucai.fulicenter.model.utils.OkUtils;

/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class ModeBase implements IModeBase {
    @Override
    public void release() {
        OkUtils.release();
    }
}
