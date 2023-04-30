package service.applicationservice;

import service.controller.dto.WifiBookmarkResponseDto;

import java.util.List;

public interface WifiBookmarkFindService {

    List<WifiBookmarkResponseDto> getWifiBookmarkList();

}
