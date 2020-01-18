package com.example.administrator.potato.bean;

/**
 * @author potato
 * @date 2020/1/16
 */

public class MusicBean {
    /**
     * 歌名
     */
    private String name;
    /**
     * 歌手
     */
    private String singer;
    /**
     * 歌曲所占空间大小
     */
    private long size;
    /**
     * 歌曲时间长度
     */
    private int duration;
    /**
     * 歌曲地址
     */
    private String path;
    /**
     * 图片id
     */
    private long albumId;
    /**
     * 歌曲id
     */
    private long id;

    /**
     * 专辑名
     */
    private String album;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
