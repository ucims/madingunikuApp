package com.madinguniku.fkom.madun;

/**
 * Created by heartfilia on 06/01/19.
 */

public class SetGetPost {

    private int id_post, id_prodi;
    private String judul, tanggal, level, file_url, keterangan;

    public SetGetPost(int id_post, String judul, String tanggal, int id_prodi, String level, String file_url, String keterangan) {
        this.id_post = id_post;
        this.judul = judul;
        this.tanggal = tanggal;
        this.id_prodi = id_prodi;
        this.level = level;
        this.file_url = file_url;
        this.keterangan = keterangan;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public int getId_prodi() {
        return id_prodi;
    }

    public void setId_prodi(int id_prodi) {
        this.id_prodi = id_prodi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
