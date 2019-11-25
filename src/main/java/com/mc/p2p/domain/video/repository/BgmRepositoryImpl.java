package com.mc.p2p.domain.video.repository;

import com.mc.p2p.mapper.BgmMapper;
import com.mc.p2p.model.po.Bgm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/25 1:52 PM
 */
@Service
public class BgmRepositoryImpl implements BgmRepository {

    @Autowired
    private BgmMapper bgmMapper;

    @Override
    public List<Bgm> selectList() {
        return bgmMapper.selectAll();
    }

    @Override
    public String selectBgmUri(String bgmId) {
        Bgm record = new Bgm();
        record.setBgmId(bgmId);
        Bgm bgm = bgmMapper.selectOne(record);

        return  (null != bgm) ? bgm.getBgmUri() : null;
    }
}
