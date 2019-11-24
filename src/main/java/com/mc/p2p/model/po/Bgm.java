package com.mc.p2p.model.po;

import java.io.Serializable;

public class Bgm implements Serializable {
    private String bgmId;

    private String bgmUri;

    private String bgmName;

    private String bgmPic;

    private static final long serialVersionUID = 1L;

    public String getBgmId() {
        return bgmId;
    }

    public void setBgmId(String bgmId) {
        this.bgmId = bgmId;
    }

    public String getBgmUri() {
        return bgmUri;
    }

    public void setBgmUri(String bgmUri) {
        this.bgmUri = bgmUri;
    }

    public String getBgmName() {
        return bgmName;
    }

    public void setBgmName(String bgmName) {
        this.bgmName = bgmName;
    }

    public String getBgmPic() {
        return bgmPic;
    }

    public void setBgmPic(String bgmPic) {
        this.bgmPic = bgmPic;
    }
}