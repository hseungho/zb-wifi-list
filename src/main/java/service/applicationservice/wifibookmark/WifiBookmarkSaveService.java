package service.applicationservice.wifibookmark;

import service.controller.dto.WifiBookmarkSaveRequestDto;
import service.controller.dto.WifiBookmarkSaveResponseDto;

public interface WifiBookmarkSaveService {

    WifiBookmarkSaveResponseDto saveWifiBookmark(WifiBookmarkSaveRequestDto dto);

}
