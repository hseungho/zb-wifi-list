package service.applicationservice.wifibookmark;

import service.controller.dto.WifiBookmarkSaveResponseDto;

public interface WifiBookmarkSaveService {

    WifiBookmarkSaveResponseDto saveWifiBookmark(String wifiId, Long bookmarkId);

}
