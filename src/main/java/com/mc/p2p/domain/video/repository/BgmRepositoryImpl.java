package com.mc.p2p.domain.video.repository;
import com.mc.p2p.mapper.BgmMapper;
import com.mc.p2p.model.po.Bgm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public Bgm selectBgm(String bgmId) {
        if (StringUtils.isEmpty(bgmId)) {
            return null;
        }

        Bgm record = new Bgm();
        record.setBgmId(bgmId);
        return bgmMapper.selectOne(record);
    }
}
