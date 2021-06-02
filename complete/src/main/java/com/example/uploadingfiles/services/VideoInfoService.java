package com.example.uploadingfiles.services;

import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.model.VideoInfo;
import com.example.uploadingfiles.repositories.UserRepository;
import com.example.uploadingfiles.repositories.VideoInfoRepository;
import com.example.uploadingfiles.security.UserPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VideoInfoService {


    private final VideoInfoRepository videoInfoRepository;
    private final UserRepository userRepository;

    public VideoInfoService(VideoInfoRepository videoInfoRepository, UserRepository userRepository) {
        this.videoInfoRepository = videoInfoRepository;
        this.userRepository = userRepository;
    }

    public VideoInfo saveVideoInfo(String videoName, String videoDesc, String category, String releaseTime, String releaseDate, String link, String username){
        User user = userRepository.findByUsername(username);
        VideoInfo videoInfo = new VideoInfo(videoName, videoDesc, category, releaseTime, releaseDate, link, user);
        return videoInfoRepository.save(videoInfo);
    }

    public boolean checkVideoInfo(String link){
        return videoInfoRepository.countVideoInfosByLink(link) > 0;
    }

    public int updateVideoInfo(String videoName,String videoDesc,String category,String releaseTime,String releaseDate,String link){
        return videoInfoRepository.updateVideoInfo(videoName, videoDesc, category, releaseTime, releaseDate, link);
    }

    public VideoInfo getVideo(String link){
        return videoInfoRepository.findVideoInfoByLink(link);
    }

    public int deleteVideo(String link){
        return videoInfoRepository.deleteVideoInfoByLink(link);
    }

    public int changeVideoName(String link, String videoName){
        return videoInfoRepository.renameVideoNameByLink(link, videoName);
    }

    public int changeVideoDesc(String link, String videoDesc){
        return videoInfoRepository.renameVideoDescByLink(link, videoDesc);
    }

    public int changeVideoCategory(String link, String category){
        return videoInfoRepository.renameVideoCategoryByLink(link, category);
    }

    @Transactional
    public int changeVideoInfo(String link, String videoName, String videoDesc, String videoCategory){
        if (!videoName.equals("")) videoInfoRepository.renameVideoNameByLink(link, videoName);
        if (!videoDesc.equals("")) videoInfoRepository.renameVideoDescByLink(link, videoDesc);
        if (!videoCategory.equals("")) videoInfoRepository.renameVideoCategoryByLink(link, videoCategory);
        return 1;
    }
}
