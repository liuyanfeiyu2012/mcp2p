package com.mc.p2p.domain.video.repository;

import com.mc.p2p.mapper.BgmMapper;
import com.mc.p2p.model.po.Bgm;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Service
public class BgmRepositoryImpl implements BgmRepository {

    @Resource
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
