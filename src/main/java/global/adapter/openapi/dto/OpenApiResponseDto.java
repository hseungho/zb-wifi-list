package global.adapter.openapi.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
public class OpenApiResponseDto {

    private TbPublicWifiInfo TbPublicWifiInfo;

    @Getter
    @ToString
    public static class TbPublicWifiInfo {

        private int list_total_count;

        private List<WifiInfoDto> row;

        @Getter
        @ToString
        public static class WifiInfoDto {
            private String X_SWIFI_MGR_NO;
            private String X_SWIFI_WRDOFC;
            private String X_SWIFI_MAIN_NM;
            private String X_SWIFI_ADRES1;
            private String X_SWIFI_ADRES2;
            private String X_SWIFI_INSTL_FLOOR;
            private String X_SWIFI_INSTL_TY;
            private String X_SWIFI_INSTL_MBY;
            private String X_SWIFI_SVC_SE;
            private String X_SWIFI_CMCWR;
            private Integer X_SWIFI_CNSTC_YEAR;
            private String X_SWIFI_INOUT_DOOR;
            private String X_SWIFI_REMARS3;
            private Double LAT;
            private Double LNT;
            private LocalDateTime WORK_DTTM;
        }

    }
}
